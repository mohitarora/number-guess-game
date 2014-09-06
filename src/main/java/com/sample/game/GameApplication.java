package com.sample.game;

import com.sample.game.resources.ResultResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Main application class of dropwizard microservice
 */
public class GameApplication extends Application<GameConfiguration> {

    public static void main(String... args) throws Exception {
        new GameApplication().run(args);
    }

    @Override
    public String getName() {
        return "number-guess-game";
    }

    /**
     * Configure aspects of the service required before the service is run,
     * like bundles, configuration source providers, etc.
     *
     * @param bootstrap
     */
    @Override
    public void initialize(Bootstrap<GameConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/"));
    }

    @Override
    public void run(GameConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new ResultResource(configuration.getTargetNumber()));
        environment.jersey().setUrlPattern("/api/*");
    }
}

package com.sample.game.resources;

import com.codahale.metrics.annotation.Timed;
import com.sample.game.api.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource to get the Game result.
 */
@Path("/result")
@Produces(MediaType.APPLICATION_JSON)
public class ResultResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultResource.class);

    /**
     * Target number that should be matched, Will be injected by dropwizard.
     */
    private final Integer targetNumber;

    public ResultResource(Integer targetNumber) {
        this.targetNumber = targetNumber;
    }

    /**
     * @param userGuess - Number that user guesses. Type IntParam will return 400 Bad Request for non integer values
     * @return - Result after comparing user guess with the target number.
     */
    @GET
    @Timed
    public Result getResult(@QueryParam("number") Integer userGuess) {

        //Will return 400 Bad Request if guess doesn't match with target.
        LOGGER.info("Calculating Result");

        if (userGuess == null || userGuess.intValue() != targetNumber.intValue()) {
            Exception cause = new IllegalArgumentException("User guess didn't match with target");
            throw new WebApplicationException(cause, Response.Status.BAD_REQUEST);
        }

        return new Result(true);

    }
}

package com.sample.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Dropwizard application configuration file. This file loads the application configuration from YAML configuration file.
 */
public class GameConfiguration extends Configuration {

    /**
     * Target number that player need to guess. It can only be between 1-20. Application will not start otherwise
     */
    @NotNull
    @Min(1)
    @Max(20)
    private Integer targetNumber;

    @JsonProperty
    public Integer getTargetNumber() {
        return targetNumber;
    }

    @JsonProperty
    public void setTargetNumber(Integer targetNumber) {
        this.targetNumber = targetNumber;
    }
}

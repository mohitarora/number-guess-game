package com.sample.game.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the result of game played
 */
public class Result {

    private boolean valid;

    public Result(boolean valid) {
        this.valid = valid;
    }

    @JsonProperty
    public boolean isValid() {
        return valid;
    }

}

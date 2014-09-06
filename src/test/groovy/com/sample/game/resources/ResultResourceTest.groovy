package com.sample.game.resources

import spock.lang.Specification

/**
 * Unit test cases for Result Resource
 */

class ResultResourceTest extends Specification {

    def "10 should give back the valid result"() {
        given:
        def resource = new ResultResource(10)
        when:
        def result = resource.getResult(10)
        then:
        result

    }

}

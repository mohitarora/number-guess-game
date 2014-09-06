package com.sample.game.resources

import spock.lang.Specification
import spock.lang.Unroll

import javax.ws.rs.WebApplicationException

/**
 * Unit test cases for Result Resource
 */

class ResultResourceTest extends Specification {

    def resource = new ResultResource(10)

    def "10 should give back the valid result"() {
        expect:
            resource.getResult(number)
        where:
            number << 10
    }

    @Unroll
    def "#number should give back the invalid result"() {
        when:
            resource.getResult(number)
        then:
            thrown(WebApplicationException)
        where:
            number << [1,2,3,null,9,11,13]
    }

}

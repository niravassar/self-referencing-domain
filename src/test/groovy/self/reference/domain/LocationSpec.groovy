package self.reference.domain

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class LocationSpec extends Specification implements DomainUnitTest<Location> {

    void "test location many self"() {
        setup:
        Location texas = new Location(name: "Texas").save(failOnError: true)
        Location dallas = new Location(name: "Dallas").save()
        Location austin  = new Location(name: "Austin").save()
        Location us = new Location(name: "US").save()
        texas.addToSublocations(dallas)
        texas.addToSublocations(austin)
        texas.parent = us

        when:
        Location location = Location.findByName("Texas")

        then:
        // top level
        location.name == "Texas"
        location.parent.name == "US"

        // second level
        location.sublocations[0].name == "Dallas"
        location.sublocations[0].parent == null
        location.sublocations[0].sublocations == null

        // second level
        location.sublocations[1].name == "Austin"
        location.sublocations[1].parent == null
        location.sublocations[1].sublocations == null
    }
}

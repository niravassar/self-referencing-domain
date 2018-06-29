package self.reference.domain

class Location {
    String name
    List<Location> sublocations
    Location parent

    static hasMany = [sublocations: Location]
    static mappedBy = [ sublocations: "none", parent: "none" ]
    static constraints = { parent nullable: true }

    String toString() {
        name
    }
}

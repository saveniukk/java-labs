public class Address {
    private String street;
    private String building;
    private int apartment;

    public Address(String street, String building, int apartment) {
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "вул. " + street + ", буд. " + building + ", кв. " + apartment;
    }
}

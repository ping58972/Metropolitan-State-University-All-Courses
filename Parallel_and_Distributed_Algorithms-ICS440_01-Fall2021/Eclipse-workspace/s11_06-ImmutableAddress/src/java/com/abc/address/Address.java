package com.abc.address;

/**
 * Instances are immutable
 */
public final class Address {
    private final String street1;
    private final String street2;
    private final String street3;
    private final String city;
    private final String state;
    private final String zip;

    private Address(Builder builder) {
        this.street1 = builder.street1;
        this.street2 = builder.street2;
        this.street3 = builder.street3;
        this.city = builder.city;
        this.state = builder.state;
        this.zip = builder.zip;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getStreet3() {
        return street3;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public Address createCopyWithZip(String newZip) {
        return new Builder(this).setZip(newZip).create();
    }

    public static class Builder  {
        private String street1;
        private String street2;
        private String street3;
        private String city;
        private String state;
        private String zip;

        public Builder(Address initial) {
            this.street1 = initial.street1;
            this.street2 = initial.street2;
            this.street3 = initial.street3;
            this.city = initial.city;
            this.state = initial.state;
            this.zip = initial.zip;
        }

        public Builder() {
        }

        public Address create() throws IllegalArgumentException {
            if (city == null) throw new IllegalArgumentException("city must be specified");
            return new Address(this);
        }

        public Builder setStreet1(String street1) {
            this.street1 = street1;
            return this;
        }

        public Builder setStreet2(String street2) {
            this.street2 = street2;
            return this;
        }

        public Builder setStreet3(String street3) {
            this.street3 = street3;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setZip(String zip) {
            this.zip = zip;
            return this;
        }
    } // type Builder
}

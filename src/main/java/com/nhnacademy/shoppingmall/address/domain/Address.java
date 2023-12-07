package com.nhnacademy.shoppingmall.address.domain;

import java.util.Objects;

public class Address {
    private int addressId;
    private String userId;
    private String state;
    private String city;
    private String addressLine;
    private String postalCode;

    public Address(String userId, String state, String city, String addressLine, String postalCode) {
        this.userId = userId;
        this.state = state;
        this.city = city;
        this.addressLine = addressLine;
        this.postalCode = postalCode;
    }

    public Address(int addressId, String userId, String state, String city, String addressLine, String postalCode) {
        this.addressId = addressId;
        this.userId = userId;
        this.state = state;
        this.city = city;
        this.addressLine = addressLine;
        this.postalCode = postalCode;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return addressId == address.addressId && Objects.equals(userId, address.userId) &&
                Objects.equals(state, address.state) && Objects.equals(city, address.city) &&
                Objects.equals(addressLine, address.addressLine) &&
                Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, userId, state, city, addressLine, postalCode);
    }

    @Override
    public String toString() {
        return ""+state + " " + city + " " + addressLine;
    }
}

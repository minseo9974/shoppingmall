package com.nhnacademy.shoppingmall.address.service;

import com.nhnacademy.shoppingmall.address.domain.Address;
import java.util.List;

public interface AddressService {
    Address getAddressById(int addressId);

    void save(Address address);

    void update(Address address);

    void delete(int addressId);

    List<Address> getList(String userId);
}

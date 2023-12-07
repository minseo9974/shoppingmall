package com.nhnacademy.shoppingmall.address.repository;

import com.nhnacademy.shoppingmall.address.domain.Address;
import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    Optional<Address> findById(int addressId);

    int save(Address address);

    int update(Address address);

    int deleteByAddressId(int addressId);
    int countByAddressId(int addressId);

    List<Address> getList(String userId);
}

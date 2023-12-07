package com.nhnacademy.shoppingmall.address.service.impl;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getAddressById(int addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        return addressOptional.orElse(null);
    }

    @Override
    public void save(Address address) {
        int count = addressRepository.countByAddressId(address.getAddressId());
        if (count > 0) {
            throw new RuntimeException("Address Already Exists");
        }
        int result = addressRepository.save(address);
        if (result < 1) {
            throw new RuntimeException("can not saveAddress");
        }
    }

    @Override
    public void update(Address address) {
        int count = addressRepository.countByAddressId(address.getAddressId());
        if (count < 1) {
            throw new RuntimeException("Address not found");
        }
        int result = addressRepository.update(address);
        if (result < 1) {
            throw new RuntimeException("can not update Address");
        }
    }

    @Override
    public void delete(int addressId) {
        int count = addressRepository.countByAddressId(addressId);
        if (count < 1) {
            throw new RuntimeException("Address not found");
        }
        int result = addressRepository.deleteByAddressId(addressId);
        if (result < 1) {
            throw new RuntimeException("can not delete Address");
        }

    }

    @Override
    public List<Address> getList(String userId) {
        return addressRepository.getList(userId);
    }
}

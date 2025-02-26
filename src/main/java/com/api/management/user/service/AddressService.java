package com.api.management.user.service;


import com.api.management.user.dto.address.AddressDto;
import com.api.management.user.dto.address.SearchAddressResponse;
import com.api.management.user.dto.search.AddressSearchRequest;
import com.api.management.user.entity.AddressEntity;

public interface AddressService extends GenericService<AddressEntity, AddressDto> {
    SearchAddressResponse findAddressByCriteria(AddressSearchRequest request);
}

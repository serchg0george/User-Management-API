package com.api.management.user.service;


import com.api.management.user.dto.AddressDto;
import com.api.management.user.dto.search.AddressSearchRequest;
import com.api.management.user.entity.AddressEntity;

import java.util.List;

public interface AddressService extends GenericService<AddressEntity, AddressDto> {
    List<AddressDto> findAddressByCriteria(AddressSearchRequest request);
}

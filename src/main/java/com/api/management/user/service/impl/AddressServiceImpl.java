package com.api.management.user.service.impl;

import com.api.management.user.dto.AddressDto;
import com.api.management.user.entity.AddressEntity;
import com.api.management.user.mapper.AddressMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.AddressRepository;
import com.api.management.user.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl extends GenericServiceImpl<AddressEntity, AddressDto> implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public BaseMapper<AddressEntity, AddressDto> getMapper() {
        return addressMapper;
    }

    @Override
    public JpaRepository<AddressEntity, Long> getRepository() {
        return addressRepository;
    }



}

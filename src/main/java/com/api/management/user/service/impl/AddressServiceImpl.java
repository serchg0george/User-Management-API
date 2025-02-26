package com.api.management.user.service.impl;

import com.api.management.user.dto.address.AddressDto;
import com.api.management.user.dto.address.SearchAddressResponse;
import com.api.management.user.dto.search.AddressSearchRequest;
import com.api.management.user.entity.AddressEntity;
import com.api.management.user.mapper.AddressMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.AddressRepository;
import com.api.management.user.service.AddressService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl extends GenericServiceImpl<AddressEntity, AddressDto> implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final EntityManager entityManager;


    @Override
    public BaseMapper<AddressEntity, AddressDto> getMapper() {
        return addressMapper;
    }

    @Override
    public JpaRepository<AddressEntity, Long> getRepository() {
        return addressRepository;
    }


    @Override
    public SearchAddressResponse findAddressByCriteria(final AddressSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AddressEntity> criteriaQuery = criteriaBuilder.createQuery(AddressEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<AddressEntity> root = criteriaQuery.from(AddressEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate addrInfo = criteriaBuilder.like(root.get("addr_info"), query);
            Predicate addrType = criteriaBuilder.like(root.get("addr_type"), query);

            predicates.add(criteriaBuilder.or(addrInfo, addrType));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<AddressEntity> query = entityManager.createQuery(criteriaQuery);

        final SearchAddressResponse response = new SearchAddressResponse();

        var addresses = query.getResultList().stream().map(addressMapper::mapEntityToDto).toList();

        response.setAddress(addresses);
        response.setAddressesCount(addresses.size());

        return response;
    }
}

package com.api.management.user.service.impl;

import com.api.management.user.dto.PeopleDto;
import com.api.management.user.entity.MailEntity;
import com.api.management.user.entity.PeopleEntity;
import com.api.management.user.exception.NotFoundException;
import com.api.management.user.mapper.PeopleMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.MailRepository;
import com.api.management.user.repository.PeopleRepository;
import com.api.management.user.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PeopleServiceImpl extends GenericServiceImpl<PeopleEntity, PeopleDto> implements PeopleService {

    private final PeopleRepository peopleRepository;
    private final MailRepository mailRepository;

    private final PeopleMapper peopleMapper;

    @Override
    public BaseMapper<PeopleEntity, PeopleDto> getMapper() {
        return peopleMapper;
    }

    @Override
    public JpaRepository<PeopleEntity, Long> getRepository() {
        return peopleRepository;
    }

    @Override
    public PeopleDto setMailToPeople(Long mailId, Long peopleId) {
        MailEntity mailEntity = mailRepository.findById(mailId).orElseThrow(
                () -> new NotFoundException(mailId));
        PeopleEntity peopleEntity = peopleRepository.findById(peopleId).orElseThrow(
                () -> new NotFoundException(peopleId));

        mailEntity.setPeople(peopleEntity);
        mailRepository.save(mailEntity);
        Optional<PeopleEntity> people = peopleRepository.findById(peopleId);
        return peopleMapper.mapEntityToDto(people.get());
    }


    @Override
    public List<PeopleDto> searchPeopleByName(String findPerson) {
        List<PeopleEntity> peopleEntityList = peopleRepository.findAllByFullNameIgnoreCase(findPerson);
        return peopleEntityList.stream()
                .map(peopleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

}

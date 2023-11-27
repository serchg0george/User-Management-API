package com.api.management.user.service.impl;

import com.api.management.user.dto.MailDto;
import com.api.management.user.entity.MailEntity;
import com.api.management.user.mapper.MailMapper;
import com.api.management.user.mapper.base.BaseMapper;
import com.api.management.user.repository.MailRepository;
import com.api.management.user.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl extends GenericServiceImpl<MailEntity, MailDto> implements MailService {

    private final MailRepository mailRepository;
    private final MailMapper mailMapper;

    @Override
    public BaseMapper<MailEntity, MailDto> getMapper() {
        return mailMapper;
    }

    @Override
    public JpaRepository<MailEntity, Long> getRepository() {
        return mailRepository;
    }
}

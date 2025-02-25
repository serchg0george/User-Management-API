package com.api.management.user.service;


import com.api.management.user.dto.MailDto;
import com.api.management.user.dto.search.MailSearchRequest;
import com.api.management.user.entity.MailEntity;

import java.util.List;

public interface MailService extends GenericService<MailEntity, MailDto> {
    List<MailDto> findMailByCriteria(MailSearchRequest request);
}

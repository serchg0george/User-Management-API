package com.api.management.user.service;


import com.api.management.user.dto.mail.MailDto;
import com.api.management.user.dto.mail.SearchMailResponse;
import com.api.management.user.dto.search.MailSearchRequest;
import com.api.management.user.entity.MailEntity;

public interface MailService extends GenericService<MailEntity, MailDto> {
    SearchMailResponse findMailByCriteria(MailSearchRequest request);
}

package com.api.management.user.dto;

import jakarta.validation.constraints.NotNull;


public record MailToPeopleDto(@NotNull Long mailId,
                              @NotNull Long peopleId) {
}

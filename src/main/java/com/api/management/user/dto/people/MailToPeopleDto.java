package com.api.management.user.dto.people;

import jakarta.validation.constraints.NotNull;


public record MailToPeopleDto(@NotNull Long mailId,
                              @NotNull Long peopleId) {
}

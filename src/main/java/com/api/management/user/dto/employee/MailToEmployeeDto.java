package com.api.management.user.dto.employee;

import jakarta.validation.constraints.NotNull;


public record MailToEmployeeDto(@NotNull Long mailId,
                                @NotNull Long peopleId) {
}

package com.cesar.school.presentation.dto.teamsmembers;

import jakarta.validation.constraints.NotNull;

public class AddMemberRequest {

    @NotNull(message = "O ID do membro é obrigatório.")
    public Integer memberId;

    @NotNull(message = "O papel do membro é obrigatório.")
    public String role;
}

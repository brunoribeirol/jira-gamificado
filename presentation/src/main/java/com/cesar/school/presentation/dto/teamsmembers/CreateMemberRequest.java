package com.cesar.school.presentation.dto.teamsmembers;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.vo.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateMemberRequest {

    public Integer id;   // opcional

    @NotBlank(message = "O nome é obrigatório.")
    public String name;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    public String email;

    @NotBlank(message = "A senha é obrigatória.")
    public String password;

    @NotBlank(message = "O papel é obrigatório.")
    public String role;

    /* NOVO campo -------------- */
    @JsonProperty("isAdmin")        // aceita "isAdmin" no JSON
    public boolean isAdmin = false; // default

    /* -------- mapping -------- */
    public Member toDomain() {
        return new Member(
                id != null ? new MemberId(id) : null,
                name,
                email,
                password,
                Role.valueOf(role),
                isAdmin                     // passa adiante
        );
    }
}

package com.tui.proof.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @NotNull
    private Long clientId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String telephone;

    @Email(message = "Email format is not valid")
    private String email;

}

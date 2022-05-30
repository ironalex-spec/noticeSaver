package com.project.noticeSaver.controller.requests;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}

package bank.eltropy.repository;


import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Builder
@Value
public class UserResponse {

    @NotBlank
    private final Long id;

    @NotBlank
    private final String login;
}

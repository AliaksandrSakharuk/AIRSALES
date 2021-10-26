package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @Email(message = "QQQQQQQQQQQ")
    private String email;
    private boolean enabled;
    private ClientDto client;
}

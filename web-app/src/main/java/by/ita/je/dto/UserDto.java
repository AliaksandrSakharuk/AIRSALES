package by.ita.je.dto;

import by.ita.je.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "ENTER YOUR LOGIN")
    private String login;
    private String password;
    @Email(message = "NOT CORRECT EMAIL")
    private String email;
    private Long clientId;
    private boolean enabled;
    private ClientDto client;
    private List<Role> roles;
}

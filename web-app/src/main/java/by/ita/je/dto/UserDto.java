package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


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
    private boolean enabled;
    private ClientDto client;
}

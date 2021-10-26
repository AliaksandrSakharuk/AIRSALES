package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldUserDto {
    @NotBlank(message = "ENTER YOUR LOGIN")
    private String login;
    @Email(message = "NOT CORRECT EMAIL")
    private String email;
}

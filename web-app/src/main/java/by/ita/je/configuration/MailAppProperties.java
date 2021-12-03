package by.ita.je.configuration;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class MailAppProperties {
    private String email;
    private String password;
    public Map<String, String> props;
}

package by.ita.je.controller;

import by.ita.je.dto.ClientDto;
import by.ita.je.dto.FieldUserDto;
import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.KafkaProducerService;
import by.ita.je.service.api.ApiService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class LoginController {

    private final UserService userDetailsService;
    private final ApiService apiService;
    private final ObjectMapper objectMapper;
    private final KafkaProducerService producerService;

    @GetMapping("/login")
    public String get(Model model) {
        return "login";
    }

    @GetMapping("/users/new")
    public String createNewLogin(@ModelAttribute("userDto") UserDto userDto, Model model) {
        model.addAttribute("userDto", userDto);
        return "form_user";
    }

    @GetMapping("/users/test")
    public void test() {
        this.producerService.sendMessage("Create User");
    }

    @PostMapping(value = "/users/save")
    public String resultCreateUser(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult , Model model) {
        if(bindingResult.hasErrors()) {
            return "form_user";
        }
        else{
            ClientDto responce=apiService.saveClient(userDto.getClient());
            User user = objectMapper.convertValue(userDto, User.class);
            user.setClientId(responce.getId());
            boolean result=userDetailsService.saveUser(user);
            model.addAttribute("result", result);
            return "registr";
        }
    }

    @GetMapping("/users/password")
    public String createFieldUserForRenawelPassword(Model model) {
        model.addAttribute("fielduser", new FieldUserDto());
        return "field_user";
    }

    @PostMapping("/users/password/renewal")
    public String renewalPassword(@ModelAttribute FieldUserDto fieldUserDto, Model model){
        final boolean result=userDetailsService.renewalPassword(fieldUserDto);
        model.addAttribute("result", result);
        return "renewal";
    }

    @ModelAttribute("userDto")
    private UserDto getUserDto(){
        UserDto userDto=new UserDto();
        userDto.setClient(new ClientDto());
        return userDto;
    }
}

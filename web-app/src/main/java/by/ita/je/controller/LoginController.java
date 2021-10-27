package by.ita.je.controller;

import by.ita.je.dto.ClientDto;
import by.ita.je.dto.FieldUserDto;
import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.UserDetailsServiceImpl;
import by.ita.je.service.api.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final UserDetailsServiceImpl userDetailsService;
    private final ApiService apiService;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;


    @GetMapping("/login")
    public String get(Model model) {
        return "login";
    }

    @GetMapping("/users/new")
    public String createNewLogin(Model model) {
        UserDto userDto=new UserDto();
        userDto.setClient(new ClientDto());
        model.addAttribute("userDto", userDto);
        return "form_user";
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
}

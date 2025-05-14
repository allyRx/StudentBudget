package org.allyrx.studentbudget.Controllers;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Services.AutheticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class AuthenticationController{

    private final AutheticationService autheticationService;

    @PostMapping( path = "login", consumes = APPLICATION_JSON_VALUE)
    public void register(@RequestBody User user){
        autheticationService.register(user);
    }
}

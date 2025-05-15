package org.allyrx.studentbudget.Controllers;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class AuthenticationController{

    private final AuthenticationService authenticationService;

    @PostMapping( path = "login", consumes = APPLICATION_JSON_VALUE)
    public void register(@RequestBody User user){
        authenticationService.register(user);
    }

    @PostMapping(path = "/activate")
    public void activate(@RequestBody Map<String , String> activation){
        authenticationService.activate(activation);
    }

}

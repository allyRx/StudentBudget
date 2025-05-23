package org.allyrx.studentbudget.Controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allyrx.studentbudget.Dto.AuthenticationDTO;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Services.AuthenticationService;
import org.allyrx.studentbudget.Services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController{

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping( path = "register", consumes = APPLICATION_JSON_VALUE)
    public void register(@RequestBody User user){
        authenticationService.register(user);
    }

    @PostMapping(path = "/activate")
    public void activate(@RequestBody Map<String , String> activation){
        authenticationService.activate(activation);
    }

    @PostMapping(path = "/connexion")
    public Map<String, String> connexion(@RequestBody AuthenticationDTO authenticationDTO) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password())
        );

        if (authenticate.isAuthenticated()) {
            log.info("Authentication successful");
            return this.jwtService.generate(authenticationDTO.email());
        } else {
            throw new RuntimeException("Invalid credentials");
        }

    }

    @GetMapping
    public List<User> allUsers() {
        return  authenticationService.getAllUsers();
    }
}

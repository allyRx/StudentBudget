package org.allyrx.studentbudget.Controllers;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.DepenseRequestDto;
import org.allyrx.studentbudget.Dto.DepenseResponseDto;
import org.allyrx.studentbudget.Services.DepenseService;
import org.allyrx.studentbudget.Services.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/depense")
@AllArgsConstructor
public class DepenseController {

    private final DepenseService depenseService;
    private JwtService jwtService;
    @PostMapping
    public void addDepense(@RequestBody DepenseRequestDto depenseRequest , @RequestHeader("Authorization") String AuthHeader){
        String token = AuthHeader.substring(7);
        String username = jwtService.extractUsername(token);
        depenseService.addDepense(depenseRequest , username);
    }

    @GetMapping
    public List<DepenseResponseDto> getAllDepenses(){
        return  depenseService.getDepense();
    }

    @GetMapping(path = "{id}")
    public Optional<DepenseResponseDto> getDepenseById(@PathVariable Long id){
        return depenseService.getDepeseById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDepenseById(@PathVariable Long id){
        depenseService.deleteDepenseById(id);
    }

    @PutMapping(path = "{id}")
    public void updateDepenseById(@PathVariable Long id, @RequestHeader("Authorization") String AuthHeader,@RequestBody DepenseRequestDto depenseRequest){
        String token = AuthHeader.substring(7);
        String username = jwtService.extractUsername(token);
        depenseService.updateDepense(depenseRequest , id , username);
    }
}

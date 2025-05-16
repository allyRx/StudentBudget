package org.allyrx.studentbudget.Controllers;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.DepenseRequestDto;
import org.allyrx.studentbudget.Services.DepenseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/depense")
@AllArgsConstructor
public class DepenseController {

    private final DepenseService depenseService;

    @PostMapping
    public void addDepense(@RequestBody DepenseRequestDto depenseRequest){
        depenseService.addDepense(depenseRequest);
    }
}

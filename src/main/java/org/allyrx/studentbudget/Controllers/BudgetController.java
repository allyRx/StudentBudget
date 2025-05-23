package org.allyrx.studentbudget.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.BudgetRequestDto;
import org.allyrx.studentbudget.Dto.BudgetResponseDto;
import org.allyrx.studentbudget.Services.BudgetService;
import org.allyrx.studentbudget.Services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('PARENT')")
    public void addBudget(@Valid @RequestBody BudgetRequestDto budgetRequestDto , @RequestHeader("Authorization") String Authheader) {
        String token = Authheader.substring(7);
        String username = jwtService.extractUsername(token);
        budgetService.addBudget(budgetRequestDto , username);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PARENT' , 'STUDENT')")
    @ResponseStatus(HttpStatus.OK)
    public List<BudgetResponseDto> getAllBudgets(){
        return  budgetService.getAllBudgets();
    }


    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('PARENT' , 'STUDENT')")
    @ResponseStatus(HttpStatus.OK)
    public BudgetResponseDto getBudgetById(@PathVariable("id") Long id){
        return  budgetService.getBudgetById(id);
    }


    @PutMapping(path = "{id}")
    @PreAuthorize("hasRole('PARENT')")
    public void updateBudget(@RequestBody BudgetRequestDto budgetRequestDto , @PathVariable("id") Long id){
        budgetService.updateBudget(budgetRequestDto, id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('PARENT')")
    @DeleteMapping(path = "{id}")
    public void deleteBudget(@PathVariable("id") Long id){
        budgetService.deleteBudget(id);
    }

}

package org.allyrx.studentbudget.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.BudgetRequestDto;
import org.allyrx.studentbudget.Dto.BudgetResponseDto;
import org.allyrx.studentbudget.Services.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBudget(@Valid @RequestBody BudgetRequestDto budgetRequestDto){
        budgetService.addBudget(budgetRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BudgetResponseDto> getAllBudgets(){
        return  budgetService.getAllBudgets();
    }


    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public BudgetResponseDto getBudgetById(@PathVariable("id") Long id){
        return  budgetService.getBudgetById(id);
    }


    @PutMapping(path = "{id}")
    public void updateBudget(@RequestBody BudgetRequestDto budgetRequestDto , @PathVariable("id") Long id){
        budgetService.updateBudget(budgetRequestDto, id);
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "{id}")
    public void deleteBudget(@PathVariable("id") Long id){
        budgetService.deleteBudget(id);
    }

}

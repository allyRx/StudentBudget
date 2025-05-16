package org.allyrx.studentbudget.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.BudgetDto;
import org.allyrx.studentbudget.Services.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBudget(@Valid @RequestBody BudgetDto budgetDto){
        budgetService.addBudget(budgetDto);
    }
}

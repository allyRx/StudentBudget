package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.BudgetDto;
import org.allyrx.studentbudget.Entites.Budget;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Repository.AuthenticationRepository;
import org.allyrx.studentbudget.Repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BudgetService {
    private final AuthenticationRepository authenticationRepository;
    private BudgetRepository  budgetRepository;

    public void addBudget(BudgetDto budgetDto){

        //Verifions si l'user est bien la
        Optional <User> user = authenticationRepository.findById(budgetDto.getUserId());
        if(user.isEmpty()){ throw new RuntimeException("User not found"); }

        Budget budget = new Budget();
        budget.setMotif(budgetDto.getMotif());
        budget.setAmount(budgetDto.getAmount());
        budget.setDescription(budgetDto.getDescription());
        budget.setMonth(budgetDto.getMonth());
        budget.setUser(user.get());

        budgetRepository.save(budget);

    }
}

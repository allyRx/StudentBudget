package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.BudgetRequestDto;
import org.allyrx.studentbudget.Dto.BudgetResponseDto;
import org.allyrx.studentbudget.Entites.Budget;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Repository.AuthenticationRepository;
import org.allyrx.studentbudget.Repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BudgetService {
    private final AuthenticationRepository authenticationRepository;
    private BudgetRepository  budgetRepository;

    public void addBudget(BudgetRequestDto budgetRequestDto){

        //Verifions si l'user est bien la
        Optional <User> user = authenticationRepository.findById(budgetRequestDto.getUserId());
        if(user.isEmpty()){ throw new RuntimeException("User not found"); }

        Budget budget = new Budget();
        budget.setMotif(budgetRequestDto.getMotif());
        budget.setAmount(budgetRequestDto.getAmount());
        budget.setDescription(budgetRequestDto.getDescription());
        budget.setMonth(budgetRequestDto.getMonth());
        budget.setUser(user.get());

        budgetRepository.save(budget);

    }


    public List<BudgetResponseDto> getAllBudgets(){
        List<Budget> budgets = budgetRepository.findAll();

        return budgets.stream().map(budget -> {
            User user = budget.getUser(); // L'utilisateur est déjà dans le budget via @ManyToOne

            return new BudgetResponseDto(
                    budget.getId(),
                    budget.getMotif(),
                    budget.getMonth(),
                    budget.getDescription(),
                    budget.getAmount(),
                    user != null ? user.getId() : null,
                    user != null ? user.getUsername() : null,
                    user != null ? user.getEmail() : null
            );
        }).collect(Collectors.toList());
    }



    public BudgetResponseDto getBudgetById(Long id){
       Optional<Budget> budget = budgetRepository.findById(id);
        if(budget.isEmpty()){ throw new RuntimeException("Budget not found"); }

        // On récupère l'utilisateur lié au budget
        User user = budget.get().getUser();
        if (user == null) {
            throw new RuntimeException("User not associated with this budget");
        }
        // Mapping vers DTO
        return new BudgetResponseDto(
                budget.get().getId(),
                budget.get().getMotif(),
                budget.get().getMonth(),
                budget.get().getDescription(),
                budget.get().getAmount(),
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}

package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Dto.DepenseRequestDto;
import org.allyrx.studentbudget.Entites.Budget;
import org.allyrx.studentbudget.Entites.Depense;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Repository.AuthenticationRepository;
import org.allyrx.studentbudget.Repository.BudgetRepository;
import org.allyrx.studentbudget.Repository.DepenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepenseService {

    private DepenseRepository depenseRepository;
    private AuthenticationRepository authenticationRepository;
    private BudgetRepository budgetRepository;

    public void addDepense(DepenseRequestDto depenseRequest){

        //verification de l'user
       Optional<User> user = authenticationRepository.findById(depenseRequest.getUser().getId());
       if (user.isEmpty()) {throw new RuntimeException("User not found with ID: " + depenseRequest.getId());}

       //verification de budget
       Optional<Budget> budget =budgetRepository.findById(depenseRequest.getBudget().getId());
       if (budget.isEmpty()) {throw new RuntimeException("Budget not found with ID: " + depenseRequest.getId());}

       //Les logique pour gerer les depenses et le budget totale
       Long budgetReste = budget.get().getAmount();
       Long DoDepense = depenseRequest.getMontant();

       if (DoDepense > budgetReste){
           throw new RuntimeException("your budget doesn't have enough money for this spent");
       }else if (DoDepense < budgetReste){
           budgetReste -= DoDepense;
           budget.get().setAmount(budgetReste);
           budgetRepository.save(budget.get());

           //creation du depenses
           Depense depense = new Depense();
           LocalDateTime now = LocalDateTime.now();

           depense.setId(depenseRequest.getId());
           depense.setDateSpent(depenseRequest.getDateSpent());
           depense.setDescription(depenseRequest.getDescription());
           depense.setMontant(depenseRequest.getMontant());
           depense.setBudget(budget.get());
           depense.setCategory(depenseRequest.getCategory());
           depense.setUser(user.get());
           depense.setDateSpent(now);

           depenseRepository.save(depense);
       }




    }
}

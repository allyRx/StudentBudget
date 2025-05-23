package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allyrx.studentbudget.Dto.DepenseRequestDto;
import org.allyrx.studentbudget.Dto.DepenseResponseDto;
import org.allyrx.studentbudget.Entites.Budget;
import org.allyrx.studentbudget.Entites.Category;
import org.allyrx.studentbudget.Entites.Depense;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Repository.AuthenticationRepository;
import org.allyrx.studentbudget.Repository.BudgetRepository;
import org.allyrx.studentbudget.Repository.CategoryRepository;
import org.allyrx.studentbudget.Repository.DepenseRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class DepenseService {

    private final CategoryRepository categoryRepository;
    private DepenseRepository depenseRepository;
    private AuthenticationRepository authenticationRepository;
    private BudgetRepository budgetRepository;

    public void addDepense(DepenseRequestDto depenseRequest, String username){

        //verification de l'user
       Optional<User> user = authenticationRepository.findByEmail(username);

        //verification de budget
       Optional<Budget> budget =budgetRepository.findByMotif(depenseRequest.getMotif());
       if (budget.isEmpty()) {throw new RuntimeException("Budget not found ");}

       //verifions le categories
        Optional<Category> category = categoryRepository.findByName(depenseRequest.getNameCategory());
        if (category.isEmpty()) {throw new RuntimeException("Category not found ");}

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
           depense.setDescription(depenseRequest.getDescription());
           depense.setMontant(depenseRequest.getMontant());
           depense.setBudget(budget.get());
           depense.setCategory(category.get());
           user.ifPresent(depense::setUser); //vue qu'user est optinale on a besoin de faire is present pour l'utiliser
           depense.setDateSpent(now);


           depenseRepository.save(depense);
       }

    }

    public List<DepenseResponseDto> getDepense(){
       List  <Depense> allDepense = depenseRepository.findAll();
       return allDepense.stream().map(depense -> {
           return new DepenseResponseDto(
                   depense.getId(),
                   depense.getDescription(),
                   depense.getMontant(),
                   depense.getDateSpent(),
                   depense.getCategory(),
                   depense.getBudget().getId(),
                    depense.getBudget().getAmount(),
                    depense.getUser().getUsername()
           );
       }).collect(Collectors.toList());
    }

    public Optional<DepenseResponseDto> getDepeseById(Long id){
       Optional<Depense> depenses = depenseRepository.findById(id);
       if (depenses.isEmpty()){throw new RuntimeException("Depense not found with ID: " + id);}

       Depense depense = depenses.get();

       DepenseResponseDto depenseResponseDto = new DepenseResponseDto(
               depense.getId(),
               depense.getDescription(),
               depense.getMontant(),
               depense.getDateSpent(),
               depense.getCategory(),
               depense.getBudget().getId(),
               depense.getBudget().getAmount(),
               depense.getUser().getUsername()
       );
       return Optional.of(depenseResponseDto);
    }

    public  void deleteDepenseById(Long id){
        depenseRepository.deleteById(id);
    }

    public void updateDepense(DepenseRequestDto depenseRequest, Long id ,String username){

        //Recuperons le depense ancien par son id
        Optional<Depense> oldDepense = depenseRepository.findById(id);
        if (oldDepense.isEmpty()){throw new RuntimeException("Depense not found with ID: " + id);}
        Depense depense = oldDepense.get();

        //verifions l'user
        Optional<User> user = authenticationRepository.findByEmail(username);
        if (user.isEmpty()){throw new RuntimeException("User not found");}

        //verifions le categories
        Optional<Category> category = categoryRepository.findByName(depenseRequest.getNameCategory());
        if (category.isEmpty()) {throw new RuntimeException("Category not found ");}

        //recuperons le budget pour verifier la disponibilite du budget
        Optional<Budget> budget = budgetRepository.findByMotif(depenseRequest.getMotif());
        if (budget.isEmpty()){throw new RuntimeException("Budget not found");}

        //Les logique pour gerer les depenses et le budget totale
        Long budgetReste = budget.get().getAmount();
        Long DoDepense = depenseRequest.getMontant();

        if (DoDepense > budgetReste){
            throw new RuntimeException("your budget doesn't have enough money for this spent");
        }else if (DoDepense < budgetReste) {

            budgetReste -= DoDepense;
            budget.get().setAmount(budgetReste);
            budgetRepository.save(budget.get());

            depense.setDescription(depenseRequest.getDescription());
            depense.setMontant(depenseRequest.getMontant());
            depense.setBudget(budget.get());
            depense.setCategory(category.get());
            user.ifPresent(depense::setUser); //vue qu'user est optinale on a besoin de faire is present pour l'utiliser
            depense.setDateSpent(LocalDateTime.now());

            depenseRepository.save(depense);

        }

    }
}

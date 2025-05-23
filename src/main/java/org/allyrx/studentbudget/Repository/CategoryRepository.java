package org.allyrx.studentbudget.Repository;

import org.allyrx.studentbudget.Entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}

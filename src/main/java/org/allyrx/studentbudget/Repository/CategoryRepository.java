package org.allyrx.studentbudget.Repository;

import org.allyrx.studentbudget.Entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

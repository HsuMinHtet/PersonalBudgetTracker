package edu.miu.cs489.hsumin.personalbudgettracker.repository;
import edu.miu.cs489.hsumin.personalbudgettracker.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget,Long> {

}

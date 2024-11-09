package edu.miu.cs489.hsumin.personalbudgettracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name="goals")
@Getter
@Setter
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;
    private String title;
    private Double targetAmount;
    private Double currentAmount;
    private LocalDate deadline;
    private Boolean isAchieved;

    @ManyToOne
    @JoinColumn(name="accountHolder_id")
    private AccountHolder accountHolder;

    public Goal(String title, Double targetAmount, Double currentAmount, LocalDate deadline, Boolean isAchieved, AccountHolder accountHolder) {
        this.title = title;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.deadline = deadline;
        this.isAchieved = isAchieved;
        this.accountHolder = accountHolder;
    }
    @Override
    public String toString() {
        return "Goal{" +
                "title='" + title + '\'' +
                ", targetAmount=" + targetAmount +
                ", currentAmount=" + currentAmount +
                ", deadline=" + deadline +
                ", isAchieved=" + isAchieved +
                '}';
    }
}

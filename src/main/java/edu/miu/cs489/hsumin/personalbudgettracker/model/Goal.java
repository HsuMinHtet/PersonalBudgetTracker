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
    private Long goal_Id;
    private String title;
    private Double targetAmount;
    private Double currentAmount;
    private Boolean isAchieved;

    @ManyToOne
    @JoinColumn(name="accountHolder_id")
    private AccountHolder accountHolder;

}

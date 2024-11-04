package edu.miu.cs489.hsumin.personalbudgettracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name="states")
@Getter
@Setter
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer state_id;
    private String name;

    public State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                '}';
    }
}

package ru.ilynoiz.tacocloud.tacos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, message="Name must be at least 5 characters long")
    private String name;

    private Date createdAt;

    @Size(min = 1, message="You must choose at least 1 ingredient")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}

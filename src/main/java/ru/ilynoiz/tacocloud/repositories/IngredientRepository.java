package ru.ilynoiz.tacocloud.repositories;

import ru.ilynoiz.tacocloud.tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}

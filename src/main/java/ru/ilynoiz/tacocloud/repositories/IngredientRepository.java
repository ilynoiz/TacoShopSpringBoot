package ru.ilynoiz.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ilynoiz.tacocloud.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}

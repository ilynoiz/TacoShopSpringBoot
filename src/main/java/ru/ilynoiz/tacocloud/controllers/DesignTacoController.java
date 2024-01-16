package ru.ilynoiz.tacocloud.controllers;

import com.google.common.collect.Lists;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.ilynoiz.tacocloud.Ingredient;
import ru.ilynoiz.tacocloud.Ingredient.Type;
import ru.ilynoiz.tacocloud.Taco;
import ru.ilynoiz.tacocloud.TacoOrder;
import ru.ilynoiz.tacocloud.repositories.IngredientRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 */
@Slf4j  //Adding logger (Simple logging facade for java)
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")     //TacoOrder must be handled on the session level.
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        List<Ingredient> ingredientsList = Lists.newArrayList(ingredients);

        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredientsList, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "/design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}

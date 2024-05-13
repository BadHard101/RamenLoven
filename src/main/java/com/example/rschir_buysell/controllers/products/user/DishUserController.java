package com.example.rschir_buysell.controllers.products.user;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.enums.ProductType;
import com.example.rschir_buysell.models.products.Dish;
import com.example.rschir_buysell.services.products.DishService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dish")
public class DishUserController {
    private final DishService dishService;

    @Operation(summary = "Show dishes", description = "Endpoint for displaying all available dishes")
    @GetMapping("/selling")
    public String showDishes(Model model, Principal principal) {
        List<Dish> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        model.addAttribute("user", dishService.getClientByPrincipal(principal));
        model.addAttribute("types", ProductType.values());
        return "products/dish/dishes";
    }

    @Operation(summary = "Get dish page", description = "Endpoint for displaying a specific dish")
    @GetMapping("/{id}")
    public String getDishPage(
            @PathVariable("id") Long id,
            Principal principal, Model model) {
        Client client = dishService.getClientByPrincipal(principal);
        Dish dish = dishService.getDishById(id);
        model.addAttribute("user", client);
        model.addAttribute("dish", dish);
        return "products/dish/dishPage";
    }
}

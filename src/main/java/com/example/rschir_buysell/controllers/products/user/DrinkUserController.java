package com.example.rschir_buysell.controllers.products.user;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.enums.ProductType;
import com.example.rschir_buysell.models.products.Drink;
import com.example.rschir_buysell.services.products.DrinkService;
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
@RequestMapping("/drink")
public class DrinkUserController {
    private final DrinkService drinkService;

    @Operation(summary = "Show drinks", description = "Endpoint for displaying all available drinks")
    @GetMapping("/selling")
    public String showDrinks(Model model, Principal principal) {
        List<Drink> drinks = drinkService.getAllDrinks();
        model.addAttribute("drinks", drinks);
        model.addAttribute("user", drinkService.getClientByPrincipal(principal));
        model.addAttribute("types", ProductType.values());
        return "products/drink/drinks";
    }

    @Operation(summary = "Get drink page", description = "Endpoint for displaying a specific drink")
    @GetMapping("/{id}")
    public String getDrinkPage(
            @PathVariable("id") Long id,
            Principal principal, Model model) {
        Client client = drinkService.getClientByPrincipal(principal);
        Drink drink = drinkService.getDrinkById(id);
        model.addAttribute("user", client);
        model.addAttribute("drink", drink);
        return "products/drink/drinkPage";
    }
}

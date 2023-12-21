package com.example.rschir_buysell.controllers.products.seller;

import com.example.rschir_buysell.models.products.Dish;
import com.example.rschir_buysell.models.products.Drink;
import com.example.rschir_buysell.models.products.Drink;
import com.example.rschir_buysell.services.products.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/drink")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class DrinkAdminController {
    private final DrinkService drinkService;

    @GetMapping("/create")
    public String createDrinkPage(Model model, Principal principal) {
        model.addAttribute("user", drinkService.getClientByPrincipal(principal));
        return "products/drink/drinkCreator";
    }

    @PostMapping("/create")
    public String createDrink(@RequestParam("file1") MultipartFile file1, Drink drink, Principal principal, Model model) throws IOException {
        String st = drinkService.createDrink(principal, drink, file1);
        if (st.equals("Success")) {
            return "redirect:/drink/selling";
        } else {
            model.addAttribute("user", drinkService.getClientByPrincipal(principal));
            model.addAttribute("errorMessage", st);
            return "products/drink/drinkCreator";
        }
    }

    @GetMapping("/edit/{id}")
    public String editDrinkForm(@PathVariable("id") Long id, Model model, Principal principal) {
        model.addAttribute("user", drinkService.getClientByPrincipal(principal));
        Drink drink = drinkService.getDrinkById(id);
        model.addAttribute("drink", drink);

        double price = drink.getPrice();
        String formattedPrice = Double.toString(price).replace(" ", "");
        model.addAttribute("formattedPrice", formattedPrice);

        return "products/drink/drinkEditor";
    }

    @PostMapping("/edit/{id}")
    public String updateDrink(@RequestParam("file1") MultipartFile file1,
                             @PathVariable("id") Long id,
                             Drink drink,
                             Model model,
                             Principal principal) throws IOException {
        String st = drinkService.updateDrink(id, drink, file1);
        if (st.equals("Success")) {
            return "redirect:/drink/selling";
        } else {
            model.addAttribute("user", drinkService.getClientByPrincipal(principal));
            model.addAttribute("drink", drink);

            Drink orig = drinkService.getDrinkById(id);
            double price = orig.getPrice();
            String formattedPrice = Double.toString(price).replace(" ", "");
            model.addAttribute("formattedPrice", formattedPrice);

            model.addAttribute("errorMessage", st);
            return "products/drink/drinkEditor";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDrink(@PathVariable Long id) {
        drinkService.deleteDrink(id);
        return "redirect:/";
    }

    @GetMapping("/panel")
    public String getDrinkPanel(@RequestParam(name = "name", required = false) String name,
                                @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                               Model model, Principal principal) {
        model.addAttribute("user", drinkService.getClientByPrincipal(principal));
        model.addAttribute("name", name);

        Page<Drink> usersPage = drinkService.getDrinksByName(name, pageable);
        model.addAttribute("drinks", usersPage.getContent());
        model.addAttribute("currentPage", usersPage.getNumber());
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("totalItems", usersPage.getTotalElements());

        return "admin/drinkPanel";
    }
}

package com.example.rschir_buysell.controllers.products.seller;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.products.Dish;
import com.example.rschir_buysell.services.products.DishService;
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
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/dish")
public class DishAdminController {
    private final DishService dishService;

    @GetMapping("/create")
    public String createDishPage(Model model, Principal principal) {
        model.addAttribute("user", dishService.getClientByPrincipal(principal));
        return "products/dish/dishCreator";
    }

    @PostMapping("/create")
    public String createDish(@RequestParam("file1") MultipartFile file1, Dish dish, Principal principal, Model model) throws IOException {
        String st = dishService.createDish(principal, dish, file1);
        if (st.equals("Success")) {
            return "redirect:/dish/selling";
        } else {
            model.addAttribute("user", dishService.getClientByPrincipal(principal));
            model.addAttribute("errorMessage", st);
            return "products/dish/dishCreator";
        }
    }

    @GetMapping("/edit/{id}")
    public String editDishForm(@PathVariable("id") Long id, Model model, Principal principal) {
        model.addAttribute("user", dishService.getClientByPrincipal(principal));
        Dish dish = dishService.getDishById(id);
        model.addAttribute("dish", dish);

        double price = dish.getPrice();
        String formattedPrice = Double.toString(price).replace(" ", "");
        System.out.println(formattedPrice);
        model.addAttribute("formattedPrice", formattedPrice);

        return "products/dish/dishEditor";
    }

    @PostMapping("/edit/{id}")
    public String updateDish(@RequestParam("file1") MultipartFile file1,
                             @PathVariable("id") Long id,
                             Dish dish,
                             Model model,
                             Principal principal) throws IOException {
        String st = dishService.updateDish(id, dish, file1);
        if (st.equals("Success")) {
            return "redirect:/dish/selling";
        } else {
            model.addAttribute("user", dishService.getClientByPrincipal(principal));
            model.addAttribute("dish", dish);

            Dish orig = dishService.getDishById(id);
            double price = orig.getPrice();
            String formattedPrice = Double.toString(price).replace(" ", "");
            model.addAttribute("formattedPrice", formattedPrice);

            model.addAttribute("errorMessage", st);
            return "products/dish/dishEditor";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/";
    }

    @GetMapping("/panel")
    public String getDishPanel(@RequestParam(name = "name", required = false) String name,
                               @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                               Model model, Principal principal) {
        model.addAttribute("user", dishService.getClientByPrincipal(principal));
        model.addAttribute("name", name);

        Page<Dish> usersPage = dishService.getDishesByName(name, pageable);
        model.addAttribute("dishes", usersPage.getContent());
        model.addAttribute("currentPage", usersPage.getNumber());
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("totalItems", usersPage.getTotalElements());

        return "admin/dishPanel";
    }
}

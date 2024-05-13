package com.example.rschir_buysell.controllers.products.user;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.ShoppingCart;
import com.example.rschir_buysell.models.products.Product;
import com.example.rschir_buysell.services.products.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductUserController {

    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Add product to cart", description = "Endpoint for adding a product to the shopping cart")
    @GetMapping("/addToCart/{id}")
    public String addProductToCart(@PathVariable Long id, @AuthenticationPrincipal Client client, Model model) {
        String st = shoppingCartService.addProductToCart(id, client);
        if (!st.equals("Success")) model.addAttribute("errorMessage", st);
        return "redirect:/product/shoppingCart";
    }

    @Operation(summary = "Show shopping cart", description = "Endpoint for displaying the shopping cart")
    @GetMapping("/shoppingCart")
    public String showShoppingCart(@AuthenticationPrincipal Client client, Model model) {
        ShoppingCart cart = shoppingCartService.getOrCreateShoppingCartByClient(client);
        Map<Product, Integer> items = cart.getItems();
        List<Map.Entry<Product, Integer>> sortedItems = new ArrayList<>(items.entrySet());
        sortedItems.sort(Comparator.comparing(entry -> entry.getKey().getPrice()));

        model.addAttribute("user", client);
        model.addAttribute("items", sortedItems);
        model.addAttribute("cart", cart);

        return "user/shoppingCart";
    }

    @Operation(summary = "Add item to shopping cart", description = "Endpoint for adding an item to the shopping cart")
    @GetMapping("/shoppingCart/addItem/{id}")
    public String addItemToShoppingCart(@PathVariable Long id, @AuthenticationPrincipal Client client, Model model) {
        String st = shoppingCartService.addProductToCart(id, client);
        if (!st.equals("Success")) model.addAttribute("errorMessage", st);

        ShoppingCart cart = shoppingCartService.getOrCreateShoppingCartByClient(client);
        Map<Product, Integer> items = cart.getItems();
        List<Map.Entry<Product, Integer>> sortedItems = new ArrayList<>(items.entrySet());
        sortedItems.sort(Comparator.comparing(entry -> entry.getKey().getPrice()));

        model.addAttribute("user", client);
        model.addAttribute("items", sortedItems);
        model.addAttribute("cart", cart);
        return "user/shoppingCart";
    }

    @Operation(summary = "Remove item from shopping cart", description = "Endpoint for removing an item from the shopping cart")
    @GetMapping("/shoppingCart/removeItem/{id}")
    public String removeItemFromShoppingCart(@PathVariable Long id, @AuthenticationPrincipal Client client, Model model) {
        shoppingCartService.removeProductFromCart(id, client);
        return "redirect:/product/shoppingCart";
    }

    @Operation(summary = "Checkout shopping cart", description = "Endpoint for checking out the shopping cart")
    @GetMapping("/shoppingCart/checkout")
    public String checkoutShoppingCart(@RequestParam(name = "address", required = true) String address,
                                       @AuthenticationPrincipal Client client, Model model) {
        String st = shoppingCartService.checkoutShoppingCart(client, address);
        if (!st.equals("Success")) model.addAttribute("errorMessage", st);
        else {
            model.addAttribute("successLabel", "Вы успешно оформили заказ!");
            model.addAttribute("user", client);
            return "user/orderPage";
        }

        ShoppingCart cart = shoppingCartService.getOrCreateShoppingCartByClient(client);
        Map<Product, Integer> items = cart.getItems();
        List<Map.Entry<Product, Integer>> sortedItems = new ArrayList<>(items.entrySet());
        sortedItems.sort(Comparator.comparing(entry -> entry.getKey().getPrice()));

        model.addAttribute("user", client);
        model.addAttribute("items", sortedItems);
        model.addAttribute("cart", cart);
        return "user/shoppingCart";
    }
}

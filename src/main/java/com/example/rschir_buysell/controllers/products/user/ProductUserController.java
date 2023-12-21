package com.example.rschir_buysell.controllers.products.user;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.ShoppingCart;
import com.example.rschir_buysell.models.products.Product;
import com.example.rschir_buysell.services.products.ShoppingCartService;
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

    @GetMapping("/addToCart/{id}")
    public String addProductToCart(@PathVariable Long id, @AuthenticationPrincipal Client client, Model model) {
        String st = shoppingCartService.addProductToCart(id, client);
        if (!st.equals("Success")) model.addAttribute("errorMessage", st);
        /*String original = productService.getProductById(id).getProductType().toString();
        String modified = original.substring(0, 1).toLowerCase() + original.substring(1);
        return "redirect:/" + modified + "/selling";*/
        return "redirect:/product/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String showShoppingCart(@AuthenticationPrincipal Client client, Model model) {
        ShoppingCart cart = shoppingCartService.getOrCreateShoppingCartByClient(client);
        Map<Product, Integer> items = cart.getItems();
        // Преобразование Map в список для сортировки
        List<Map.Entry<Product, Integer>> sortedItems = new ArrayList<>(items.entrySet());
        // Сортировка списка. Пример: сортировка по имени продукта
        sortedItems.sort(Comparator.comparing(entry -> entry.getKey().getPrice()));

        // Теперь, когда у вас есть отсортированный список, добавьте его в модель
        model.addAttribute("user", client);
        model.addAttribute("items", sortedItems); // Используйте отсортированный список здесь
        model.addAttribute("cart", cart); // Используйте отсортированный список здесь

        return "user/shoppingCart";
    }


    @GetMapping("/shoppingCart/addItem/{id}")
    public String addItemToShoppingCart(@PathVariable Long id, @AuthenticationPrincipal Client client, Model model) {
        String st = shoppingCartService.addProductToCart(id, client);
        if (!st.equals("Success")) model.addAttribute("errorMessage", st);

        ShoppingCart cart = shoppingCartService.getOrCreateShoppingCartByClient(client);
        Map<Product, Integer> items = cart.getItems();
        // Преобразование Map в список для сортировки
        List<Map.Entry<Product, Integer>> sortedItems = new ArrayList<>(items.entrySet());
        // Сортировка списка. Пример: сортировка по имени продукта
        sortedItems.sort(Comparator.comparing(entry -> entry.getKey().getPrice()));

        // Теперь, когда у вас есть отсортированный список, добавьте его в модель
        model.addAttribute("user", client);
        model.addAttribute("items", sortedItems); // Используйте отсортированный список здесь
        model.addAttribute("cart", cart); // Используйте отсортированный список здесь
        return "user/shoppingCart";
    }

    @GetMapping("/shoppingCart/removeItem/{id}")
    public String removeItemFromShoppingCart(@PathVariable Long id, @AuthenticationPrincipal Client client, Model model) {
        shoppingCartService.removeProductFromCart(id, client);
        return "redirect:/product/shoppingCart";
    }

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
        // Преобразование Map в список для сортировки
        List<Map.Entry<Product, Integer>> sortedItems = new ArrayList<>(items.entrySet());
        // Сортировка списка. Пример: сортировка по имени продукта
        sortedItems.sort(Comparator.comparing(entry -> entry.getKey().getPrice()));

        // Теперь, когда у вас есть отсортированный список, добавьте его в модель
        model.addAttribute("user", client);
        model.addAttribute("items", sortedItems); // Используйте отсортированный список здесь
        model.addAttribute("cart", cart); // Используйте отсортированный список здесь

        return "user/shoppingCart";
    }
}

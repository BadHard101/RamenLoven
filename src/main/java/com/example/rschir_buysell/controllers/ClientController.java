package com.example.rschir_buysell.controllers;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.products.Product;
import com.example.rschir_buysell.models.enums.ProductType;
import com.example.rschir_buysell.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @Operation(summary = "Login page", description = "Endpoint for displaying login page")
    @GetMapping("/login")
    public String login() {
        return "authorization/login";
    }

    @Operation(summary = "Login error page", description = "Endpoint for displaying login error page")
    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", "Введены неверные данные");
        return "authorization/login";
    }

    @Operation(summary = "Registration page", description = "Endpoint for displaying registration page")
    @GetMapping("/registration")
    public String registration() {
        return "authorization/registration";
    }

    @Operation(summary = "Create user", description = "Endpoint for creating a new user")
    @PostMapping("/registration")
    public String createUser(
            @Parameter(description = "User object to be created", required = true) Client client,
            Model model) {
        if (!clientService.createClient(client)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + client.getEmail() + " уже существует");
            return "authorization/registration";
        }
        return "redirect:/login";
    }

    @Operation(summary = "Account page", description = "Endpoint for displaying user account page")
    @GetMapping("/account")
    public String account(
            @Parameter(description = "Authenticated client", required = true) @AuthenticationPrincipal Client client,
            Model model) {
        model.addAttribute("user", client);
        return "user/account";
    }

    @Operation(summary = "Main page", description = "Endpoint for displaying main page with products")
    @GetMapping("")
    public String categories(Model model, Principal principal) {
        List<Product> products = clientService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("user", clientService.getClientByPrincipal(principal));
        model.addAttribute("types", ProductType.values());
        return "user/main";
    }
}
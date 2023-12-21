package com.example.rschir_buysell.controllers;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.enums.Role;
import com.example.rschir_buysell.models.products.Product;
import com.example.rschir_buysell.repositories.ClientRepository;
import com.example.rschir_buysell.repositories.products.ProductRepository;
import com.example.rschir_buysell.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/panel")
    public String showPanel(@AuthenticationPrincipal Client client, Model model) {
        model.addAttribute("user", client);
        return "admin/panel";
    }

    @GetMapping("/user/{id}")
    public String showUserInfo(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal Client user) {
        Client client = adminService.getClientById(id);

        model.addAttribute("user", user);
        model.addAttribute("client", client);
        return "user/userPage";
    }

    @GetMapping("/users")
    public String showUsers(@RequestParam(name = "email", required = false) String email,
                            Model model,
                            Principal principal) {
        model.addAttribute("users", adminService.findAllClients(email));
        model.addAttribute("user", adminService.getClientByPrincipal(principal));
        return "admin/usersPanel";
    }

    @PostMapping("/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        adminService.banUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/edit/{user}")
    public String userEdit(@PathVariable("user") Client user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/userEdit";
    }

    @PostMapping("/user/edit")
    public String userEdit(@RequestParam("userId") Client user, @RequestParam("userRole") String role) {
        adminService.changeUserRole(user, role);
        return "redirect:/admin/users";
    }
}

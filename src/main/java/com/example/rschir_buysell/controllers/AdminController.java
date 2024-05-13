package com.example.rschir_buysell.controllers;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.ShoppingCart;
import com.example.rschir_buysell.models.enums.Role;
import com.example.rschir_buysell.models.enums.Status;
import com.example.rschir_buysell.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "Show admin panel", description = "Endpoint for displaying admin panel")
    @GetMapping("/panel")
    public String showPanel(
            @Parameter(description = "Authenticated admin", required = true) @AuthenticationPrincipal Client client,
            Model model) {
        model.addAttribute("user", client);
        return "admin/panel";
    }

    @Operation(summary = "Show user info", description = "Endpoint for displaying user information")
    @GetMapping("/user/{id}")
    public String showUserInfo(
            @Parameter(description = "User ID", required = true) @PathVariable("id") Long id,
            Model model,
            @AuthenticationPrincipal Client user) {
        Client client = adminService.getClientById(id);

        model.addAttribute("user", user);
        model.addAttribute("client", client);
        model.addAttribute("orders", adminService.getHistory(client));
        return "user/userPage";
    }

    @Operation(summary = "Show users panel", description = "Endpoint for displaying users panel")
    @GetMapping("/users")
    public String getUsers(
            @Parameter(description = "User email (optional)") @RequestParam(name = "email", required = false) String email,
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model, Principal principal) {
        model.addAttribute("user", adminService.getClientByPrincipal(principal));
        model.addAttribute("email", email);

        Page<Client> usersPage = adminService.getUsersByEmail(email, pageable);
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentPage", usersPage.getNumber());
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("totalItems", usersPage.getTotalElements());

        return "admin/usersPanel";
    }

    @Operation(summary = "Show orders panel", description = "Endpoint for displaying orders panel")
    @GetMapping("/orders")
    public String getOrders(
            @Parameter(description = "Order address (optional)") @RequestParam(name = "address", required = false) String address,
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model, Principal principal) {
        model.addAttribute("user", adminService.getClientByPrincipal(principal));
        model.addAttribute("address", address);

        Page<ShoppingCart> ordersPage = adminService.getOrdersByAddress(address, pageable);
        model.addAttribute("orders", ordersPage.getContent());
        model.addAttribute("currentPage", ordersPage.getNumber());
        model.addAttribute("totalPages", ordersPage.getTotalPages());
        model.addAttribute("totalItems", ordersPage.getTotalElements());

        return "admin/ordersPanel";
    }

    @Operation(summary = "Show order details", description = "Endpoint for displaying details of a specific order")
    @GetMapping("/order/{id}")
    public String showOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id,
            @AuthenticationPrincipal Client client, Model model) {
        model.addAttribute("user", client);
        model.addAttribute("order", adminService.getOrderById(id));

        return "admin/orderPage";
    }

    @Operation(summary = "Ban user", description = "Endpoint for banning a user")
    @PostMapping("/user/ban/{id}")
    public String userBan(
            @Parameter(description = "User ID", required = true) @PathVariable("id") Long id) {
        adminService.banUser(id);
        return "redirect:/admin/users";
    }

    @Operation(summary = "Edit user", description = "Endpoint for editing user information")
    @GetMapping("/user/edit/{user}")
    public String userEdit(
            @Parameter(description = "User to edit", required = true) @PathVariable("user") Client user,
            Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/userEdit";
    }

    @Operation(summary = "Save edited user", description = "Endpoint for saving edited user information")
    @PostMapping("/user/edit")
    public String userEdit(
            @Parameter(description = "User ID", required = true) @RequestParam("userId") Client user,
            @Parameter(description = "New role", required = true) @RequestParam("userRole") String role) {
        adminService.changeUserRole(user, role);
        return "redirect:/admin/users";
    }

    @Operation(summary = "Edit order status", description = "Endpoint for editing order status")
    @GetMapping("/order/edit/{id}")
    public String orderEditPage(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id, Model model,
            @AuthenticationPrincipal Client client) {
        model.addAttribute("user", client);
        model.addAttribute("status", Status.values());
        model.addAttribute("order", adminService.getOrderById(id));
        return "admin/orderStatusEdit";
    }

    @Operation(summary = "Save edited order status", description = "Endpoint for saving edited order status")
    @PostMapping("/order/edit/{id}")
    public String orderEdit(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id,
            @Parameter(description = "New status", required = true) @RequestParam("orderStatus") String status) {
        adminService.changeOrderStatus(id, status);
        return "redirect:/admin/orders";
    }
}

package com.example.rschir_buysell.controllers;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.ShoppingCart;
import com.example.rschir_buysell.models.enums.Role;
import com.example.rschir_buysell.models.enums.Status;
import com.example.rschir_buysell.models.products.Product;
import com.example.rschir_buysell.repositories.ClientRepository;
import com.example.rschir_buysell.repositories.products.ProductRepository;
import com.example.rschir_buysell.services.AdminService;
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
        model.addAttribute("orders", adminService.getHistory(client));
        return "user/userPage";
    }

    /*@GetMapping("/users")
    public String showUsers(@RequestParam(name = "email", required = false) String email,
                            Model model,
                            Principal principal) {
        model.addAttribute("users", adminService.findAllClients(email));
        model.addAttribute("user", adminService.getClientByPrincipal(principal));
        return "admin/usersPanel";
    }*/

    @GetMapping("/users")
    public String getUsers(@RequestParam(name = "email", required = false) String email,
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

    @GetMapping("/orders")
    public String getOrders(@RequestParam(name = "address", required = false) String address,
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

    @GetMapping("/order/{id}")
    public String showPanel(@PathVariable("id") Long id, @AuthenticationPrincipal Client client, Model model) {
        model.addAttribute("user", client);
        model.addAttribute("order", adminService.getOrderById(id));

        return "admin/orderPage";
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

    @GetMapping("/order/edit/{id}")
    public String orderEditPage(@PathVariable("id") Long id, Model model,
                                @AuthenticationPrincipal Client client) {
        model.addAttribute("user", client);
        model.addAttribute("status", Status.values());
        model.addAttribute("order", adminService.getOrderById(id));
        return "admin/orderStatusEdit";
    }

    @PostMapping("/order/edit/{id}")
    public String orderEdit(@PathVariable("id") Long id, @RequestParam("orderStatus") String status) {
        adminService.changeOrderStatus(id, status);
        return "redirect:/admin/orders";
    }

}

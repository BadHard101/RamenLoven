package com.example.rschir_buysell.controllers.products.cooking;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(summary = "Show employee panel", description = "Endpoint for displaying employee panel")
    @GetMapping("/panel")
    public String showPanel(
            @Parameter(description = "Authenticated employee", required = true) @AuthenticationPrincipal Client client,
            Model model) {
        model.addAttribute("user", client);
        model.addAttribute("orders", employeeService.findAllCreatedOrders());
        return "employee/employeePanel";
    }

    @Operation(summary = "Accept order", description = "Endpoint for accepting an order")
    @GetMapping("/order/accept/{id}")
    public String acceptOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id,
            @AuthenticationPrincipal Client client) {
        employeeService.acceptOrder(id, client);
        return "redirect:/employee/panel";
    }

    @Operation(summary = "Mark order as cooked", description = "Endpoint for marking an order as cooked")
    @GetMapping("/order/cooked/{id}")
    public String cookedOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id) {
        employeeService.cookedOrder(id);
        return "redirect:/employee/panel";
    }

    @Operation(summary = "Cancel order", description = "Endpoint for cancelling an order")
    @GetMapping("/order/cancel/{id}")
    public String cancelOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id) {
        employeeService.cancelOrder(id);
        return "redirect:/employee/panel";
    }
}

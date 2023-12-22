package com.example.rschir_buysell.controllers.products.cooking;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    @GetMapping("/panel")
    public String showPanel(@AuthenticationPrincipal Client client, Model model) {
        model.addAttribute("user", client);
        model.addAttribute("orders", employeeService.findAllCreatedOrders());

        return "employee/employeePanel";
    }

    @GetMapping ("/order/accept/{id}")
    public String acceptOrder(@PathVariable("id") Long id, @AuthenticationPrincipal Client client) {
        employeeService.acceptOrder(id, client);
        return "redirect:/employee/panel";
    }

    @GetMapping("/order/cooked/{id}")
    public String cookedOrder(@PathVariable("id") Long id) {
        employeeService.cookedOrder(id);
        return "redirect:/employee/panel";
    }

    @GetMapping("/order/cancel/{id}")
    public String cancelOrder(@PathVariable("id") Long id) {
        employeeService.cancelOrder(id);
        return "redirect:/employee/panel";
    }

}

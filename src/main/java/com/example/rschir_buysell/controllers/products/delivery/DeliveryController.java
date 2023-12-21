package com.example.rschir_buysell.controllers.products.delivery;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.services.DeliveryService;
import com.example.rschir_buysell.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_DELIVERYMAN')")
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;


    @GetMapping("/panel")
    public String showPanel(@AuthenticationPrincipal Client client, Model model) {
        model.addAttribute("user", client);
        model.addAttribute("orders", deliveryService.findAllCookedOrders());

        return "delivery/deliveryPanel";
    }

    @GetMapping ("/order/delivering/{id}")
    public String deliveringOrder(@PathVariable("id") Long id, @AuthenticationPrincipal Client client) {
        deliveryService.deliveringOrder(id, client);
        return "redirect:/delivery/panel";
    }

    @GetMapping("/order/complete/{id}")
    public String completeOrder(@PathVariable("id") Long id) {
        deliveryService.completeOrder(id);
        return "redirect:/delivery/panel";
    }

    @GetMapping("/order/cancel/{id}")
    public String cancelOrder(@PathVariable("id") Long id) {
        deliveryService.cancelOrder(id);
        return "redirect:/delivery/panel";
    }

}

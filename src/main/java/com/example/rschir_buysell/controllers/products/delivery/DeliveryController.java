package com.example.rschir_buysell.controllers.products.delivery;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.services.DeliveryService;
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
@PreAuthorize("hasAuthority('ROLE_DELIVERYMAN')")
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Operation(summary = "Show delivery panel", description = "Endpoint for displaying delivery panel")
    @GetMapping("/panel")
    public String showPanel(
            @Parameter(description = "Authenticated deliveryman", required = true) @AuthenticationPrincipal Client client,
            Model model) {
        model.addAttribute("user", client);
        model.addAttribute("orders", deliveryService.findAllCookedOrders());
        return "delivery/deliveryPanel";
    }

    @Operation(summary = "Mark order as delivering", description = "Endpoint for marking an order as delivering")
    @GetMapping("/order/delivering/{id}")
    public String deliveringOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id,
            @AuthenticationPrincipal Client client) {
        deliveryService.deliveringOrder(id, client);
        return "redirect:/delivery/panel";
    }

    @Operation(summary = "Mark order as complete", description = "Endpoint for marking an order as complete")
    @GetMapping("/order/complete/{id}")
    public String completeOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id) {
        deliveryService.completeOrder(id);
        return "redirect:/delivery/panel";
    }

    @Operation(summary = "Cancel order", description = "Endpoint for cancelling an order")
    @GetMapping("/order/cancel/{id}")
    public String cancelOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable("id") Long id) {
        deliveryService.cancelOrder(id);
        return "redirect:/delivery/panel";
    }
}

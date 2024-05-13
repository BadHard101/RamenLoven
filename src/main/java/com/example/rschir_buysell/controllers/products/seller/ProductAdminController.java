package com.example.rschir_buysell.controllers.products.seller;

import com.example.rschir_buysell.models.enums.ProductType;
import com.example.rschir_buysell.models.products.Product;
import com.example.rschir_buysell.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ProductAdminController {
    private final AdminService adminService;

    @Operation(summary = "Product type selection", description = "Endpoint for selecting product type")
    @GetMapping("/typeSelect")
    public String productTypeSelect(Model model, Principal principal) {
        List<Product> products = adminService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("user", adminService.getClientByPrincipal(principal));
        model.addAttribute("types", ProductType.values());
        return "/products/selling/productTypeSelect";
    }

    @Operation(summary = "Delete product", description = "Endpoint for deleting a product")
    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id) {
        adminService.deleteProduct(id);
        return "redirect:/";
    }
}

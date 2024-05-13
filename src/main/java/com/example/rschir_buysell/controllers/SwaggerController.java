package com.example.rschir_buysell.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/swagger")
public class SwaggerController {
    @GetMapping("")
    public void root(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
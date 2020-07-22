package com.wcs.hellfestHistoric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/band")
    public String adminBand() {

        return "admin_band";
    }

    @GetMapping("/admin/concert")
    public String adminConcert() {

        return "admin_concert";
    }
}

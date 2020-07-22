package com.wcs.hellfestHistoric.controller;

import com.wcs.hellfestHistoric.entity.Band;
import com.wcs.hellfestHistoric.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BandController {

    @Autowired
    private BandRepository bandRepository;

    @PostMapping("/home/search")
    public String bandSearch(Model model, @RequestParam String name) {


        List<Band> bands = bandRepository.findAllByName(name);

        model.addAttribute("bands", bandRepository.findAll());

        return "result";
    }

    @GetMapping("/band")
    public String band() {

        return "band";
    }

    @GetMapping("/result")
    public String result() {

        return "result";
    }
}

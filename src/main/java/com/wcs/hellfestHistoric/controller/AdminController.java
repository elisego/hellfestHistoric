package com.wcs.hellfestHistoric.controller;

import com.wcs.hellfestHistoric.entity.Band;
import com.wcs.hellfestHistoric.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class AdminController {

    @Autowired
    private BandRepository bandRepository;


    @GetMapping("/admin/band")
    public String adminBand(Model model,
                            @RequestParam(required = false) String name) {

        Optional<Band> optionalBand = bandRepository.findByName(name);

        Band band = new Band();

        if(optionalBand.isPresent()){
            band = optionalBand.get();
        } band.setName(name);

        model.addAttribute("band", band);
        return "admin_band";
    }

    @PostMapping("/admin/band")
    public String postBand(@ModelAttribute Band band) {

        bandRepository.save(band);

        return "redirect:/admin/band";
    }

//    @PostMapping("/admin/band/search")
//    public String bandSearch(Model model, @RequestParam String name) {
//
//        Band band = new Band();
//        Optional<Band> optionalBand = bandRepository.findByName(name);
//        if (optionalBand.isPresent()) {
//            band = optionalBand.get();
//        }
//        model.addAttribute("band", band);
//
//        return "admin_band";
//    }


    @GetMapping("/admin/concert")
    public String adminConcert() {

        return "admin_concert";
    }

}

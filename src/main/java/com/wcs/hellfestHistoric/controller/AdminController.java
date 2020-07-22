package com.wcs.hellfestHistoric.controller;

import com.wcs.hellfestHistoric.entity.Band;
import com.wcs.hellfestHistoric.entity.Concert;
import com.wcs.hellfestHistoric.repository.BandRepository;
import com.wcs.hellfestHistoric.repository.ConcertRepository;
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

    @Autowired
    private ConcertRepository concertRepository;

    // pour afficher les info du groupe après la recherche
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

    // pour enregister le groupe
    @PostMapping("/admin/band")
    public String postBand(@ModelAttribute Band band) {

        bandRepository.save(band);

        return "redirect:/admin/band";
    }

    // rechercher un groupe par le nom exact
    @PostMapping("/admin/band/search")
    public String bandSearch(Model model, @RequestParam String name) {

        Band band = new Band();
        Optional<Band> optionalBand = bandRepository.findByName(name);
        if (optionalBand.isPresent()) {
            band = optionalBand.get();
        }
        model.addAttribute("band", band);

        return "admin_band";
    }

    // pour afficher les infos d'un concert
    @GetMapping("/admin/concert")
    public String adminConcert(Model model,
                            @RequestParam(required = false) Long id) {

       Concert concert = new Concert();

        if (id != null) {
            Optional<Concert> optionalConcert = concertRepository.findById(id);
            if (optionalConcert.isPresent()) {
                concert = optionalConcert.get();
            }
        }

        model.addAttribute("concert", concert);
        model.addAttribute("bands", bandRepository.findAll());
        return "admin_concert";
    }

    // pour enregister le concert
    @PostMapping("/admin/concert")
    public String postConcert(@ModelAttribute Concert concert,
                              @RequestParam(required = true) Long band) {

        Optional<Band> optionalBand = bandRepository.findById(band);
        if (optionalBand.isPresent()){
            concert.setBand(optionalBand.get());
        }

        concertRepository.save(concert);

        return "redirect:/admin/concert";
    }
}


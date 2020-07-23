package com.wcs.hellfestHistoric.controller;

import com.wcs.hellfestHistoric.entity.Band;
import com.wcs.hellfestHistoric.repository.BandRepository;
import com.wcs.hellfestHistoric.repository.ConcertRepository;
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

    @Autowired
    private ConcertRepository concertRepository;

    @GetMapping("/results")
    public String results(Model model,
                             @RequestParam String search) {

        List<Long> bandsList = bandRepository.finAllBySearchName(search);

        List<Band> bands = bandRepository.findAllById(bandsList);

        model.addAttribute("bands", bands);

        return "result";
    }

    @GetMapping("/band")
    public String bandById(@RequestParam Long id, Model model ) {

        Band band = bandRepository.findById(id).get();

        model.addAttribute("band", band);
        model.addAttribute("concerts", concertRepository.findConcertByBandId(id));

        return "band";
    }
}

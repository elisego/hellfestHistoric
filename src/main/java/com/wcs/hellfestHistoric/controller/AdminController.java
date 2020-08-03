package com.wcs.hellfestHistoric.controller;

import com.wcs.hellfestHistoric.entity.Band;
import com.wcs.hellfestHistoric.entity.Concert;
import com.wcs.hellfestHistoric.entity.Role;
import com.wcs.hellfestHistoric.entity.User;
import com.wcs.hellfestHistoric.repository.BandRepository;
import com.wcs.hellfestHistoric.repository.ConcertRepository;
import com.wcs.hellfestHistoric.repository.RoleRepository;
import com.wcs.hellfestHistoric.repository.UserRepository;
import com.wcs.hellfestHistoric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class AdminController {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String getLogIn() {

        return "admin_login";
    }

    @GetMapping("/admin/admin")
    public String adminAdmin(Model model) {

        User user = userService.getLoggedUsername();

        List<User> userList = userRepository.findAll();

        List<Role> roles = roleRepository.findAll();

        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userList);
        model.addAttribute("roles", roles);
        return "admin_admin";
    }

    @GetMapping("/admin/profile")
    public String adminProfile(Model model) {
        User user = userService.getLoggedUsername();
        model.addAttribute("user", user);
        return "admin_profile";
    }

    // pour afficher les info du groupe
    @GetMapping("/admin/band")
    public String adminBand(Model model,
                            @RequestParam(required = false) String name) {

        Optional<Band> optionalBand = bandRepository.findByName(name);

        Band band = new Band();

        if (optionalBand.isPresent()) {
            band = optionalBand.get();
        }
        band.setName(name);

        User user = userService.getLoggedUsername();

        model.addAttribute("user", user);
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

        User user = userService.getLoggedUsername();

        Band band = new Band();
        Optional<Band> optionalBand = bandRepository.findByName(name);
        if (optionalBand.isPresent()) {
            band = optionalBand.get();
        }
        model.addAttribute("band", band);
        model.addAttribute("user", user);

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
        User user = userService.getLoggedUsername();
        model.addAttribute("user", user);
        model.addAttribute("concert", concert);
        model.addAttribute("bands", bandRepository.findAll());
        return "admin_concert";
    }

    // pour enregister le concert
    @PostMapping("/admin/concert")
    public String postConcert(@ModelAttribute Concert concert, Model model){

        User user = userService.getLoggedUsername();
        model.addAttribute("user", user);
        concertRepository.save(concert);

        return "redirect:/admin/concert";
    }
}

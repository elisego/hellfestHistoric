package com.wcs.hellfestHistoric.controller;

import com.wcs.hellfestHistoric.entity.Role;
import com.wcs.hellfestHistoric.entity.User;
import com.wcs.hellfestHistoric.repository.RoleRepository;
import com.wcs.hellfestHistoric.repository.UserRepository;
import com.wcs.hellfestHistoric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){

        return "home";
    }

    @GetMapping("/init")
    @ResponseBody
    public User init() {

        if (!roleRepository.findByName("ROLE_USER").isPresent()) {
            roleRepository.save(new Role("ROLE_USER"));
        }
        if (!roleRepository.findByName("ROLE_ADMIN").isPresent()) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        Optional<Role> optionalRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User("Admin", passwordEncoder.encode("Hellfest666"));
        if (optionalRole.isPresent()) {
            user.setRole(optionalRole.get());
        }
        return userRepository.save(user);
    }


}

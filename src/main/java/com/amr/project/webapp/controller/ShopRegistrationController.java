package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/shop/registration")
public class ShopRegistrationController {

    private ShopMapper shopMapper;
    private ShopService shopService;
    private UserService userService;

    @Autowired
    public ShopRegistrationController(ShopMapper shopMapper, ShopService shopService, UserService userService) {
        this.shopMapper = shopMapper;
        this.shopService = shopService;
        this.userService = userService;
    }

    @GetMapping()
    public String showRegistrationPage(Model model, Principal principal) {
        model.addAttribute("shopToRegister", new ShopDto());
        User user = null;
        if (principal != null) {
            user = userService.findByUsername(principal.getName());
            // Замена первой буквы имени на заглавную для корректного отображения в приветствии на фронте.
            user.setUsername(user.getUsername().substring(0, 1).toUpperCase() + user.getUsername().substring(1));
        }
        model.addAttribute("activeUser", user);
        return "registrationPage";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("attribute") ShopDto shopDto) {
        Shop newShop = shopMapper.toModel(shopDto);
        newShop.setModerated(false);
        newShop.setModerateAccept(false);
        shopService.persist(newShop);

        return "redirect:/shop/registration";
    }
}


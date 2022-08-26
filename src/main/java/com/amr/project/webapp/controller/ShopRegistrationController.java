package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CountryService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.amr.project.service.abstracts.CityService;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/shop/registration")
public class ShopRegistrationController {

    private ShopMapper shopMapper;
    private ShopService shopService;
    private UserService userService;
    private CityService cityService;
    private CountryService countryService;

    @Autowired
    public ShopRegistrationController(ShopMapper shopMapper, CountryService countryService, CityService cityService, ShopService shopService, UserService userService) {
        this.shopMapper = shopMapper;
        this.shopService = shopService;
        this.userService = userService;
        this.cityService = cityService;
        this.countryService = countryService;

    }

    @GetMapping()
    public String showRegistrationPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("authUser", user);
        List <City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        List <Country> countries = countryService.findAll();
        model.addAttribute("countries", countries);
        return "shopRegistrationPage";
    }

}


package ua.lviv.lgs.periodicals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.lviv.lgs.periodicals.dtos.UserRegistrationRequest;
import ua.lviv.lgs.periodicals.services.UserService;

@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public String register(@ModelAttribute UserRegistrationRequest userDto){
    userService.register(userDto);
    return "redirect:/login";
  }

  @GetMapping("/registration")
  public String getRegisterPage(Model model){
    model.addAttribute("userDto", new UserRegistrationRequest());
    return "registration";
  }
}

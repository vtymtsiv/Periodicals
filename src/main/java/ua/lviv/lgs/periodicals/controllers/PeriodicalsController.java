package ua.lviv.lgs.periodicals.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ua.lviv.lgs.periodicals.dtos.PeriodicalCreateFormDto;
import ua.lviv.lgs.periodicals.services.PeriodicalService;

@Controller
@RequestMapping("/periodicals")
public class PeriodicalsController {

  private final PeriodicalService periodicalService;

  @Autowired
  public PeriodicalsController(PeriodicalService periodicalService) {
    this.periodicalService = periodicalService;
  }

  @GetMapping("/create")
  public String create() {
    return "createPeriodicals";
  }

  @PostMapping("/save")
  public String save(@ModelAttribute PeriodicalCreateFormDto periodicalCreateFormDto, @RequestParam MultipartFile[] photos) {
    periodicalService.save(periodicalCreateFormDto, photos);
    return "redirect:/periodicals/create?success";
  }

  @GetMapping("/all")
  public String all(Model model) {
    model.addAttribute("periodicals", periodicalService.findAll());
    return "allPeriodicals";
  }
}

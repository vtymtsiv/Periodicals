package ua.lviv.lgs.periodicals.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ua.lviv.lgs.periodicals.dtos.PeriodicalBaseViewDto;
import ua.lviv.lgs.periodicals.dtos.PeriodicalCreateFormDto;
import ua.lviv.lgs.periodicals.services.CustomUserDetails;
import ua.lviv.lgs.periodicals.services.PeriodicalService;
import ua.lviv.lgs.periodicals.services.SubscriptionService;

@Controller
@RequestMapping("/periodicals")
public class PeriodicalsController {

  private final PeriodicalService periodicalService;
  private final SubscriptionService subscriptionService;

  @Autowired
  public PeriodicalsController(PeriodicalService periodicalService, SubscriptionService subscriptionService) {
    this.periodicalService = periodicalService;
    this.subscriptionService = subscriptionService;
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
  public String all(Model model, Authentication authentication ) {
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
    int userId = customUserDetails.getUserId();
    List<Integer> subscribedPeriodicalIds = periodicalService.getAllIdsByUserId(userId);

    List<PeriodicalBaseViewDto> periodicalBaseViewDtos = periodicalService.findAll()
      .stream()
      .map(periodical -> {
        PeriodicalBaseViewDto dto = new PeriodicalBaseViewDto(
          periodical.getId(),
          periodical.getName(),
          periodical.getDescription(),
          periodical.getPhotos()
        );
        dto.setIsUserSubscribed(subscribedPeriodicalIds.contains(periodical.getId()));
        return dto;
      })
      .collect(Collectors.toList());


    model.addAttribute("periodicals", periodicalBaseViewDtos);
    return "allPeriodicals";
  }

  @GetMapping("/subscribe/{id}")
  public String subscribe(@PathVariable("id") int periodicalId, Authentication authentication) {
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

    subscriptionService.subscribe(periodicalId, customUserDetails.getUserId());
    return "redirect:/periodicals/all";
  }

}

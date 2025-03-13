package com.example.webtoon_print.controller;

import com.example.webtoon_print.dto.SeriesDTO;
import com.example.webtoon_print.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final SeriesService seriesService;

    @Autowired
    public HomeController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/")
    public String home(Model model) {
        SeriesDTO seriesDTO = seriesService.getFirstSeries();
        model.addAttribute("seriesId", seriesDTO.getId());
        return "Home";
    }
}

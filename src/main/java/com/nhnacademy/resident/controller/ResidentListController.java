package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/residents")
public class ResidentListController {
    private final ResidentService residentService;

    public ResidentListController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public String getResidents(ModelMap modelMap) {
        modelMap.put("residents", residentService.getResidents());
        return "residentList";
    }
}

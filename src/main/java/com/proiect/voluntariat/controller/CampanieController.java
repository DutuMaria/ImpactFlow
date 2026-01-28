package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.entity.Campanie;
import com.proiect.voluntariat.entity.enums.CampanieStatus;
import com.proiect.voluntariat.service.CampanieService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/campanii")
public class CampanieController {

    private final CampanieService campanieService;

    public CampanieController(CampanieService campanieService) {
        this.campanieService = campanieService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("campanii", campanieService.findAll());
        return "campanie/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("campanie", new Campanie());
        model.addAttribute("mode", "create");
        model.addAttribute("statusuri", CampanieStatus.values());
        return "campanie/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("campanie") Campanie campanie,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            model.addAttribute("statusuri", CampanieStatus.values());
            return "campanie/form";
        }
        try {
            campanieService.save(campanie);
            ra.addFlashAttribute("success", "Campania a fost adaugata.");
            return "redirect:/campanii";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "create");
            model.addAttribute("statusuri", CampanieStatus.values());
            model.addAttribute("error", ex.getMessage());
            return "campanie/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("campanie", campanieService.findByIdOrThrow(id));
        model.addAttribute("mode", "edit");
        model.addAttribute("statusuri", CampanieStatus.values());
        return "campanie/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("campanie") Campanie campanie,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("statusuri", CampanieStatus.values());
            return "campanie/form";
        }
        try {
            campanie.setId(id);
            campanieService.save(campanie);
            ra.addFlashAttribute("success", "Campania a fost modificata.");
            return "redirect:/campanii";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "edit");
            model.addAttribute("statusuri", CampanieStatus.values());
            model.addAttribute("error", ex.getMessage());
            return "campanie/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            campanieService.deleteById(id);
            ra.addFlashAttribute("success", "Campania a fost stearsa.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/campanii";
    }
}

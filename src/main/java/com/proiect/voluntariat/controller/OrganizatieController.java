package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.entity.Organizatie;
import com.proiect.voluntariat.service.OrganizatieService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/organizatii")
public class OrganizatieController {

    private final OrganizatieService organizatieService;

    public OrganizatieController(OrganizatieService organizatieService) {
        this.organizatieService = organizatieService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("organizatii", organizatieService.findAll());
        return "organizatie/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("organizatie", new Organizatie());
        model.addAttribute("mode", "create");
        return "organizatie/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("organizatie") Organizatie organizatie,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            return "organizatie/form";
        }
        try {
            organizatieService.save(organizatie);
            ra.addFlashAttribute("success", "Organizatia a fost adaugata.");
            return "redirect:/organizatii";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "create");
            model.addAttribute("error", ex.getMessage());
            return "organizatie/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("organizatie", organizatieService.findByIdOrThrow(id));
        model.addAttribute("mode", "edit");
        return "organizatie/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("organizatie") Organizatie organizatie,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "organizatie/form";
        }
        try {
            organizatie.setId(id);
            organizatieService.save(organizatie);
            ra.addFlashAttribute("success", "Organizatia a fost modificata.");
            return "redirect:/organizatii";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "edit");
            model.addAttribute("error", ex.getMessage());
            return "organizatie/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            organizatieService.deleteById(id);
            ra.addFlashAttribute("success", "Organizatia a fost stearsa.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/organizatii";
    }
}

package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.entity.Locatie;
import com.proiect.voluntariat.service.LocatieService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/locatii")
public class LocatieController {
    private final LocatieService locatieService;

    public LocatieController(LocatieService locatieService) {
        this.locatieService = locatieService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("locatii", locatieService.findAll());
        return "locatie/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("locatie", new Locatie());
        model.addAttribute("mode", "create");
        return "locatie/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("locatie") Locatie locatie,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            return "locatie/form";
        }
        locatieService.save(locatie);
        ra.addFlashAttribute("success", "Locatia a fost adaugata.");
        return "redirect:/locatii";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("locatie", locatieService.findByIdOrThrow(id));
        model.addAttribute("mode", "edit");
        return "locatie/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("locatie") Locatie locatie,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "locatie/form";
        }
        locatie.setId(id);
        locatieService.save(locatie);
        ra.addFlashAttribute("success", "Locatia a fost modificata.");
        return "redirect:/locatii";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            locatieService.deleteById(id);
            ra.addFlashAttribute("success", "Locatia a fost stearsa.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/locatii";
    }
}

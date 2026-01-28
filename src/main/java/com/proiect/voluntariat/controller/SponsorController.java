package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.entity.Sponsor;
import com.proiect.voluntariat.entity.enums.TipSponsor;
import com.proiect.voluntariat.service.SponsorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sponsori")
public class SponsorController {

    private final SponsorService sponsorService;

    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("sponsori", sponsorService.findAll());
        return "sponsor/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("sponsor", new Sponsor());
        model.addAttribute("mode", "create");
        model.addAttribute("tipuri", TipSponsor.values());
        return "sponsor/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("sponsor") Sponsor sponsor,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            model.addAttribute("tipuri", TipSponsor.values());
            return "sponsor/form";
        }
        try {
            sponsorService.save(sponsor);
            ra.addFlashAttribute("success", "Sponsorul a fost adaugat.");
            return "redirect:/sponsori";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "create");
            model.addAttribute("tipuri", TipSponsor.values());
            model.addAttribute("error", ex.getMessage());
            return "sponsor/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("sponsor", sponsorService.findByIdOrThrow(id));
        model.addAttribute("mode", "edit");
        model.addAttribute("tipuri", TipSponsor.values());
        return "sponsor/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("sponsor") Sponsor sponsor,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("tipuri", TipSponsor.values());
            return "sponsor/form";
        }
        try {
            sponsor.setId(id);
            sponsorService.save(sponsor);
            ra.addFlashAttribute("success", "Sponsorul a fost modificat.");
            return "redirect:/sponsori";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "edit");
            model.addAttribute("tipuri", TipSponsor.values());
            model.addAttribute("error", ex.getMessage());
            return "sponsor/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            sponsorService.deleteById(id);
            ra.addFlashAttribute("success", "Sponsorul a fost sters.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/sponsori";
    }
}

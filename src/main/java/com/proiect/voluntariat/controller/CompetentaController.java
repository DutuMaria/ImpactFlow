package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.entity.Competenta;
import com.proiect.voluntariat.service.CompetentaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/competente")
public class CompetentaController {

    private final CompetentaService service;

    public CompetentaController(CompetentaService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("competente", service.findAll());
        return "competenta/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("competenta", new Competenta());
        model.addAttribute("mode", "create");
        return "competenta/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("competenta") Competenta competenta,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            return "competenta/form";
        }
        try {
            service.save(competenta);
            ra.addFlashAttribute("success", "Competenta a fost adaugata.");
            return "redirect:/competente";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "create");
            model.addAttribute("error", ex.getMessage());
            return "competenta/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("competenta", service.findByIdOrThrow(id));
        model.addAttribute("mode", "edit");
        return "competenta/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("competenta") Competenta competenta,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "competenta/form";
        }
        try {
            competenta.setId(id);
            service.save(competenta);
            ra.addFlashAttribute("success", "Competenta a fost modificata.");
            return "redirect:/competente";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "edit");
            model.addAttribute("error", ex.getMessage());
            return "competenta/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.deleteById(id);
            ra.addFlashAttribute("success", "Competenta a fost stearsa.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/competente";
    }
}

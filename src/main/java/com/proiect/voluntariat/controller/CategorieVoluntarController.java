package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.entity.CategorieVoluntar;
import com.proiect.voluntariat.entity.enums.NumeCategorieVoluntar;
import com.proiect.voluntariat.service.CategorieVoluntarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorii-voluntari")
public class CategorieVoluntarController {

    private final CategorieVoluntarService service;

    public CategorieVoluntarController(CategorieVoluntarService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categorii", service.findAll());
        return "categorie-voluntar/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("categorieVoluntar", new CategorieVoluntar());
        model.addAttribute("mode", "create");
        model.addAttribute("numeCategorii", NumeCategorieVoluntar.values());
        return "categorie-voluntar/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("categorieVoluntar") CategorieVoluntar categorie,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            model.addAttribute("numeCategorii", NumeCategorieVoluntar.values());
            return "categorie-voluntar/form";
        }
        try {
            service.save(categorie);
            ra.addFlashAttribute("success", "Categoria a fost adaugata.");
            return "redirect:/categorii-voluntari";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "create");
            model.addAttribute("numeCategorii", NumeCategorieVoluntar.values());
            model.addAttribute("error", ex.getMessage());
            return "categorie-voluntar/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("categorieVoluntar", service.findByIdOrThrow(id));
        model.addAttribute("mode", "edit");
        model.addAttribute("numeCategorii", NumeCategorieVoluntar.values());
        return "categorie-voluntar/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("categorieVoluntar") CategorieVoluntar categorie,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("numeCategorii", NumeCategorieVoluntar.values());
            return "categorie-voluntar/form";
        }
        try {
            categorie.setId(id);
            service.save(categorie);
            ra.addFlashAttribute("success", "Categoria a fost modificata.");
            return "redirect:/categorii-voluntari";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "edit");
            model.addAttribute("numeCategorii", NumeCategorieVoluntar.values());
            model.addAttribute("error", ex.getMessage());
            return "categorie-voluntar/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.deleteById(id);
            ra.addFlashAttribute("success", "Categoria a fost stearsa.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/categorii-voluntari";
    }
}

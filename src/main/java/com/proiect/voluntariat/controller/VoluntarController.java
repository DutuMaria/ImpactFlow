package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.dto.VoluntarForm;
import com.proiect.voluntariat.entity.Voluntar;
import com.proiect.voluntariat.service.CategorieVoluntarService;
import com.proiect.voluntariat.service.VoluntarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/voluntari")
public class VoluntarController {

    private final VoluntarService voluntarService;
    private final CategorieVoluntarService categorieVoluntarService;

    public VoluntarController(VoluntarService voluntarService,
                              CategorieVoluntarService categorieVoluntarService) {
        this.voluntarService = voluntarService;
        this.categorieVoluntarService = categorieVoluntarService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("voluntari", voluntarService.findAll());
        return "voluntar/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("voluntarForm", new VoluntarForm());
        model.addAttribute("mode", "create");
        model.addAttribute("categorii", categorieVoluntarService.findAll());
        return "voluntar/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("voluntarForm") VoluntarForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            model.addAttribute("categorii", categorieVoluntarService.findAll());
            return "voluntar/form";
        }

        try {
            Voluntar voluntar = mapToEntity(form, new Voluntar());
            voluntarService.save(voluntar);
            ra.addFlashAttribute("success", "Voluntarul a fost adaugat.");
            return "redirect:/voluntari";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "create");
            model.addAttribute("categorii", categorieVoluntarService.findAll());
            model.addAttribute("error", ex.getMessage());
            return "voluntar/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Voluntar v = voluntarService.findByIdOrThrow(id);

        VoluntarForm form = new VoluntarForm();
        form.setId(v.getId());
        form.setNume(v.getNume());
        form.setPrenume(v.getPrenume());
        form.setEmail(v.getEmail());
        form.setTelefon(v.getTelefon());
        form.setDataNasterii(v.getDataNasterii());
        form.setCategorieVoluntarId(v.getCategorieVoluntar().getId());

        model.addAttribute("voluntarForm", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("categorii", categorieVoluntarService.findAll());
        return "voluntar/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("voluntarForm") VoluntarForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("categorii", categorieVoluntarService.findAll());
            return "voluntar/form";
        }

        try {
            Voluntar voluntar = voluntarService.findByIdOrThrow(id);
            mapToEntity(form, voluntar);
            voluntarService.save(voluntar);
            ra.addFlashAttribute("success", "Voluntarul a fost modificat.");
            return "redirect:/voluntari";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "edit");
            model.addAttribute("categorii", categorieVoluntarService.findAll());
            model.addAttribute("error", ex.getMessage());
            return "voluntar/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            voluntarService.deleteById(id);
            ra.addFlashAttribute("success", "Voluntarul a fost sters.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/voluntari";
    }

    private Voluntar mapToEntity(VoluntarForm form, Voluntar entity) {
        entity.setNume(form.getNume());
        entity.setPrenume(form.getPrenume());
        entity.setEmail(form.getEmail());
        entity.setTelefon(form.getTelefon());
        entity.setDataNasterii(form.getDataNasterii());

        var cat = categorieVoluntarService.findByIdOrThrow(form.getCategorieVoluntarId());
        entity.setCategorieVoluntar(cat);

        return entity;
    }
}

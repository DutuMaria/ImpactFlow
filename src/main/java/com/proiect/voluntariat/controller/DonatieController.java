package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.dto.DonatieForm;
import com.proiect.voluntariat.entity.Donatie;
import com.proiect.voluntariat.service.DonatieService;
import com.proiect.voluntariat.service.ProiectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/donatii")
public class DonatieController {

    private final DonatieService donatieService;
    private final ProiectService proiectService;

    public DonatieController(DonatieService donatieService, ProiectService proiectService) {
        this.donatieService = donatieService;
        this.proiectService = proiectService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("donatii", donatieService.findAll());
        return "donatie/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("donatieForm", new DonatieForm());
        model.addAttribute("mode", "create");
        model.addAttribute("proiecte", proiectService.findAll());
        return "donatie/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("donatieForm") DonatieForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            model.addAttribute("proiecte", proiectService.findAll());
            return "donatie/form";
        }

        try {
            Donatie d = mapToEntity(form, new Donatie());
            donatieService.save(d);
            ra.addFlashAttribute("success", "Donatia a fost adaugata.");
            return "redirect:/donatii";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "create");
            model.addAttribute("proiecte", proiectService.findAll());
            model.addAttribute("error", ex.getMessage());
            return "donatie/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Donatie d = donatieService.findByIdOrThrow(id);

        DonatieForm form = new DonatieForm();
        form.setId(d.getId());
        form.setProiectId(d.getProiect().getId());
        form.setDataDonatie(d.getDataDonatie());
        form.setDonator(d.getDonator());
        form.setSuma(d.getSuma());

        model.addAttribute("donatieForm", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("proiecte", proiectService.findAll());
        return "donatie/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("donatieForm") DonatieForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("proiecte", proiectService.findAll());
            return "donatie/form";
        }

        try {
            Donatie d = donatieService.findByIdOrThrow(id);
            mapToEntity(form, d);
            donatieService.save(d);
            ra.addFlashAttribute("success", "Donatia a fost modificata.");
            return "redirect:/donatii";
        } catch (IllegalStateException ex) {
            model.addAttribute("mode", "edit");
            model.addAttribute("proiecte", proiectService.findAll());
            model.addAttribute("error", ex.getMessage());
            return "donatie/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        donatieService.deleteById(id);
        ra.addFlashAttribute("success", "Donatia a fost stearsa.");
        return "redirect:/donatii";
    }

    private Donatie mapToEntity(DonatieForm form, Donatie d) {
        d.setDataDonatie(form.getDataDonatie());
        d.setDonator(form.getDonator());
        d.setSuma(form.getSuma());
        d.setProiect(proiectService.findByIdOrThrow(form.getProiectId()));
        return d;
    }
}

package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.dto.ProiectSponsorForm;
import com.proiect.voluntariat.entity.ProiectSponsor;
import com.proiect.voluntariat.entity.ids.ProiectSponsorId;
import com.proiect.voluntariat.service.ProiectService;
import com.proiect.voluntariat.service.ProiectSponsorService;
import com.proiect.voluntariat.service.SponsorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proiecte-sponsori")
public class ProiectSponsorController {

    private final ProiectSponsorService service;
    private final ProiectService proiectService;
    private final SponsorService sponsorService;

    public ProiectSponsorController(ProiectSponsorService service,
                                    ProiectService proiectService,
                                    SponsorService sponsorService) {
        this.service = service;
        this.proiectService = proiectService;
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("asocieri", service.findAll());
        return "proiect-sponsor/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ProiectSponsorForm());
        fillDropdowns(model);
        return "proiect-sponsor/form-create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("form") ProiectSponsorForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillDropdowns(model);
            return "proiect-sponsor/form-create";
        }

        try {
            ProiectSponsor ps = new ProiectSponsor();
            ps.setId(new ProiectSponsorId(form.getProiectId(), form.getSponsorId()));
            ps.setProiect(proiectService.findByIdOrThrow(form.getProiectId()));
            ps.setSponsor(sponsorService.findByIdOrThrow(form.getSponsorId()));

            service.save(ps);
            ra.addFlashAttribute("success", "Sponsorul a fost asociat proiectului.");
            return "redirect:/proiecte-sponsori";
        } catch (IllegalStateException ex) {
            fillDropdowns(model);
            model.addAttribute("error", ex.getMessage());
            return "proiect-sponsor/form-create";
        }
    }

    @PostMapping("/{proiectId}/{sponsorId}/delete")
    public String delete(@PathVariable Long proiectId,
                         @PathVariable Long sponsorId,
                         RedirectAttributes ra) {
        service.deleteById(new ProiectSponsorId(proiectId, sponsorId));
        ra.addFlashAttribute("success", "Asocierea a fost stearsa.");
        return "redirect:/proiecte-sponsori";
    }

    private void fillDropdowns(Model model) {
        model.addAttribute("proiecte", proiectService.findAll());
        model.addAttribute("sponsori", sponsorService.findAll());
    }
}

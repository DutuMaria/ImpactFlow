package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.dto.ProiectForm;
import com.proiect.voluntariat.entity.Proiect;
import com.proiect.voluntariat.entity.enums.ProiectStatus;
import com.proiect.voluntariat.service.CampanieService;
import com.proiect.voluntariat.service.LocatieService;
import com.proiect.voluntariat.service.OrganizatieService;
import com.proiect.voluntariat.service.ProiectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proiecte")
public class ProiectController {

    private final ProiectService proiectService;
    private final OrganizatieService organizatieService;
    private final LocatieService locatieService;
    private final CampanieService campanieService;

    public ProiectController(ProiectService proiectService,
                             OrganizatieService organizatieService,
                             LocatieService locatieService,
                             CampanieService campanieService) {
        this.proiectService = proiectService;
        this.organizatieService = organizatieService;
        this.locatieService = locatieService;
        this.campanieService = campanieService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("proiecte", proiectService.findAll());
        return "proiect/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("proiectForm", new ProiectForm());
        fillDropdowns(model);
        model.addAttribute("mode", "create");
        return "proiect/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("proiectForm") ProiectForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillDropdowns(model);
            model.addAttribute("mode", "create");
            return "proiect/form";
        }

        try {
            Proiect p = mapToEntity(form, new Proiect());
            proiectService.save(p);
            ra.addFlashAttribute("success", "Proiectul a fost adaugat.");
            return "redirect:/proiecte";
        } catch (IllegalStateException ex) {
            fillDropdowns(model);
            model.addAttribute("mode", "create");
            model.addAttribute("error", ex.getMessage());
            return "proiect/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Proiect p = proiectService.findByIdOrThrow(id);

        ProiectForm form = new ProiectForm();
        form.setId(p.getId());
        form.setNumeProiect(p.getNumeProiect());
        form.setDescriere(p.getDescriere());
        form.setDataStart(p.getDataStart());
        form.setDataFinal(p.getDataFinal());
        form.setStatus(p.getStatus());
        form.setOrganizatieId(p.getOrganizatie().getId());
        form.setLocatieId(p.getLocatie().getId());
        form.setCampanieId(p.getCampanie() == null ? null : p.getCampanie().getId());

        model.addAttribute("proiectForm", form);
        fillDropdowns(model);
        model.addAttribute("mode", "edit");
        return "proiect/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("proiectForm") ProiectForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillDropdowns(model);
            model.addAttribute("mode", "edit");
            return "proiect/form";
        }

        try {
            Proiect p = proiectService.findByIdOrThrow(id);
            mapToEntity(form, p);
            proiectService.save(p);
            ra.addFlashAttribute("success", "Proiectul a fost modificat.");
            return "redirect:/proiecte";
        } catch (IllegalStateException ex) {
            fillDropdowns(model);
            model.addAttribute("mode", "edit");
            model.addAttribute("error", ex.getMessage());
            return "proiect/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            proiectService.deleteById(id);
            ra.addFlashAttribute("success", "Proiectul a fost sters.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/proiecte";
    }

    private void fillDropdowns(Model model) {
        model.addAttribute("organizatii", organizatieService.findAll());
        model.addAttribute("locatii", locatieService.findAll());
        model.addAttribute("campanii", campanieService.findAll());
        model.addAttribute("statusuri", ProiectStatus.values());
    }

    private Proiect mapToEntity(ProiectForm form, Proiect p) {
        p.setNumeProiect(form.getNumeProiect());
        p.setDescriere(form.getDescriere());
        p.setDataStart(form.getDataStart());
        p.setDataFinal(form.getDataFinal());
        p.setStatus(form.getStatus());

        p.setOrganizatie(organizatieService.findByIdOrThrow(form.getOrganizatieId()));
        p.setLocatie(locatieService.findByIdOrThrow(form.getLocatieId()));

        if (form.getCampanieId() == null) {
            p.setCampanie(null);
        } else {
            p.setCampanie(campanieService.findByIdOrThrow(form.getCampanieId()));
        }
        return p;
    }
}

package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.dto.ProiectCompetentaForm;
import com.proiect.voluntariat.entity.ProiectCompetenta;
import com.proiect.voluntariat.entity.ids.ProiectCompetentaId;
import com.proiect.voluntariat.service.CompetentaService;
import com.proiect.voluntariat.service.ProiectCompetentaService;
import com.proiect.voluntariat.service.ProiectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proiecte-competente")
public class ProiectCompetentaController {

    private final ProiectCompetentaService service;
    private final ProiectService proiectService;
    private final CompetentaService competentaService;

    public ProiectCompetentaController(ProiectCompetentaService service,
                                       ProiectService proiectService,
                                       CompetentaService competentaService) {
        this.service = service;
        this.proiectService = proiectService;
        this.competentaService = competentaService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("asocieri", service.findAll());
        return "proiect-competenta/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ProiectCompetentaForm());
        fillDropdowns(model);
        return "proiect-competenta/form-create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("form") ProiectCompetentaForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillDropdowns(model);
            return "proiect-competenta/form-create";
        }

        try {
            ProiectCompetenta pc = new ProiectCompetenta();
            pc.setId(new ProiectCompetentaId(form.getProiectId(), form.getCompetentaId()));
            pc.setProiect(proiectService.findByIdOrThrow(form.getProiectId()));
            pc.setCompetenta(competentaService.findByIdOrThrow(form.getCompetentaId()));

            service.save(pc);
            ra.addFlashAttribute("success", "Competenta a fost asociata proiectului.");
            return "redirect:/proiecte-competente";
        } catch (IllegalStateException ex) {
            fillDropdowns(model);
            model.addAttribute("error", ex.getMessage());
            return "proiect-competenta/form-create";
        }
    }

    @PostMapping("/{proiectId}/{competentaId}/delete")
    public String delete(@PathVariable Long proiectId,
                         @PathVariable Long competentaId,
                         RedirectAttributes ra) {
        service.deleteById(new ProiectCompetentaId(proiectId, competentaId));
        ra.addFlashAttribute("success", "Asocierea a fost stearsa.");
        return "redirect:/proiecte-competente";
    }

    private void fillDropdowns(Model model) {
        model.addAttribute("proiecte", proiectService.findAll());
        model.addAttribute("competente", competentaService.findAll());
    }
}

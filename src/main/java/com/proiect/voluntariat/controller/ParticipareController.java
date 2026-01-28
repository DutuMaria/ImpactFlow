package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.dto.ParticipareForm;
import com.proiect.voluntariat.entity.ParticipareVoluntarProiect;
import com.proiect.voluntariat.entity.ids.ParticipareId;
import com.proiect.voluntariat.service.ParticipareService;
import com.proiect.voluntariat.service.ProiectService;
import com.proiect.voluntariat.service.VoluntarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/participari")
public class ParticipareController {

    private final ParticipareService participareService;
    private final VoluntarService voluntarService;
    private final ProiectService proiectService;

    public ParticipareController(ParticipareService participareService,
                                 VoluntarService voluntarService,
                                 ProiectService proiectService) {
        this.participareService = participareService;
        this.voluntarService = voluntarService;
        this.proiectService = proiectService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("participari", participareService.findAll());
        return "participare/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ParticipareForm());
        fillDropdowns(model);
        return "participare/form-create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("form") ParticipareForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillDropdowns(model);
            return "participare/form-create";
        }

        try {
            ParticipareVoluntarProiect p = new ParticipareVoluntarProiect();
            p.setId(new ParticipareId(form.getVoluntarId(), form.getProiectId()));
            p.setVoluntar(voluntarService.findByIdOrThrow(form.getVoluntarId()));
            p.setProiect(proiectService.findByIdOrThrow(form.getProiectId()));
            p.setRol(form.getRol());
            p.setNumarOre(form.getNumarOre());

            participareService.save(p);
            ra.addFlashAttribute("success", "Participarea a fost adaugata.");
            return "redirect:/participari";
        } catch (IllegalStateException ex) {
            fillDropdowns(model);
            model.addAttribute("error", ex.getMessage());
            return "participare/form-create";
        }
    }

    @GetMapping("/{voluntarId}/{proiectId}/edit")
    public String editForm(@PathVariable Long voluntarId,
                           @PathVariable Long proiectId,
                           Model model) {
        ParticipareId id = new ParticipareId(voluntarId, proiectId);
        ParticipareVoluntarProiect p = participareService.findByIdOrThrow(id);

        ParticipareForm form = new ParticipareForm();
        form.setVoluntarId(voluntarId);
        form.setProiectId(proiectId);
        form.setRol(p.getRol());
        form.setNumarOre(p.getNumarOre());

        model.addAttribute("form", form);
        model.addAttribute("voluntar", p.getVoluntar());
        model.addAttribute("proiect", p.getProiect());
        return "participare/form-edit";
    }

    @PostMapping("/{voluntarId}/{proiectId}")
    public String update(@PathVariable Long voluntarId,
                         @PathVariable Long proiectId,
                         @Valid @ModelAttribute("form") ParticipareForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("voluntar", voluntarService.findByIdOrThrow(voluntarId));
            model.addAttribute("proiect", proiectService.findByIdOrThrow(proiectId));
            return "participare/form-edit";
        }

        ParticipareId id = new ParticipareId(voluntarId, proiectId);
        ParticipareVoluntarProiect p = participareService.findByIdOrThrow(id);
        p.setRol(form.getRol());
        p.setNumarOre(form.getNumarOre());

        participareService.save(p);
        ra.addFlashAttribute("success", "Participarea a fost modificata.");
        return "redirect:/participari";
    }

    @PostMapping("/{voluntarId}/{proiectId}/delete")
    public String delete(@PathVariable Long voluntarId,
                         @PathVariable Long proiectId,
                         RedirectAttributes ra) {
        participareService.deleteById(new ParticipareId(voluntarId, proiectId));
        ra.addFlashAttribute("success", "Participarea a fost stearsa.");
        return "redirect:/participari";
    }

    private void fillDropdowns(Model model) {
        model.addAttribute("voluntari", voluntarService.findAll());
        model.addAttribute("proiecte", proiectService.findAll());
    }
}

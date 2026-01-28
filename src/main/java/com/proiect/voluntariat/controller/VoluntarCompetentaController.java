package com.proiect.voluntariat.controller;

import com.proiect.voluntariat.dto.VoluntarCompetentaForm;
import com.proiect.voluntariat.entity.VoluntarCompetenta;
import com.proiect.voluntariat.entity.enums.NivelCompetenta;
import com.proiect.voluntariat.entity.ids.VoluntarCompetentaId;
import com.proiect.voluntariat.service.CompetentaService;
import com.proiect.voluntariat.service.VoluntarCompetentaService;
import com.proiect.voluntariat.service.VoluntarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/voluntari-competente")
public class VoluntarCompetentaController {

    private final VoluntarCompetentaService service;
    private final VoluntarService voluntarService;
    private final CompetentaService competentaService;

    public VoluntarCompetentaController(VoluntarCompetentaService service,
                                        VoluntarService voluntarService,
                                        CompetentaService competentaService) {
        this.service = service;
        this.voluntarService = voluntarService;
        this.competentaService = competentaService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("asocieri", service.findAll());
        return "voluntar-competenta/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form", new VoluntarCompetentaForm());
        fillDropdowns(model);
        model.addAttribute("mode", "create");
        return "voluntar-competenta/form-create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("form") VoluntarCompetentaForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillDropdowns(model);
            model.addAttribute("mode", "create");
            return "voluntar-competenta/form-create";
        }

        try {
            VoluntarCompetenta vc = new VoluntarCompetenta();
            vc.setId(new VoluntarCompetentaId(form.getVoluntarId(), form.getCompetentaId()));
            vc.setVoluntar(voluntarService.findByIdOrThrow(form.getVoluntarId()));
            vc.setCompetenta(competentaService.findByIdOrThrow(form.getCompetentaId()));
            vc.setNivel(form.getNivel());

            service.save(vc);
            ra.addFlashAttribute("success", "Competenta a fost atribuita voluntarului.");
            return "redirect:/voluntari-competente";
        } catch (IllegalStateException ex) {
            fillDropdowns(model);
            model.addAttribute("mode", "create");
            model.addAttribute("error", ex.getMessage());
            return "voluntar-competenta/form-create";
        }
    }

    @GetMapping("/{voluntarId}/{competentaId}/edit")
    public String editForm(@PathVariable Long voluntarId,
                           @PathVariable Long competentaId,
                           Model model) {
        var id = new VoluntarCompetentaId(voluntarId, competentaId);
        var vc = service.findByIdOrThrow(id);

        var form = new VoluntarCompetentaForm();
        form.setVoluntarId(voluntarId);
        form.setCompetentaId(competentaId);
        form.setNivel(vc.getNivel());

        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        model.addAttribute("voluntar", vc.getVoluntar());
        model.addAttribute("competenta", vc.getCompetenta());
        model.addAttribute("niveluri", NivelCompetenta.values());
        return "voluntar-competenta/form-edit";
    }

    @PostMapping("/{voluntarId}/{competentaId}")
    public String update(@PathVariable Long voluntarId,
                         @PathVariable Long competentaId,
                         @Valid @ModelAttribute("form") VoluntarCompetentaForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("voluntar", voluntarService.findByIdOrThrow(voluntarId));
            model.addAttribute("competenta", competentaService.findByIdOrThrow(competentaId));
            model.addAttribute("niveluri", NivelCompetenta.values());
            return "voluntar-competenta/form-edit";
        }

        var id = new VoluntarCompetentaId(voluntarId, competentaId);
        var vc = service.findByIdOrThrow(id);
        vc.setNivel(form.getNivel());

        service.save(vc);
        ra.addFlashAttribute("success", "Nivelul competentei a fost actualizat.");
        return "redirect:/voluntari-competente";
    }

    @PostMapping("/{voluntarId}/{competentaId}/delete")
    public String delete(@PathVariable Long voluntarId,
                         @PathVariable Long competentaId,
                         RedirectAttributes ra) {
        service.deleteById(new VoluntarCompetentaId(voluntarId, competentaId));
        ra.addFlashAttribute("success", "Asocierea a fost stearsa.");
        return "redirect:/voluntari-competente";
    }

    private void fillDropdowns(Model model) {
        model.addAttribute("voluntari", voluntarService.findAll());
        model.addAttribute("competente", competentaService.findAll());
        model.addAttribute("niveluri", NivelCompetenta.values());
    }
}

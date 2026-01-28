package com.proiect.voluntariat.dto;

import com.proiect.voluntariat.entity.enums.NivelCompetenta;
import jakarta.validation.constraints.NotNull;

public class VoluntarCompetentaForm {

    @NotNull(message = "Voluntarul este obligatoriu.")
    private Long voluntarId;

    @NotNull(message = "Competenta este obligatorie.")
    private Long competentaId;

    @NotNull(message = "Nivelul este obligatoriu.")
    private NivelCompetenta nivel;

    public Long getVoluntarId() { return voluntarId; }
    public Long getCompetentaId() { return competentaId; }
    public NivelCompetenta getNivel() { return nivel; }

    public void setVoluntarId(Long voluntarId) { this.voluntarId = voluntarId; }
    public void setCompetentaId(Long competentaId) { this.competentaId = competentaId; }
    public void setNivel(NivelCompetenta nivel) { this.nivel = nivel; }
}

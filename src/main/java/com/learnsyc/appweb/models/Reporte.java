package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "reportes")
@Entity
@NoArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reporte")
    Long idReporte;

    @Column(name = "razon")
    String razon;

    @JoinColumns({
            @JoinColumn(name="id_usuario_reportado", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuarioReportado;

    @JoinColumns({
            @JoinColumn(name="id_usuario_reportador", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuarioReportador;

    @JoinColumns({
            @JoinColumn(name="id_comentario", referencedColumnName="id_comentario")
    })
    @ManyToOne
    Comentario comentario;

    @JoinColumns({
            @JoinColumn(name="id_hilo", referencedColumnName="id_hilo")
    })
    @ManyToOne
    Hilo hilo;

    public Reporte(Long idReporte, String razon, Usuario usuarioReportado, Usuario usuarioReportador){
        this.idReporte = idReporte;
        this.razon = razon;
        this.usuarioReportado = usuarioReportado;
        this.usuarioReportador = usuarioReportador;
        comentario = null;
        hilo = null;
    }
}
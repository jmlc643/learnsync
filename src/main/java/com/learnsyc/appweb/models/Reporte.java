package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "reportes")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reporte")
    Long idReporte;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario usuario;

    @Column(name = "usuarioreportado")
    String usuarioReportado;

    @Column(name = "comentarioreportado")
    String comentarioReportado;

    @Column(name = "hiloreportado")
    String hiloReportado;

    @Column(name = "razon", columnDefinition = "TEXT")
    String razon;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    Comentario comentario;

    @ManyToOne
    @JoinColumn(name = "hilo_id")
    Hilo hilo;
}
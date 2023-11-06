package com.learnsyc.appweb.models;

import java.time.*; //Para las fechas

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*; 
@Data //Construye métodos Set, Get, toString
@Table(name="usuarios") //Da a entender que sera un modelo de base de datos
@Entity //Da a entender que sera una entidad de base de datos
@NoArgsConstructor //Genera constructor vacio
public class Usuario {
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO) //Hace un autoincrement
    @Column(name="id_usuario") //Para que ubique a que columna agregar el valor
    Long idUsuario;
    @Column(name="user")
    String user;
    @Column(name="password")
    String password;
    @Column(name="email")
    String email;
    @Column(name="es_admin")
    boolean esAdmin;
    @Column(name="fecha_creacion")
    final LocalDate fechaCreacion = LocalDate.now();
    @Column(name="inicio_suspension")
    LocalDateTime inicioSuspension;
    @Column(name="fin_suspension")
    LocalDateTime finSuspension;
    @Column(name="baneado")
    boolean baneado;
    @Column(name="nro_reportes")
    int nroReportes;

    public Usuario(Long idUsuario, String user, String password, String email){
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
        this.email = email;
        esAdmin = false;
        inicioSuspension = null;
        finSuspension = null;
        baneado = false;
        nroReportes = 0;
    }
}
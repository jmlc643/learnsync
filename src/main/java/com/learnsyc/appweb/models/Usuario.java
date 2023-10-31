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
    @Column(name="idUsuario") //Para que ubique a que columna agregar el valor
    Long idUsuario;
    @Column(name="user")
    String user;
    @Column(name="password")
    String password;
    @Column(name="email")
    String email;
    @Column(name="esAdmin")
    boolean esAdmin;
    @Column(name="fechaCreacion")
    final LocalDate fechaCreacion = LocalDate.now();
    @Column(name="inicioSuspension")
    LocalDateTime inicioSuspension;
    @Column(name="finSuspension")
    LocalDateTime finSuspension;
    @Column(name="baneado")
    boolean baneado;
    @Column(name="nroReportes")
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
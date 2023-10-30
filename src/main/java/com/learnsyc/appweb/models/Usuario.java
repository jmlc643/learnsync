package com.learnsyc.appweb.models;

import lombok.Data; 
import lombok.NoArgsConstructor; 
import jakarta.persistence.*; 
@Data //Construye métodos Set, Get, toString
@Table(name="usuario") //Da a entender que sera un modelo de base de datos
@Entity //Da a entender que sera una entidad de base de datos
@NoArgsConstructor //Genera constructor vacio
public class Usuario {
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO) //Hace un autoincrement
    Long Id;
    String user;
    String password;
    String email;
    boolean esAdmin;
    String fechaCreacion;
    String inicioSuspension;
    String finSuspension;
    boolean baneado;
    int nroReportes;

    public Usuario(Long Id, String user, String password, String email){
        this.Id = Id;
        this.user = user;
        this.password = password;
        this.email = email;
        esAdmin = false;
        fechaCreacion = null;
        inicioSuspension = null;
        finSuspension = null;
        baneado = false;
        nroReportes = 0;
    }
}
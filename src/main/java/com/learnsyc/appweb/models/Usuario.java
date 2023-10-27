package com.learnsyc.appweb.models;

import lombok.Data;

@Data
public class Usuario {
    String user;
    String password;
    String email;
    boolean esAdmin;
    String fechaCreacion;
    String inicioSuspension;
    String finSuspension;
    boolean baneado;
    int nroReportes;

    public Usuario(String user, String password, String email){
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
package com.learnsyc.appweb.models;

public class Usuario {
    String nombre;
    String user;
    String password;

    public Usuario(){
    }

    public Usuario(String nombre, String user, String password){
        this.nombre = nombre;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString(){
        return "Usuario(nombre: " + nombre + "\tuser: " + user +"\tpassword: " + password + " )"; 
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getPassword(){
        return password;
    }

    public void setPassowrd(String password){
        this.password = password;
    }
}

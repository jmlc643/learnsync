package com.learnsyc.appweb.overview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;

import com.learnsyc.appweb.models.Usuario;

public class OverviewMain {
    static String AuthenticatedUsername = "anónimo";
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Usuario> array = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        
        String opc = "";
        do{
            System.out.println("Menú");
            System.out.println("---------------------------------");
            System.out.println("Bienvenido "+ AuthenticatedUsername);
            System.out.println("---------------------------------");
            System.out.println("A. Crear usuario");
            System.out.println("B. Listar usuario");
            System.out.println("C. Login");
            System.out.println("D. Cerrar sesión");
            System.out.println("Z. Salir");
            System.out.println("Seleccione una opción: ");
            opc = br.readLine();

            switch(opc.toUpperCase()){
                case "A" -> crearUsuario();    
                case "B" -> listarUsuario();                
                case "C" -> autenticarUsuario(); 
                case "D" -> cerrarSesion();
                default -> System.out.println("Opción incorrecta");
            }
        }while(!opc.toUpperCase().equals("Z"));
    }
    
    private static void cerrarSesion() {
        AuthenticatedUsername = "anónimo";
    }

    private static void autenticarUsuario() throws IOException {
        System.out.println("Ingrese su usuario");
        String user = br.readLine();
        System.out.println("Ingrese su password");
        String password = br.readLine();

        Optional<Usuario> result = array.stream().filter(usuario -> {
            return usuario.getUser().equalsIgnoreCase(user) 
            && usuario.getPassword().equalsIgnoreCase(password);   
        }).findFirst();
        if (result.isPresent()){
            System.out.println("Bienvenido " + result.get().getNombre());
            AuthenticatedUsername = result.get().getUser();
        } else {
            System.out.println("Usuario o contraseña incorrecto");
        }
    }
    private static void listarUsuario() {
        array.stream().map(usuario -> usuario.getNombre().toUpperCase()).forEach(nombre ->{
            System.out.println("-" + nombre);
        });
    }

    private static void crearUsuario() throws IOException {
        System.out.println("Ingrese su nombre");
        String nombre = br.readLine();
        System.out.println("Ingrese su usuario");
        String user = br.readLine();
        System.out.println("Ingrese su password");
        String password = br.readLine();
        Usuario usuario = new Usuario(nombre, user, password);
        array.add(usuario);
    }
}

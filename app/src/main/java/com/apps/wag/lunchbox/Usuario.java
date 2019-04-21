package com.apps.wag.lunchbox;

public class Usuario {

    private String correo;
    private String contrasenna;

    public Usuario() {

    }

    public Usuario(String correo, String contrasenna) {
        this.correo = correo;
        this.contrasenna = contrasenna;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getContrasenna() {
        return this.contrasenna;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }
}
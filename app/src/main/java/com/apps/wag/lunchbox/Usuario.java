package com.apps.wag.lunchbox;

public class Usuario {

    private String codigo;
    private String nomUsuario;
    private String correo;
    private String contrasenna;
    private String descripcion;

    public Usuario() {

    }

    public Usuario(String codigo, String nomUsuario, String correo, String contrasenna, String descripcion) {
        this.setCodigo(codigo);
        this.setNomUsuario(nomUsuario);
        this.correo = correo;
        this.contrasenna = contrasenna;
        this.setDescripcion(descripcion);
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
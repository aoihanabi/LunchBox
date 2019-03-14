package com.apps.wag.lunchbox;

public class User {

    private String usuario;
    private String clave;

    public User(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public boolean matches(String clave) {
        return this.clave.equals(clave);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}

package com.token.jwt.tokensJWT.models;

public class User {
    private String usuario;
    private String password;
    private String role;

    
    public String getUsuario() {
        return usuario;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
}

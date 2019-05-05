/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.UsuarioFacadeLocal;
import com.ayungan.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class Autenticacion implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private Usuario usuario;

    @PostConstruct
    public void init() {
        //usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String autenticar() {
        String url = null;
        try {
            Usuario u
                    = usuarioFacadeLocal.login(usuario);
            if (u != null) {
                //almacenar session
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                url = "/protegido/principal?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Usuario o contraseña incprrecto"));

            }
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "" + e.getMessage()));
        }
        return url;
    }
}

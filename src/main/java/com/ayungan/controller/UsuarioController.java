/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.PersonaFacadeLocal;
import com.ayungan.ejb.UsuarioFacadeLocal;
import com.ayungan.model.Persona;
import com.ayungan.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class UsuarioController implements Serializable {

    List<Usuario> lstUsuario;
    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    PersonaFacadeLocal personaFacadeLocal;
    @Inject
    private Usuario usuario;
    @Inject
    private Persona persona;

    @PostConstruct
    public void init() {
        //  usuario = new Usuario();
        // persona = new Persona();
        lstUsuario = usuarioFacadeLocal.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    public PersonaFacadeLocal getPersonaFacadeLocal() {
        return personaFacadeLocal;
    }

    public void setPersonaFacadeLocal(PersonaFacadeLocal personaFacadeLocal) {
        this.personaFacadeLocal = personaFacadeLocal;
    }

    public void crear() {
        System.out.println("creando...");
        try {
            usuario.setPersona(persona);
            usuarioFacadeLocal.create(usuario);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se creo corectamente "));

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "" + e.getMessage()));
        } finally {
            init();
        }
    }

}

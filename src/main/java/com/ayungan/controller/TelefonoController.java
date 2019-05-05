/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.TelefonoFacadeLocal;
import com.ayungan.model.Telefono;
import com.ayungan.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class TelefonoController implements Serializable {

    @EJB
    private TelefonoFacadeLocal telefonoFacedeLocal;
    @Inject
    private Telefono telefono;
    private List<Telefono> lstTelefono;
    private boolean edit;

    @PostConstruct
    public void init() {
        edit = false;
        lstTelefono = telefonoFacedeLocal.findAll();
        System.out.println("telefonos" + lstTelefono.size());
    }

    public TelefonoFacadeLocal getTelefonoFacedeLocal() {
        return telefonoFacedeLocal;
    }

    public void setTelefonoFacedeLocal(TelefonoFacadeLocal telefonoFacedeLocal) {
        this.telefonoFacedeLocal = telefonoFacedeLocal;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public List<Telefono> getLstTelefono() {
        return lstTelefono;
    }

    public void setLstTelefono(List<Telefono> lstTelefono) {
        this.lstTelefono = lstTelefono;
    }

    public void registrarNnuevo() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            telefono.setPersona(u.getPersona());
            System.out.println("" + telefono.toString());
            if (edit == true) {
                telefonoFacedeLocal.edit(telefono);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion", "Correct"));
            } else {
                telefonoFacedeLocal.create(telefono);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso", "Correct"));
            }
            telefono = new Telefono();
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "" + e.getMessage()));
        }

    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void nuevo() {
        edit = false;
        telefono = new Telefono();
        
        PrimeFaces.current().executeScript("PF('nuevo').show()");
    }

    public void nodificar(Telefono t) {
        telefono = t;
        edit = true;
        System.out.println("Editando...");
        registrarNnuevo();
    }

    public void editar(Telefono t) {
        try {
            telefono = t;
            edit = true;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "" + e.getMessage()));
        }

    }

}

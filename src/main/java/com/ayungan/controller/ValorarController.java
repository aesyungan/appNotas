/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.NotaFacadeLocal;
import com.ayungan.model.Nota;
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
public class ValorarController implements Serializable{

    @Inject
    private ComentarController comentarController;
    @Inject
    private Nota nota;
    @EJB
    private NotaFacadeLocal notaFacadeLocal;

    @PostConstruct
    public void init() {
        this.nota = comentarController.getNota();
    }

    public ComentarController getComentarController() {
        return comentarController;
    }

    public void setComentarController(ComentarController comentarController) {
        this.comentarController = comentarController;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public void registrar() {
        try {
            notaFacadeLocal.edit(nota);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Correcto!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "" + e.getMessage()));
            System.out.println("error->" + e.getMessage());
        } finally {
            //guarda los mensajes para la otra pagina
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

}

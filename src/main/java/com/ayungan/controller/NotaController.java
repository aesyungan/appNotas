/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.CategoriaFacadeLocal;
import com.ayungan.ejb.NotaFacadeLocal;
import com.ayungan.model.Categoria;
import com.ayungan.model.Nota;
import com.ayungan.model.Persona;
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

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class NotaController implements Serializable {

    @EJB
    private NotaFacadeLocal notaFacadeLocal;
    @EJB
    private CategoriaFacadeLocal categoriaFacadeLocal;
    @Inject
    private Nota nota;
    @Inject
    private Categoria categoria;

    List<Categoria> lstCatehgoria;

    @PostConstruct
    private void init() {
        lstCatehgoria = categoriaFacadeLocal.findAll();
    }

    public void registrar() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            nota.setCategoria(categoria);

            nota.setPersona(u.getPersona());
            notaFacadeLocal.create(nota);
            nota = new Nota();
            categoria = new Categoria();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "correcto"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "" + e.getMessage()));
            System.out.println("com.ayungan.controller.NotaController.registrar()");
            System.out.println("Error->" + e.getMessage());
        }
    }

    public NotaFacadeLocal getNotaFacadeLocal() {
        return notaFacadeLocal;
    }

    public void setNotaFacadeLocal(NotaFacadeLocal notaFacadeLocal) {
        this.notaFacadeLocal = notaFacadeLocal;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public CategoriaFacadeLocal getCategoriaFacadeLocal() {
        return categoriaFacadeLocal;
    }

    public void setCategoriaFacadeLocal(CategoriaFacadeLocal categoriaFacadeLocal) {
        this.categoriaFacadeLocal = categoriaFacadeLocal;
    }

    public List<Categoria> getLstCatehgoria() {
        return lstCatehgoria;
    }

    public void setLstCatehgoria(List<Categoria> lstCatehgoria) {
        this.lstCatehgoria = lstCatehgoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.CategoriaFacadeLocal;

import com.ayungan.model.Categoria;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.persistence.sessions.serializers.Serializer;

/**
 *
 * @author Alex
 */
@Named//inyeccion de dependencias
@ViewScoped
public class CategoriaController implements Serializable {

    @EJB
    CategoriaFacadeLocal categoriaEJB;
    @Inject
    private Categoria categoria;

    @PostConstruct
    public void init() {
        System.out.println("init");
       // categoria = new Categoria();
    }

    public void registrarNuevo() {
        try {
            System.out.println("Creado...");
            categoriaEJB.create(categoria);
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Successful", "Se creo corectamente "));
        } catch (Exception e) {
            System.out.println("com.ayungan.controller.CategoriaController.registrar()");
            System.out.println("Error->" + e.getMessage());
        }
    }

    public CategoriaFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriaFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}

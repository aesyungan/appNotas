/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.NotaFacadeLocal;
import com.ayungan.model.Nota;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alex
 */
@Named
@RequestScoped
public class ComentarController implements Serializable {

    @EJB
    private NotaFacadeLocal notaFacadeLocal;
    private List< Nota> lstNotas;
   @Inject
    private Nota nota;

    @PostConstruct
    private void init() {
      lstNotas=  notaFacadeLocal.findAll();
      
    }

    public NotaFacadeLocal getNotaFacadeLocal() {
        return notaFacadeLocal;
    }

    public void setNotaFacadeLocal(NotaFacadeLocal notaFacadeLocal) {
        this.notaFacadeLocal = notaFacadeLocal;
    }

    public List<Nota> getLstNotas() {
        return lstNotas;
    }

    public void setLstNotas(List<Nota> lstNotas) {
        this.lstNotas = lstNotas;
    }
    
    public  void asignar(Nota n){
    this.nota =n;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    
}

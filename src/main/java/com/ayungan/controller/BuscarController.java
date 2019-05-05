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
import com.ayungan.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class BuscarController implements Serializable {

    @EJB
    private CategoriaFacadeLocal categoriaLocalFacade;

    @EJB
    private NotaFacadeLocal notaFacadeLocal;
    private List<Categoria> lstCategoria;
    private int codigoCategoria;
    private List<Nota> lstNota;
    private Date fechaConsulta;

    @PostConstruct
    private void init() {
        lstCategoria = categoriaLocalFacade.findAll();
    }

    public CategoriaFacadeLocal getCategoriaLocalFacade() {
        return categoriaLocalFacade;
    }

    public void setCategoriaLocalFacade(CategoriaFacadeLocal categoriaLocalFacade) {
        this.categoriaLocalFacade = categoriaLocalFacade;
    }

    public List<Categoria> getLstCategoria() {
        return lstCategoria;
    }

    public void setLstCategoria(List<Categoria> lstCategoria) {
        this.lstCategoria = lstCategoria;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public void buscar() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            lstNota = notaFacadeLocal.buscar(u.getPersona().getCodigo(), codigoCategoria, fechaConsulta);
            System.out.println("notas->" + lstNota.size());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "" + e.getMessage()));
        }

    }

    public NotaFacadeLocal getNotaFacadeLocal() {
        return notaFacadeLocal;
    }

    public void setNotaFacadeLocal(NotaFacadeLocal notaFacadeLocal) {
        this.notaFacadeLocal = notaFacadeLocal;
    }

    public List<Nota> getLstNota() {
        return lstNota;
    }

    public void setLstNota(List<Nota> lstNota) {
        this.lstNota = lstNota;
    }

}

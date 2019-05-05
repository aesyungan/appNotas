/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.PersonaFacadeLocal;
import com.ayungan.ejb.TelefonoFacadeLocal;
import com.ayungan.model.Persona;
import com.ayungan.model.Telefono;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class CansultarController implements Serializable {

    @EJB
    private PersonaFacadeLocal personaFacadeLocal;
    @EJB
    private TelefonoFacadeLocal telefnoFacadeLocal;
    private List<Persona> lstPersona;
    private List<Telefono> lsttelefono;
    private int codigoPersona;

    @PostConstruct
    private void init() {
        lstPersona = personaFacadeLocal.findAll();
    }

    public PersonaFacadeLocal getPersonaFacadeLocal() {
        return personaFacadeLocal;
    }

    public void setPersonaFacadeLocal(PersonaFacadeLocal personaFacadeLocal) {
        this.personaFacadeLocal = personaFacadeLocal;
    }

    public List<Persona> getLstPersona() {
        return lstPersona;
    }

    public void setLstPersona(List<Persona> lstPersona) {
        this.lstPersona = lstPersona;
    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public TelefonoFacadeLocal getTelefnoFacadeLocal() {
        return telefnoFacadeLocal;
    }

    public void setTelefnoFacadeLocal(TelefonoFacadeLocal telefnoFacadeLocal) {
        this.telefnoFacadeLocal = telefnoFacadeLocal;
    }

    public List<Telefono> getLsttelefono() {
        return lsttelefono;
    }

    public void setLsttelefono(List<Telefono> lsttelefono) {
        this.lsttelefono = lsttelefono;
    }

    public void buscar() {
        try {
            lsttelefono = telefnoFacadeLocal.buscar(codigoPersona);
        } catch (Exception e) {
        }
    }
}

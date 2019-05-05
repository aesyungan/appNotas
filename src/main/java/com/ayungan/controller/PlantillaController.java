/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.ejb.PersonaFacadeLocal;
import com.ayungan.ejb.UsuarioFacadeLocal;
import com.ayungan.model.Usuario;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class PlantillaController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadelocal;
    @EJB
    private PersonaFacadeLocal personaFacadeLocal;

    public void verificarSession() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (u == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../Permisos.xhtml");
            }
        } catch (Exception e) {
        }

    }

    public void generarPDF() {
        try {
            //System.out.println("" + FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator + "reportes" + File.separator + "personas.jasper"));
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator + "reportes" + File.separator + "personas.jasper"));
            Map<String, Object> parm = new HashMap<String, Object>();
            parm.put("txtUsuario", "Alex yungan");
            parm.put("txtCode", "12345-hola");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parm, new JRBeanCollectionDataSource(personaFacadeLocal.findAll()));

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment;filename=reportusuario.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("com.ayungan.controller.PlantillaController.generarPdf()");
            System.out.println("Error->" + e.getMessage());
        }
    }

    public void cerrarSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");

        } catch (Exception e) {
        }
    }
}

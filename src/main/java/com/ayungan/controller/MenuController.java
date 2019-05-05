/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.controller;

import com.ayungan.model.Menu;
import com.ayungan.ejb.MenuFacadeLocal;
import com.ayungan.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class MenuController implements Serializable {

    @EJB
    private MenuFacadeLocal menuFacadeLocal;
    private List<Menu> lstMenu;
    private MenuModel model;

    @PostConstruct
    public void init() {
        listarMenu();
        System.out.println("Init menu");
    }

    public void listarMenu() {
        try {
            model = new DefaultMenuModel();
            lstMenu = menuFacadeLocal.findAll();
            establecerPermisos();

        } catch (Exception e) {
            System.out.println("com.ayungan.controller.MenuController.listarMenu()");
            System.out.println("error ->" + e.getMessage());
        }
    }

    public void establecerPermisos() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

            for (Menu menu : lstMenu) {
                if (menu.getTipo().equals("S") && menu.getTipoUsuario().equals(u.getTipo())) {
                    DefaultSubMenu firstSubmenu = new DefaultSubMenu(menu.getNombre());
                    for (Menu i : lstMenu) {
                        Menu submenu = i.getSubmenu();
                        if (submenu != null) {
                            if (submenu.getCodigo() == menu.getCodigo()) {
                                DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
                                item.setUrl(i.getUrl());
                                //item.setIcon("pi pi-home");
                                firstSubmenu.addElement(item);
                            }
                        }
                    }
                    model.addElement(firstSubmenu);
                } else {
                    if (menu.getSubmenu() == null && menu.getTipoUsuario().equals(u.getTipo())) {
                        DefaultMenuItem item = new DefaultMenuItem(menu.getNombre());
                        item.setUrl(menu.getUrl());
                        model.addElement(item);
                    }
                }

            }
            DefaultMenuItem item = new DefaultMenuItem("Cerrar Session");
            item.setIcon("ui ui-power");
            item.setCommand("#{plantillaController.cerrarSession()}");
            model.addElement(item);
        } catch (Exception e) {
            System.out.println("com.ayungan.controller.MenuController.listarMenu()");
            System.out.println("error ->" + e.getMessage());
        }
    }

    public MenuFacadeLocal getMenuFacadeLocal() {
        return menuFacadeLocal;
    }

    public void setMenuFacadeLocal(MenuFacadeLocal menuFacadeLocal) {
        this.menuFacadeLocal = menuFacadeLocal;
    }

    public List<Menu> getLstMenu() {
        return lstMenu;
    }

    public void setLstMenu(List<Menu> lstMenu) {
        this.lstMenu = lstMenu;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public String nombreUsuario() {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return u.getUsuario();
    }
}

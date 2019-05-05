/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.ejb;

import com.ayungan.model.Telefono;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class TelefonoFacade extends AbstractFacade<Telefono> implements TelefonoFacadeLocal {

    @PersistenceContext(unitName = "PrimePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelefonoFacade() {
        super(Telefono.class);
    }

    @Override
    public List<Telefono> buscar(int idPersona) {
        List<Telefono> lst = new ArrayList<>();
        try {
            String consulta = "SELECT t FROM Telefono t WHERE t.persona.codigo=?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idPersona);
            lst = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}

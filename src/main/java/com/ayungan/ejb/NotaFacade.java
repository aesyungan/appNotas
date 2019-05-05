/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.ejb;

import com.ayungan.model.Nota;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Alex
 */
@Stateless
public class NotaFacade extends AbstractFacade<Nota> implements NotaFacadeLocal {

    @PersistenceContext(unitName = "PrimePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotaFacade() {
        super(Nota.class);
    }

    @Override
    public List<Nota> buscar(int idPersona, int idCategoria, Date fechaConsulta) throws Exception {
        List<Nota> lst = new ArrayList<>();
        try {
            String consulta = "SELECT n FROM Nota n WHERE n.persona.codigo=?1 and n.categoria.codigo=?2 and n.fecha between ?3 and ?4";

            Query query = em.createQuery(consulta);

            query.setParameter(1, idPersona);
            query.setParameter(2, idCategoria);
            query.setParameter(3, fechaConsulta, TemporalType.DATE);
            Calendar ca = Calendar.getInstance();
            ca.setTime(fechaConsulta);
            ca.add(Calendar.DATE, 1);
            query.setParameter(4, ca, TemporalType.DATE);
            lst = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

}

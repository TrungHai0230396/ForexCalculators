package com.example.curd.dao;

import java.util.List;

import com.example.curd.models.PipValuePerLot;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PipValuePerLotDao {
    @Autowired
	private SessionFactory sessionFactory;

	public List<PipValuePerLot> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PipValuePerLot> pipValuePerLots = session.createQuery("from PipValuePerLot", PipValuePerLot.class).getResultList();
        return pipValuePerLots;
	}

    public PipValuePerLot findById(final Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(PipValuePerLot.class, id);
	}
}

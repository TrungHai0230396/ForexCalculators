package com.example.curd.dao;


import java.util.List;

import com.example.curd.models.OrderInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class OrderInfoDao {
    @Autowired
	private SessionFactory sessionFactory;

	public void save(final OrderInfo orderInfo) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(orderInfo);
	}

	public void update(final OrderInfo orderInfo) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(orderInfo);
	}

	public OrderInfo findById(final Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(OrderInfo.class, id);
	}

	public void delete(final OrderInfo orderInfo) {
		Session session = this.sessionFactory.getCurrentSession();
		session.remove(orderInfo);
	}

	public List<OrderInfo> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<OrderInfo> orderInfos = session.createQuery("from OrderInfo", OrderInfo.class).getResultList();
		return orderInfos;
	}
}

package com.example.curd.services;

import java.util.List;

import com.example.curd.models.OrderInfo;

public interface OrderInfoService {
    public List<OrderInfo> findAll();
	public OrderInfo findById(final Long id);
	public void save(final OrderInfo orderInfo);
	public void update(final OrderInfo orderInfo);
	public void delete(final Long id);
    public OrderInfo calculateResult(OrderInfo orderInfo);
	public boolean existsById(Long id);
}

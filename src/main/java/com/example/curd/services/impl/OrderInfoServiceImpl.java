package com.example.curd.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.example.curd.dao.OrderInfoDao;
import com.example.curd.exception.ResourceNotFoundException;
import com.example.curd.models.OrderInfo;
import com.example.curd.services.OrderInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService{
    
	@Autowired
	private OrderInfoDao orderInfoDao;
	
    @Override
	public List<OrderInfo> findAll() {
		return orderInfoDao.findAll();
	}

    @Override
	public OrderInfo findById(final Long id) {
		OrderInfo orderInfo = orderInfoDao.findById(id);
		if (orderInfo == null) {
			throw new ResourceNotFoundException("OrderInfo", "Id", id);
		} else {
			return orderInfo;
		}
	}

    @Override
	public void save(final OrderInfo orderInfo) {
		orderInfoDao.save(orderInfo);
	}

    @Override
	public void update(final OrderInfo orderInfo) {
		orderInfoDao.update(orderInfo);
	}

    @Override
	public void delete(final Long id) {
		OrderInfo orderInfo = orderInfoDao.findById(id);
		if (orderInfo != null) {
			orderInfoDao.delete(orderInfo);
		}
	}
	
	@Override
	public boolean existsById(Long id) {
		try {
			findById(id);
			return true;
		} catch (ResourceNotFoundException e) {
			return false;
		}
	}

	@Override
	public OrderInfo calculateResult(OrderInfo orderInfo) {
        float pipValue = orderInfo.getPipValuePerLot().getPipValue();
		float amoutToRisk = orderInfo.getAmoutToRisk();
		String quoteCurrency = orderInfo.getPipValuePerLot().getCurrencyPair().split("/")[1];
		int size = 0;
		if ("JPY".equals(quoteCurrency)) {
			size = 100;
		} else {
			size = 10000;
		}
		float pip = Math.abs(orderInfo.getEntry() - orderInfo.getStopLoss())*size;
		float lotTarget= amoutToRisk/pip/pipValue;

		orderInfo.setPipValue(pipValue);
		orderInfo.setPip(pip);
		orderInfo.setLot(lotTarget);
		return orderInfo;
	}
}

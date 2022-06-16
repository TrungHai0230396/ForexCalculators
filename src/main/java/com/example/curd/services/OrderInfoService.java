package com.example.curd.services;

import org.springframework.stereotype.Service;

import com.example.curd.models.OrderInfo;

@Service
public class OrderInfoService implements IOrderInfoService{

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

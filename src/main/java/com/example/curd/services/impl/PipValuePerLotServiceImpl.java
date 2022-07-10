package com.example.curd.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.example.curd.dao.PipValuePerLotDao;
import com.example.curd.models.PipValuePerLot;
import com.example.curd.services.PipValuePerLotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PipValuePerLotServiceImpl implements PipValuePerLotService{

    @Autowired
	private PipValuePerLotDao pipValuePerLotDao;
    
    @Override
    public List<PipValuePerLot> findAll() {
        return pipValuePerLotDao.findAll();
    }

    @Override
	public PipValuePerLot findById(final Long id) {
		return pipValuePerLotDao.findById(id);
	}

    @Override
    public List<String> getCurrencyPairs() {
        // TODO Auto-generated method stub
        return null;
    }
    
}

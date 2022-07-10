package com.example.curd.services;

import java.util.List;

import com.example.curd.models.PipValuePerLot;

public interface PipValuePerLotService {
    public List<PipValuePerLot> findAll();
    public PipValuePerLot findById(final Long id);
    public List<String> getCurrencyPairs();
}

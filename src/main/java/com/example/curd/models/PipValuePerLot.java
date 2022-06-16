package com.example.curd.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pip_value_per_lot")
public class PipValuePerLot {
    @Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "currency_pair", nullable = false)
    private String currencyPair;

    @Column(name = "pip_value", nullable = false)
    private float pipValue;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pipValuePerLot")
    private Set<OrderInfo> calculator = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyPair() {
        return this.currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public float getPipValue() {
        return this.pipValue;
    }

    public void setPipValue(float pipValue) {
        this.pipValue = pipValue;
    }
}

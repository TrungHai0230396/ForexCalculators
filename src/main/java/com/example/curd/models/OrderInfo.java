package com.example.curd.models;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "order_info")
public class OrderInfo {
    @Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne()
    @JoinColumn(name = "currency_pair_id", nullable = false)
    private PipValuePerLot pipValuePerLot;

    @Column(name = "stop_loss", nullable = false)
    private float stopLoss;

    @Column(name = "entry", nullable = false)
    private float entry;

    @Column(name = "take_profit")
    private Float takeProfit;

    @Column(name = "lot", nullable = false)
    private float lot;

    @Column(name = "pip", nullable = false)
    private float pip;

    @Column(name = "account_balance")
    private float accountBalance;

    @Column(name = "risk_by_percent")
    private float riskByPercent;
    
    @Column(name = "amout_to_risk", nullable = false)
    private float amoutToRisk;

    @Column(name = "pip_value")
    private float pipValue;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition="DATETIME")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition="DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "result")
    private Float result;

    @Transient
    private Float resultRiskReward;
    public OrderInfo() {
    }
    
    public Float getResultRiskReward() {
        try {
            if (this.result != null) {
                DecimalFormat df = new DecimalFormat("#.#"); 
                return Float.valueOf(df.format(this.result/this.amoutToRisk));
            }
        }catch(Exception ex){
            return 0f;
        }
        return 0f;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PipValuePerLot getPipValuePerLot() {
        return this.pipValuePerLot;
    }

    public void setPipValuePerLot(PipValuePerLot pipValuePerLot) {
        this.pipValuePerLot = pipValuePerLot;
    }

    public float getStopLoss() {
        return this.stopLoss;
    }

    public void setStopLoss(float stopLoss) {
        this.stopLoss = stopLoss;
    }

    public float getEntry() {
        return this.entry;
    }

    public void setEntry(float entry) {
        this.entry = entry;
    }

    public Float getTakeProfit() {
        return this.takeProfit;
    }

    public void setTakeProfit(Float takeProfit) {
        this.takeProfit = takeProfit;
    }

    public float getLot() {
        return this.lot;
    }

    public void setLot(float lot) {
        this.lot = lot;
    }

    public float getPip() {
        return this.pip;
    }

    public void setPip(float pip) {
        this.pip = pip;
    }

    public float getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public float getRiskByPercent() {
        return this.riskByPercent;
    }

    public void setRiskByPercent(float riskByPercent) {
        this.riskByPercent = riskByPercent;
    }

    public float getAmoutToRisk() {
        return this.amoutToRisk;
    }

    public void setAmoutToRisk(float amoutToRisk) {
        this.amoutToRisk = amoutToRisk;
    }

    public float getPipValue() {
        return this.pipValue;
    }

    public void setPipValue(float pipValue) {
        this.pipValue = pipValue;
    }

    public LocalDateTime  getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime  createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime  getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime  updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Float getResult() {
        return this.result;
    }

    public void setResult(Float result) {
        this.result = result;
    }
}

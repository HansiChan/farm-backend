package com.dreamfarm.farmmain.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@DynamicUpdate
@Entity
@Table(name = "land_transaction")
public class LandTransaction {
    @Id
    @Column(name = "token_id")
    private int token_id;
    @Column(name = "transaction_hash")
    private String transaction_hash;
    @Column(name = "transaction_time")
    private long transaction_time;
    @Column(name = "buyer")
    private String buyer;
    @Column(name = "price")
    private double price;
    @Column(name = "sell_round")
    private int sell_round;

    public int getToken_id() {
        return token_id;
    }

    public void setToken_id(int token_id) {
        this.token_id = token_id;
    }

    public String getTransaction_hash() {
        return transaction_hash;
    }

    public void setTransaction_hash(String transaction_hash) {
        this.transaction_hash = transaction_hash;
    }

    public long getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(long transaction_time) {
        this.transaction_time = transaction_time;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSell_round() {
        return sell_round;
    }

    public void setSell_round(int sell_round) {
        this.sell_round = sell_round;
    }
}

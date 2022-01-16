package com.dreamfarm.farmmain.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@DynamicUpdate
@Entity
@Table(name = "pond_nft")
public class Pond {
    @Id
    @Column(name = "token_id")
    private int token_id;
    @Column(name = "rarity")
    private String rarity;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "create_time")
    private long create_time;

    public Pond(){

    }

    public Pond(int token_id, String rarity, int capacity, long create_time) {
        this.token_id = token_id;
        this.rarity = rarity;
        this.capacity = capacity;
        this.create_time = create_time;
    }

    public int getToken_id() {
        return token_id;
    }

    public void setToken_id(int token_id) {
        this.token_id = token_id;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}

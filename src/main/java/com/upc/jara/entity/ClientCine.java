package com.upc.jara.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientCine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jlId;
    private Long jlDni;
    private String jlName;
    private  int jlDependets;
    private int jlStock;
    private double jlCuotaNeta;

    public ClientCine() {
    }

    public ClientCine(Long jlId, Long jlDni, String jlName, int jlDependets, int jlStock, double jlCuotaNeta) {
        this.jlId = jlId;
        this.jlDni = jlDni;
        this.jlName = jlName;
        this.jlDependets = jlDependets;
        this.jlStock = jlStock;
        this.jlCuotaNeta = jlCuotaNeta;
    }

    public Long getJlId() {
        return jlId;
    }

    public void setJlId(Long jlId) {
        this.jlId = jlId;
    }

    public Long getJlDni() {
        return jlDni;
    }

    public void setJlDni(Long jlDni) {
        this.jlDni = jlDni;
    }

    public String getJlName() {
        return jlName;
    }

    public void setJlName(String jlName) {
        this.jlName = jlName;
    }

    public int getJlDependets() {
        return jlDependets;
    }

    public void setJlDependets(int jlDependets) {
        this.jlDependets = jlDependets;
    }

    public int getJlStock() {
        return jlStock;
    }

    public void setJlStock(int jlStock) {
        this.jlStock = jlStock;
    }

    public double getJlCuotaNeta() {
        return jlCuotaNeta;
    }

    public void setJlCuotaNeta(double jlCuotaNeta) {
        this.jlCuotaNeta = jlCuotaNeta;
    }

    @Override
    public String toString() {
        return "ClientCine{" +
                "jlId=" + jlId +
                ", jlDni=" + jlDni +
                ", jlName='" + jlName + '\'' +
                ", jlDependets=" + jlDependets +
                ", jlStock=" + jlStock +
                ", jlCuotaNeta=" + jlCuotaNeta +
                '}';
    }
}

package com.supermercado.model;

public class Cargo {
    int cargId;
    String cargNombre;

    public Cargo(int cargId, String cargNombre) {
        this.cargId = cargId;
        this.cargNombre = cargNombre;
    }

    public int getCargId() {
        return cargId;
    }

    public void setCargId(int cargId) {
        this.cargId = cargId;
    }

    public String getCargNombre() {
        return cargNombre;
    }

    public void setCargNombre(String cargNombre) {
        this.cargNombre = cargNombre;
    }
}

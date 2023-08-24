package com.simonsoft.conversormoneda.modelo;

import java.io.Serializable;

public class Moneda {
    private double euro;
    private double dolar;

    public Moneda(double euro, double dolar) {
        this.euro = euro;
        this.dolar = dolar;
    }
    public double convertirADolar(double euros){
        return euros * 1.09;
    }
    public double convertirAEuro(double dolares){
        return dolares * 0.92;
    }
}

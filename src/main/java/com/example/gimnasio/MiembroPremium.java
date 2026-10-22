package com.example.gimnasio;

public class MiembroPremium extends Miembro {
    private String entrenadorPersonal;

    public MiembroPremium(String nombre, int edad, String entrenadorPersonal) {
        super(nombre, edad);
        this.entrenadorPersonal = entrenadorPersonal;
    }

    public String getEntrenadorPersonal() {
        return entrenadorPersonal;
    }

    public void setEntrenadorPersonal(String entrenadorPersonal) {
        this.entrenadorPersonal = entrenadorPersonal;
    }

    @Override
    public double cuotaMensual() {
        return super.cuotaMensual() + 30000;
    }
}
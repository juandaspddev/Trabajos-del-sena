package com.example.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GimnasioController {

    private List<Miembro> miembros;
    private Map<String, String> horarios;

    public GimnasioController() {
        miembros = new ArrayList<>();
        miembros.add(new Miembro("Carlos", 30));
        miembros.add(new MiembroPremium("Ana", 25, "Juan Pérez"));
        miembros.add(new MiembroEstudiante("Luis", 20, "Universidad Nacional"));
        miembros.add(new Miembro("Sofia", 28));

        horarios = new HashMap<>();
        horarios.put("yoga", "Lunes y Miércoles 8:00 AM");
        horarios.put("spinning", "Martes y Jueves 6:00 PM");
        horarios.put("crossfit", "Viernes 7:00 PM");
    }

    @GetMapping("/miembros")
    public List<Miembro> obtenerMiembros() {
        return miembros;
    }

    @GetMapping("/miembros/buscar/{nombre}")
    public String buscarMiembro(@PathVariable String nombre) {
        for (Miembro m : miembros) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m.descripcion() + " - cuota mensual: $" + (int) m.cuotaMensual() + ".0";
            }
        }
        return "No se encontró al miembro " + nombre;
    }

    @GetMapping("/miembros/cuota/{minima}")
    public List<Miembro> obtenerMiembrosPorCuota(@PathVariable double minima) {
        List<Miembro> filtrados = new ArrayList<>();
        for (Miembro m : miembros) {
            if (m.cuotaMensual() >= minima) {
                filtrados.add(m);
            }
        }
        return filtrados;
    }

    @GetMapping("/clases/{nombre}")
    public String consultarHorario(@PathVariable String nombre) {
        String horario = horarios.get(nombre.toLowerCase());
        if (horario != null) {
            return horario;
        }
        return "Clase no disponible";
    }
}
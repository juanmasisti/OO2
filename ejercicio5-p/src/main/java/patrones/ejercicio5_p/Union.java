package patrones.ejercicio5_p;

import java.util.ArrayList;
import java.util.List;

public class Union extends ElementoQuimico {
    
    // Clase interna para manejar las cantidades
    private class Componente {
        ElementoQuimico elemento;
        int cantidad;
        Componente(ElementoQuimico e, int c) { elemento = e; cantidad = c; }
    }

    private List<Componente> componentes = new ArrayList<>();

    // El "mecanismo" de creación fluido
    public Union agregar(ElementoQuimico elemento, int cantidad) {
        this.componentes.add(new Componente(elemento, cantidad));
        return this; // Permite encadenar llamadas
    }

    @Override
    public int pesoMolecular() {
        return componentes.stream()
                .mapToInt(c -> c.elemento.pesoMolecular() * c.cantidad)
                .sum();
    }

    @Override
    public int carga() {
        return componentes.stream()
                .mapToInt(c -> c.elemento.carga() * c.cantidad)
                .sum();
    }

    @Override
    protected boolean tieneMetal() {
        return componentes.stream()
                .anyMatch(c -> c.elemento.tieneMetal());
    }

    @Override
    public boolean esValida() {
        // Regla: No puede haber más de 1 componente que aporte un metal
        long metales = componentes.stream()
                .filter(c -> c.elemento.tieneMetal())
                .count();
        
        if (metales > 1) return false;
        
        // La unión es válida solo si sus sub-partes también lo son
        return componentes.stream()
                .allMatch(c -> c.elemento.esValida());
    }

    @Override
    public String formula() {
        StringBuilder sb = new StringBuilder();
        for (Componente c : componentes) {
            // Si hay más de 1 cantidad y es una Unión, requiere paréntesis (ej: (OH)2 )
            if (c.cantidad > 1 && c.elemento.requiereParentesis()) {
                sb.append("(").append(c.elemento.formula()).append(")");
            } else {
                sb.append(c.elemento.formula());
            }
            // Agregamos el número si es mayor a 1 (ej: H2)
            if (c.cantidad > 1) {
                sb.append(c.cantidad);
            }
        }
        return sb.toString();
    }

    @Override
    protected boolean requiereParentesis() { return true; }
}
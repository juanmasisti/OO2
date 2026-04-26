package patrones.ejercicio4_p;

import java.util.Arrays;
import java.util.List;

public class Mixta extends Topografia {
    private List<Topografia> componentes;

    public Mixta(Topografia t1, Topografia t2, Topografia t3, Topografia t4) {
        // Guardamos las 4 partes en orden
        this.componentes = Arrays.asList(t1, t2, t3, t4);
    }

    @Override
    public double proporcionAgua() {
        // Sumamos las proporciones de los 4 componentes y dividimos por 4
        return this.componentes.stream()
                .mapToDouble(Topografia::proporcionAgua)
                .sum() / 4.0;
    }

    @Override
    public boolean equals(Object obj) {
    	// Optimización (Atajo de memoria)
        if (this == obj) return true;
        // Filtro de seguridad (Tipos)
        if (!(obj instanceof Mixta)) return false;
        
        // Recién acá podemos transformar (castear) y comparar la lógica de negocio
        Mixta otraMixta = (Mixta) obj;
        // Esto automáticamente llamará al equals de cada topografía hija,
        // logrando una comparación recursiva de todo el árbol.
        return this.componentes.equals(otraMixta.componentes);
    }
}

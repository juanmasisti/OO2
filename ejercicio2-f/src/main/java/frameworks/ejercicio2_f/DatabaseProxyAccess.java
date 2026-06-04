package frameworks.ejercicio2_f;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import patrones.ejercicio20_p.DatabaseAccess;
import patrones.ejercicio20_p.DatabaseRealAccess;

public class DatabaseProxyAccess implements DatabaseAccess {
    private DatabaseRealAccess realDatabase;
    private boolean isLogged;
    private String passwordGuardada;
    // 1. Instanciamos el Logger estático asociado a esta clase
    private static final Logger logger = Logger.getLogger(DatabaseProxyAccess.class.getName());

    public DatabaseProxyAccess(String password) {
        this.passwordGuardada = password;
        this.isLogged = false;
        // El proxy instancia o recibe el objeto real (Virtual Proxy vs Protection)
        // En este caso lo instanciamos acá.
        this.realDatabase = new DatabaseRealAccess(); 
    }

    public boolean login(String inputPassword) {
        if (this.passwordGuardada.equals(inputPassword)) {
            this.isLogged = true;
            return true;
        }
        return false;
    }

    public void logout() {
        this.isLogged = false;
    }

    private void checkAccess() {
        if (!this.isLogged) {
        	logger.severe("Intento de acceso denegado a la base de datos: Usuario no autenticado.");
            throw new RuntimeException("Acceso denegado: Usuario no autenticado.");
        }
    }

    @Override
    public Collection<String> getSearchResults(String queryString) {
        checkAccess();
        logger.info("Acceso autorizado (Búsqueda): " + queryString);
        // Si no explotó la excepción, delegamos al objeto real
        return this.realDatabase.getSearchResults(queryString);
    }

    @Override
    public int insertNewRow(List<String> rowData) {
        checkAccess();
        logger.warning("Acceso autorizado (Inserción): Se agregará una nueva fila a la DB.");
        // Delegamos al objeto real
        return this.realDatabase.insertNewRow(rowData);
    }
}
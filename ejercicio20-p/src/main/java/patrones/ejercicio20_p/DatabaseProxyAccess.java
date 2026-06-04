package patrones.ejercicio20_p;

import java.util.Collection;
import java.util.List;

public class DatabaseProxyAccess implements DatabaseAccess {
    private DatabaseRealAccess realDatabase;
    private boolean isLogged;
    private String passwordGuardada; // En un caso real esto sería un Hash

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
            throw new RuntimeException("Acceso denegado: Usuario no autenticado.");
        }
    }

    @Override
    public Collection<String> getSearchResults(String queryString) {
        checkAccess();
        // Si no explotó la excepción, delegamos al objeto real
        return this.realDatabase.getSearchResults(queryString);
    }

    @Override
    public int insertNewRow(List<String> rowData) {
        checkAccess();
        // Delegamos al objeto real
        return this.realDatabase.insertNewRow(rowData);
    }
}
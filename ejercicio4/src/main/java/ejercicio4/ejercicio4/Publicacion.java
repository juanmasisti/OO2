package ejercicio4.ejercicio4;

public class Publicacion {
	 private String texto;
	 private int likes;
	   public Publicacion(String texto) {
	      this.texto = texto;
	      this.likes = 0;
	   }
	   
	   public void darLike() { likes++; }
	   
	   public void darDislike() { likes--; }
	   
	   // Rename Method: Cambiar la firma del método de private int procesar() a private int impacto().
	   private int impacto() {
	       return likes * 3;
	   }
	   
	   // Rename Method: Cambiar la firma del método de public int calcular() a public int alcance().
	   public int alcance() {
		   // Cambiar la invocación dentro del método calcular().
	       return impacto() * 10;
	   }
}

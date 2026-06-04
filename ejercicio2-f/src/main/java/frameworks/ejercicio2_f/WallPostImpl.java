package frameworks.ejercicio2_f;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Completar esta clase de acuerdo a lo especificado en el cuadernillo
 *
 */
public class WallPostImpl implements WallPost {

	private String text;
	private int likes;
	private boolean isFeatured;
	// 1. Declaramos el Logger del modelo
	private static final Logger logger = Logger.getLogger(WallPostImpl.class.getName());

	public WallPostImpl() {
		this.text = "Undefined post";

	}

	public WallPostImpl(String text, int likes, boolean isFeaured) {
		this.text = text;
		this.likes = likes;
		this.isFeatured = isFeaured;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;

	}

	@Override
	public int getLikes() {
		return likes;
	}

	@Override
	public void like() {
		this.likes = this.likes + 1;
		// Requisito: Advertir cuando llegue a 10 likes
		if (this.likes == 10) {
			logger.warning("La publicación ha alcanzado los 10 likes.");
		}

	}

	@Override
	public void dislike() {
		if (likes > 0) {
			this.likes = this.likes - 1;
			// Requisito: Advertir cuando los dislikes hagan llegar a 0
			if (this.likes == 0) {
				logger.warning("La cantidad de likes de la publicación ha bajado a 0.");
			}
		}

	}

	@Override
	public boolean isFeatured() {
		return this.isFeatured;
	}

	@Override
	public void toggleFeatured() {
		this.isFeatured = !this.isFeatured;

	}

	/*
	 * Este mensaje se utiliza para que una instancia de Wallpost se muestre de
	 * forma adecuada
	 */

	@Override
	public String toString() {
		return "WallPost {" + "text: " + getText() + ", likes: '" + getLikes() + "'" + ", featured: '" + isFeatured()
				+ "'" + "}";
	}

}

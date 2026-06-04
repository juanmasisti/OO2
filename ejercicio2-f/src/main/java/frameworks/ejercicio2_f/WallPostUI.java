package frameworks.ejercicio2_f;

import java.awt.GridLayout;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class WallPostUI {

  private WallPost wallPost;
  private JTextArea textArea;
  private JLabel featuredLabelTitle;
  private JCheckBox featuredCheckbox;
  private JLabel likesLabelTitle;
  private JLabel likesLabel;
  private JButton like; 
  private JButton dislike; 
  private JFrame window;

  //1. Declaramos el Logger de la UI
  private static final Logger logger = Logger.getLogger(WallPostUI.class.getName());
 
  public WallPostUI() {
	// Requisito: Registrar inicio de ejecución
	logger.info("Iniciando la aplicación visual de WallPost...");
	
    this.wallPost = new WallPostImpl();
    this.textArea = new JTextArea();
    this.featuredLabelTitle = new JLabel("Featured");
    this.featuredCheckbox = new JCheckBox();
    this.likesLabelTitle = new JLabel("Likes");
    this.like = new JButton("Like");
    this.likesLabel = new JLabel();
    this.dislike = new JButton("Dislike");
    this.window = new JFrame("WallPost");
    this.setUpWindow();
    this.wireComponents();
    this.window.setVisible(true);
  }
  
  private void setUpWindow() {
    JPanel pane = new JPanel();
    this.window.getContentPane().add(pane);
    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.window.setSize(640, 480);
    pane.setLayout(new GridLayout(5,1,1,10));
    pane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
   
    JScrollPane scrollPane = new JScrollPane(this.textArea);
    pane.add(scrollPane);
    this.textArea.setEditable(true);
    this.textArea.setLineWrap(true);
    this.textArea.setWrapStyleWord(true);
    
    JPanel featuredPane = new JPanel();
    featuredPane.setLayout(new GridLayout(1, 2));
    featuredPane.add(this.featuredLabelTitle);
    featuredPane.add(this.featuredCheckbox);
    pane.add(featuredPane);
    
    JPanel likesPane = new JPanel();
    likesPane.setLayout(new GridLayout(1, 2));
    likesPane.add(this.likesLabelTitle);
    likesPane.add(this.likesLabel);
    likesLabel.setText("0");
    pane.add(likesPane);
    
    JPanel likeButtonsPane = new JPanel();
    likeButtonsPane.setLayout(new GridLayout(1, 2));
    likeButtonsPane.add(this.like);
    likeButtonsPane.add(this.dislike);
    pane.add(likeButtonsPane);
  
    this.window.pack();
  }
  
  private void wireComponents() {
    this.like.addActionListener( e -> {
      // Requisito: Registrar interacción (Like)
      logger.info("El usuario clickeó el botón 'Like'.");
      this.wallPost.like();
      this.likesLabel.setText(String.valueOf(this.wallPost.getLikes()));
    });
    
    this.dislike.addActionListener( e -> {
      // Requisito: Registrar interacción (disLike)
      logger.info("El usuario clickeó el botón 'Dislike'. ");
      this.wallPost.dislike();
      this.likesLabel.setText(String.valueOf(this.wallPost.getLikes()));
    });
    
    this.featuredCheckbox.addActionListener(e -> {
      // Requisito: Registrar interacción (Checkbox)
      logger.info("El usuario modificó el estado 'Featured'.");
      this.wallPost.toggleFeatured();
    });
    
    this.textArea.getDocument().addDocumentListener(new DocumentListener() {
      
    	@Override
        public void removeUpdate(DocumentEvent e) {
          logger.info("El usuario eliminó texto de la publicación.");
          wallPost.setText(textArea.getText());
        }
        
        @Override
        public void insertUpdate(DocumentEvent e) {
          logger.info("El usuario insertó texto en la publicación.");
          wallPost.setText(textArea.getText());
        }
        
        @Override
        public void changedUpdate(DocumentEvent e) {
          logger.info("El usuario modificó el formato del texto de la publicación.");
          wallPost.setText(textArea.getText());
        }
    });
    
    
  }
}

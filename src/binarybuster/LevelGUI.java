package binarybuster;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * Klasa opisująca i tworząca JTextField z tekstem prezentującym aktualny poziom rozgrywki
 * @author Dominika Wysocka
 */
public class LevelGUI extends JTextField{
  
    /** ścieżka dostepu do czcionki */    
    private URL fontUrl;
    
    /** czcionka dla bloczków */
    public Font myFont;
    
    /** pole przechowujące aktualny numer poziomu */
    public int lvl = 1;    
    
    /**
     * Konstruktur opisujący dodatkowe parametry JTextField'a
     * @param jp JPanel przesłany z klasy MainWindow do którego dodamy pasek z zegarem     
     */
    public LevelGUI(JPanel jp){        
        super("Level 1");
        
        this.setBackground(null);
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setBorder(null);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setFocusable(false);
        this.setOpaque(false);
        newFont();
        this.setFont(myFont);
        
        jp.add(this, new AbsoluteConstraints(575, 5, 130, 40));
        jp.setComponentZOrder(this, 1);
        jp.repaint();
    }
    
    /**
     * Metoda aktualizuje numer poziomu i wyświetlany tekst
     */
    public void addLevel(){
        this.lvl++;        
        this.setText("Level " + Integer.toString(this.lvl));
    }
    
    /**
     * Ładuje z pliku i ustawia nową czcionkę
     */
    public void newFont(){
        try {            
            fontUrl = getClass().getResource("img/kimberley.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontUrl.getPath())).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(myFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
package binarybuster;

import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * Klasa opisująca i tworząca JLabela z grafiką przedstawiającą aktualną liczbą żyć
 * @author Dominika Wysocka
 */
public class LifesGUI extends JLabel{
    
    /** pole przechowujące aktualną liczbę żyć*/
    public int currentLifes = 3;
    
    /** kopia przycisku pojawiającego się po skończonym czasie */
    private JButton btnCopy;
    
    /**
     * Konstruktur zegara
     * @param jp JPanel przesłany z klasy MainWindow do którego dodamy grafikę z aktualnym stanem żyć
     * @param btn JButton przesłany z klasy MainWindow; pojawia się w momencie, kiedy gracz utraci wszystkie życia
     */
    public LifesGUI(JPanel jp, JButton btn){
        super();
        
        btnCopy = btn;
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setOpaque(false);        
        this.setIcon(new ImageIcon(getClass().getResource("img/three.png")));
        
        jp.add(this, new AbsoluteConstraints(575, 85, 130, 40));        
        jp.setComponentZOrder(this, 1);        
        jp.repaint();
    }

    /**
     * metoda ustawia odpowiednią grafikę w zależności o liczby żyć     
     */
    public void updateIcon(){
        currentLifes--;
        
        if(currentLifes == 3){
            this.setIcon(new ImageIcon(getClass().getResource("img/three.png")));
        }
        else if(currentLifes == 2){
            this.setIcon(new ImageIcon(getClass().getResource("img/two.png")));
        }
        else if(currentLifes == 1){
            this.setIcon(new ImageIcon(getClass().getResource("img/one.png")));
        }
        else if(currentLifes == 0){
            this.setIcon(null);            
            btnCopy.setVisible(true);
        }
    }
}
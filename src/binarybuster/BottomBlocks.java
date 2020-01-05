package binarybuster;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * Klasa opisująca dolne bloczki zawierająca odpowiedzi
 * @author Dominika Wysocka
 */
public class BottomBlocks extends Blocks{

    /**
    * Konstruktor dolnego bloczka 
    * @param jp przesłany JPanel z klasy MainWindow w celu dodania do panelu bloczków        
    * @param pos jest aktualnym indeksem dolnego bloczka; w zależności od niego obliczana jest pozycja na ekranie i nadawana jest odpowiednia tekstura
    */
    BottomBlocks(JPanel jp, int pos){
        super(jp);
        
        this.setOpaque(false);
        this.generateNumbers(pos);
        this.ganerateSystems(pos);
        this.setFont(myFont);        
        this.setBackground(null);
                
        switch(pos){
            case 1:
                this.setIcon(new ImageIcon(getClass().getResource("img/block1.png")));
                break;
            
            case 2:
                this.setIcon(new ImageIcon(getClass().getResource("img/block2.png")));
                break;
            
            case 3:
                this.setIcon(new ImageIcon(getClass().getResource("img/block3.png")));
                break;
            
            case 4:
                this.setIcon(new ImageIcon(getClass().getResource("img/block4.png")));
                break;
        }        
        
        this.setterText(pos);
        jp.add(this, new AbsoluteConstraints(30 + (pos-1)*320, 590, 260, 130));
        jp.setComponentZOrder(this, 1);
        
        jp.repaint();
    }
    
    /**
    * Pobiera przekonwertowaną liczbę i ustawia jako tekst bloczka
    * @param i indeks aktualnego bloczka któremu ustawiany jest tekst (pobrany z klasy nadrzędnej)
    */
    public void setterText(int i){
        this.setText(getNumber(i));
    }
    
}
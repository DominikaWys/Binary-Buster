package binarybuster;

import java.awt.event.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * Klasa opisująca poruszająca się platformy oraz jej wpływ na inne obiekty (poziom, życie, czas)
 * @author Dominika Wysocka
 */
public class FloatingBlock extends Blocks implements ActionListener{
        
    /** maksymalna, stała prędkość horyzontalna */
    private final int MAX_SPEED_X = 5;
    
    /** maksymalna, stała prędkość wertykalna */
    private final int MAX_SPEED_Y = 12;
    
    /** startowa pozycja X */
    public int xpos = 575;
    
    /** startowa pozycja X */
    public int ypos = 200;
    
    /** aktualna prędkość horyzontalna */
    public int xAdd = MAX_SPEED_X;
            
    /** aktualna prędkość horyzontalna */
    public int yAdd = 0; 
    
    /** flaga oznaczająca, czy w danej chwili platforma opuszza się */
    public boolean fallingFlag = false;
    
    /** flaga oznaczająca, czy został spełniony warunek pozytywnego ukończenia poziomu */
    public boolean newLevel = false;
    
    /** flaga oznaczająca, czy w danej chwili platforma może się poruszać */
    public boolean canMove = false;
    
    /** zegar, który odmierza określony czas */
    public Timer timer;
    
    /** kopia obiektu wyświetlającego liczbę żyć */
    public LifesGUI lifesCopy;
    
    /** kopia obiektu wyświetlającego numer poziomu*/
    public LevelGUI levelCopy;
       
    /**
    * Konstruktor poruszającej się platformy
    * @param jp przesłany JPanel z klasy MainWindow w celu dodania do panelu bloczków        
    * @param lifes przesłany JLabel w celu aktualizaji jego grafiki w zależności od błędnej odpowiedzi
    * @param level przesłany JLabel w celu aktualizaji numeru poziomu rozgrywki     
    */
    FloatingBlock(JPanel jp, LifesGUI lifes, LevelGUI level){
        super(jp);
        
        levelCopy = level;
        lifesCopy = lifes;
        
        this.setIcon(new ImageIcon(getClass().getResource("img/platform.png")));
        this.setFont(myFont);
        this.setOpaque(false);
        this.generateNumbers(0);
        this.ganerateSystems(0);
        this.setterText();
        this.setBackground(null);
        this.setIcon(new ImageIcon(getClass().getResource("img/platform.png")));        
        
        jp.add(this, new AbsoluteConstraints(xpos, ypos, 130, 80));
        jp.setComponentZOrder(this, 1);
        jp.repaint();
        
        timer = new Timer(15, this);
        timer.start();
    }
    
    /**
    * Pobiera przekonwertowaną liczbę i ustawia jako tekst bloczka    
    */    
    public void setterText(){
        this.setText(getNumber(0));
    }        
    
    /**
     * Cykliczna metoda odpowiadająca za automatyczne poruszanie się platformy oraz jej opuszczanie przy spełnionym warunku 
     */
    public void actionPerformed(ActionEvent evt){        
        if(canMove){
            if(this.getLocation().x == -15)
                xAdd = MAX_SPEED_X;
            else if (this.getLocation().x == 1165)
                xAdd = -MAX_SPEED_X;

            if(fallingFlag){
                if(this.getLocation().y + this.getHeight() <= 610){
                    yAdd = MAX_SPEED_Y;
                    xAdd = 0;                    
                }
                else
                    collisionDetection();
            }

            this.setLocation(this.getLocation().x + xAdd, this.getLocation().y + yAdd);
        }
    }

    /**
     * Metoda sprawdzająca kolizję platformy z dolnymi bloczkami - centrum platformy nie może wychodzić poza krawendzie dolnego bloczka     
     */
    private void collisionDetection(){
        int x = this.getLocation().x;        
        int width = this.getWidth();        
        int xCenter = x + width/2;        
     
        if(xCenter >= 30 && xCenter <= 290){
            if(isCorrect(1))                
                newLevel = true;
            else                
                lifesCopy.updateIcon();
        }
        else if(xCenter >= 350 && xCenter <= 610){                        
            if(isCorrect(2))
                newLevel = true;
            else
                lifesCopy.updateIcon();
        }
        else if(xCenter >= 670 && xCenter <= 930){
            if(isCorrect(3))
                newLevel = true;
            else
                lifesCopy.updateIcon();
        }
        else if(xCenter >= 990 && xCenter <= 1250){
            if(isCorrect(4))
                newLevel = true;
            else
                lifesCopy.updateIcon();
        }
        else{
            lifesCopy.updateIcon();
        }            
        
        resetParameters();
    }
    
    /**
     * Resetowanie do ustawień początkowych
     */
    private void resetParameters(){
        yAdd = 0;
        xAdd = MAX_SPEED_X;
        fallingFlag = false;
        this.setLocation(575, 200);
    }
}
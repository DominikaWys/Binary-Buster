package binarybuster;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Klasa opisująca interfejs graficzny zegara odliczającego czas
 * @author Dominika Wysocka
 */
public class GameTimer extends JLabel implements ActionListener{
    
    /** zegar, który odmierza określony czas */
    public Timer timer;
    
    /** kopia przycisku pojawiającego się po skończonym czasie */
    private JButton btnCopy;
    
    /** opóźnienie z jakim działa zegar w ms */
    private final int DELAY = 100;
    
    /** stały, maksymalny czas używany przy resetowaniu remainingTime */
    private final int MAX_TIME = 3000;
    
    /** stała, maksymalna szerokość paska czasu */
    private final int MAX_WIDTH = 1200;
    
    /** pole przechowujące wartość pozostałego czasu do końca tury */
    private int remainingTime = 3000;
    
    /** flaga opisująca stan możliwości odliczania czasu */
    public boolean canCount = true;
    
    /** obiekt odtwarzający dźwięk minutnika */
    public AudioStream audio;
    
    /**
     * Konstruktur zegara
     * @param jp JPanel przesłany z klasy MainWindow do którego dodamy pasek z zegarem
     * @param btn JButton przesłany z klasy MainWindow; pojawia się w momencie, kiedy skończy się czas
     */
    public GameTimer(JPanel jp, JButton btn){
        super();
        btnCopy = btn;
                
        this.setSize(MAX_WIDTH, this.getHeight());
        this.setLocation((1280-MAX_WIDTH)/2, this.getLocation().y);
        this.setOpaque(true);
        this.setBackground(new Color(255, 255, 255));
        this.playSound();

        jp.add(this, new AbsoluteConstraints(40, 45, MAX_WIDTH, 30));
        jp.setComponentZOrder(this, 1);
        jp.repaint();

        timer = new javax.swing.Timer(DELAY, this);
        timer.start();
    }
    
    /**
     * Cykliczna metoda sprawdzająca, czy możliwe jest dalsze odliczanie czasu; jeśli tak, wywoływana jest metoda odejmująca pozostały czas
     */
    public void actionPerformed(ActionEvent evt){
        if(canCount){            
            countDown();   
            
            if(btnCopy.isVisible()){
                changeFlag(false);
            }
        }
    }
    
    /**
     * metoda odgrywająca dźwięk minutnika     
     */
    private void playSound(){
        InputStream input;                
        URL path;
        
        try{ 
            path = this.getClass().getResource("sound/sound.wav");
            input = new FileInputStream(path.getFile());
            
            audio = new AudioStream(input);
            AudioPlayer.player.start(audio);
        }catch(Exception e){
            e.printStackTrace();
        }    
    }
    
    /**
     * metoda zmieniająca wartość logiczną flagi
     * @param flag flaga odpowiedzialna za możliwość odliczania czasu
     */
    public void changeFlag(boolean flag){
        canCount = flag;
    }
    
    /**
     * Metoda wywoływana co określone opóźnienie; aktualizuje pozostały czas gracza; ustawia szerokość paska zegara
     */
    private void countDown(){
        if(remainingTime >= 1){

            if(remainingTime <= 700)
                this.setBackground(new Color(255, 50, 70));
            
            remainingTime -= 10;
            this.setSize(this.getWidth()-4, this.getHeight());
            this.setLocation(this.getLocation().x+2, this.getLocation().y);
        }
        else{
            changeFlag(false);
            btnCopy.setVisible(true);
            AudioPlayer.player.stop(audio);
        }
    }
    
    /**
     * Resetowanie do ustawień początkowych
     */
    public void resetTimer(){
        remainingTime = MAX_TIME;
        this.playSound();
        this.setSize(MAX_WIDTH, this.getHeight());
        this.setLocation(40, 45);
        this.setBackground(new Color(255, 255, 255));
    }
}
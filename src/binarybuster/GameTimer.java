package binarybuster;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import sun.audio.AudioStream;


public class GameTimer extends JLabel implements ActionListener{
    public Timer timer;
    private JButton btnCopy;
    private final int DELAY = 100;
    private final int MAX_TIME = 3000;
    private final int MAX_WIDTH = 1200;
    private int remainingTime = 3000;
    public boolean canCount = true;
    public AudioStream audio;
    
    public GameTimer(JPanel jp, JButton btn){
        super();
        
        this.setSize(MAX_WIDTH, this.getHeight());
        this.setLocation((1280-MAX_WIDTH)/2, this.getLocation().y);
        this.setOpaque(true);
        this.setBackground(new Color(255, 255, 255));
        
        this.btnCopy = btn;
        
        jp.add(this, new AbsoluteConstraints(40, 45, MAX_WIDTH, 30));
        jp.setComponentZOrder(this, 1);
        jp.repaint();
        
        timer = new javax.swing.Timer(DELAY, this);
        timer.start();
    }
    
    public void actionPerformed(ActionEvent evt){
        if(canCount){
            countDown();
            
            if(btnCopy.isVisible()){
                changeFlag(false);
            }
        }
    }
    
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
        }
    }
    
    public void changeFlag(boolean flag){
        canCount = flag;
    }
    
    public void resetTimer(){
        remainingTime = MAX_TIME;
        this.setSize(MAX_WIDTH, this.getHeight());
        this.setLocation(40, 45);
        this.setBackground(new Color(255, 255, 255));
    }
}

package binarybuster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;


public class FloatingBlock extends AbstractBlocks implements ActionListener{

    private final int MAX_SPEED_X = 5, MAX_SPEED_Y = 12;
    public int xpos = 0, ypos = 200;
    public int xAdd = MAX_SPEED_X, yAdd = 0;
    public boolean fallingFlag = false;
    public boolean canMove = false;
    public boolean newLevel = false;
    public Timer timer;
    
    FloatingBlock(JPanel jp, int pos) {
        super(jp);
        
        //this.setIcon(new ImageIcon(getClass().getResource("img/platform.png")));
        this.setFont(myFont);
        this.setOpaque(true);
        this.generateNumbers(0);
        this.generateSystems(0);
        this.setterText();
        this.setBackground(null);
        this.setIcon(new ImageIcon(getClass().getResource("img/platform.png")));
        
        jp.add(this, new AbsoluteConstraints(xpos, ypos, 160, 110));
        jp.setComponentZOrder(this, 1);
        jp.repaint();
        
        timer = new Timer(4, this);
        timer.start();
    }
    
    public void setterText(){
        this.setText(getNumber(0));
    }
    
    public void actionPerformed(ActionEvent evt){
        timer.start();
        
        if(this.getLocation().x == 0){
            xpos = 1;
            System.out.println(xpos);
        }
        else if (this.getLocation().x == 1130){
            xpos = -1;
            System.out.println(xpos);
        }
        
        this.setLocation(this.getLocation().x + xpos, ypos);
    }  
    
    
}

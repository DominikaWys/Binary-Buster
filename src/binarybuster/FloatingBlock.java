package binarybuster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class FloatingBlock extends AbstractBlocks implements ActionListener{

    public int xpos = 0, ypos = 200;
    public Timer timer;
    
    FloatingBlock(JPanel jp, int pos) {
        super(jp);
        
        this.setOpaque(true);
        jp.add(this, new org.netbeans.lib.awtextra.AbsoluteConstraints(xpos, ypos, 150, 50));
        
        jp.repaint();
        timer = new Timer(3, this);
        timer.start();
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

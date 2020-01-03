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
    public boolean canMove = true;
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
        //jp.setComponentZOrder(this, 1);
        jp.repaint();
        
        timer = new Timer(3, this);
        timer.start();   
    }
    
    public void setterText(){
        this.setText(getNumber(0));
    }
    
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
    
    private void resetParameters(){
        yAdd = 0;
        xAdd = MAX_SPEED_X;
        fallingFlag = false;
        this.setLocation(0, 200);
    }
    
    private void collisionDetection(){
        int x = this.getLocation().x;
        int width = this.getWidth();
        int xCenter = x + width/2;
        
        if(xCenter >= 30 && xCenter <= 290){                               
            System.out.println("hit 1. block");                
            resetParameters();
        }
        else if(xCenter >= 350 && xCenter <= 610){                
            System.out.println("hit 2. block");
            resetParameters();
        }
        else if(xCenter >= 670 && xCenter <= 930){                
            System.out.println("hit 3. block");
            resetParameters();
        }
        else if(xCenter >= 990 && xCenter <= 1250){                
            System.out.println("hit 4. block");
            resetParameters();
        }  
    }
}

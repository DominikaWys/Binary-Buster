package binarybuster;

import javax.swing.JPanel;


public class BottomBlocks extends AbstractBlocks{
    
    BottomBlocks(JPanel jpB, int pos){
        super(jpB);
        
        this.setOpaque(true);
        jpB.add(this, new org.netbeans.lib.awtextra.AbsoluteConstraints(60 + pos*320, 620, 200, 100)); 
        jpB.repaint();
    }
}

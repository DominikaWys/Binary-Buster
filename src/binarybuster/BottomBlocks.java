package binarybuster;

import javax.swing.JPanel;


public class BottomBlocks extends AbstractBlocks{
    
    BottomBlocks(JPanel jp, int pos){
        super(jp);
        
        this.setOpaque(true);
        jp.add(this, new org.netbeans.lib.awtextra.AbsoluteConstraints(60 + pos*320, 620, 200, 100)); 
        jp.repaint();
    }
}

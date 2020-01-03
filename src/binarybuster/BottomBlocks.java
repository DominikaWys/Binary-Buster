package binarybuster;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;


public class BottomBlocks extends AbstractBlocks{
    
    BottomBlocks(JPanel jp, int pos){
        super(jp);
        
        this.setOpaque(true);
        this.generateNumbers(pos);
        this.generateSystems(pos);
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
        //jp.setComponentZOrder(this, 1);
        jp.repaint();
    }
    
    public void setterText(int i){
        this.setText(getNumber(i));
    }
}

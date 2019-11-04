package binarybuster;

import javax.swing.JLabel;
import javax.swing.JPanel;


public abstract class AbstractBlocks extends JLabel {
    private int width;
    private int height;
    public int number;
    
    AbstractBlocks(JPanel jpanelA){
        super("napis");
        this.setHorizontalAlignment(CENTER);
        
    }
}

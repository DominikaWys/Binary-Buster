package binarybuster;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;


public abstract class AbstractBlocks extends JLabel {
    public Font myFont;
    private URL fontUrl;
    Random randomClone = new Random();
    Random randomNumber = new Random();
    Random randomSystem = new Random();
    public static int floatingNumber = 0, cloneOrder = 0, range = 10;
    static private int generatedNumbers [] = new int[5], systemsQueue [] = new int[5];
    static private boolean canRepeat;
    
    
    AbstractBlocks(JPanel jp){
        super();
        this.setForeground(Color.WHITE);
        this.setBackground(null);        
        this.setOpaque(false);
        this.setFocusable(false);
        this.setVerticalTextPosition(CENTER);
        this.setHorizontalTextPosition(CENTER);
        canRepeat = true;
        newFont();       
    }
    
    public int cloneAnswer(){
        return randomClone.nextInt(4) + 1;
    }
    
    public void setRange(){
        range += 2;
    }
    
    private void resetValues(){
        floatingNumber = -1;
        cloneOrder = -1;
        canRepeat = true;
        
        for (int i = 0; i < 5; i++){
            generatedNumbers[i] = -1;
            systemsQueue[i]= -1;
        }
    }
    //------------------------------------------------------
    public void generateNumbers(int order){
        int MIN = 1, MAX = range, tmp;
        
        if(order == 0)
            resetValues();
        
        if (order == 0){
            floatingNumber = (int)(Math.random() * ((MAX - MIN) + 1)) + MIN;
            generatedNumbers[order] = floatingNumber;
            cloneOrder = cloneAnswer();
        }
        else if (order == cloneOrder){
            generatedNumbers[cloneOrder] = floatingNumber;
        }
        else{
            tmp = randomNumber.nextInt((MAX - MIN) +1) + MIN;
            
            for (int i =1; i <= order; i++){
                if (i == cloneOrder){
                    continue;
                }
                
                if (tmp == generatedNumbers[i] || tmp == generatedNumbers[0]){
                    i = 0;
                    tmp = randomNumber.nextInt((MAX - MIN) + 1) + MIN;
                }
            }
            
            generatedNumbers[order] = tmp;
        }
    }
    
    public void generateSystems(int order){
        int MAX =3, MIN = 0;
        int answerSys, tmp;
        
        if(order == 0){
            answerSys = randomSystem.nextInt((MAX - MIN) + 1) + MIN;
            systemsQueue[0] = answerSys;
        }
        else if(order == 1){
            tmp = randomSystem.nextInt((MAX - MIN) +1) + MIN;
            
            while(tmp == systemsQueue[0])
                tmp = randomSystem.nextInt((MAX - MIN) +1) + MIN;
            
            systemsQueue[order] = tmp;
        }
        else{
            tmp = randomSystem.nextInt((MAX - MIN) +1) + MIN;
            
            for(int i=1; i< order; i++){
                
                if(tmp != systemsQueue[0]){
                    if(tmp == systemsQueue[i]){
                      if(!canRepeat){
                            i=0;
                            tmp = randomSystem.nextInt((MAX - MIN) +1) + MIN;
                        }
                        else{
                            canRepeat = false;
                        }  
                    }
                }
                else{
                    i = 0;
                    tmp = randomSystem.nextInt((MAX - MIN) +1) + MIN;
                }
            }
            systemsQueue[order]=tmp;
        }
    }
    
    public void generateIndex(){
        floatingNumber = randomSystem.nextInt(4);
        generatedNumbers[0] = floatingNumber;
    }
    
    public boolean isCorrect(int hitBox){
        int question = generatedNumbers[0];
        int answer = generatedNumbers[hitBox];
        
        if(question == answer)
            return true;
        else
            return false;
    }
    
    public String getNumber(int order){
        String returnValue = " ";
        
        switch(systemsQueue[order]){
            case 0:
                returnValue = Integer.toBinaryString(generatedNumbers[order]) + " (2)";
                break;
            case 1:
                returnValue = Integer.toOctalString(generatedNumbers[order]) + " (8)";
                break;
            case 2:
                returnValue = Integer.toString(generatedNumbers[order]) + " (10)";
                break;
            case 3:
                returnValue = Integer.toHexString(generatedNumbers[order]) + " (16)";
                break;
        }
        return returnValue;
    }
    
    public void newFont(){
        try{
            fontUrl = getClass().getResource("img/kimberley-bl.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontUrl.getPath())).deriveFont(22f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(myFont);
        }catch (IOException e){
            e.printStackTrace();
        }catch (FontFormatException e){
            e.printStackTrace();
        }
        
    }
}

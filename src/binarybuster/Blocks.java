package binarybuster;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import java.util.Random;
import static javax.swing.SwingConstants.CENTER;

/**
 * Klasa zawierająca metody i pola używane przez obiekty klas podrzędnych - BottomBlocks i FloatingBlock
 * @author Dominika Wysocka
 */
public abstract class Blocks extends JLabel{
    
    /** ścieżka dostepu do czcionki */
    private URL fontUrl;
    
    /** czcionka dla bloczków */
    public Font myFont;
    
    /** randomowa liczba wyświetlana na bloczkach */
    Random randomNumber = new Random();
    
    /** randomowa pozycja poprawnej odpowiedzi */
    Random randomClone = new Random();
    
    /** randomowy system konwersji liczby */
    Random randomSystem = new Random();    
    
    /** statyczne pole do przechowywania wartości z poruszającego się bloczka */
    private static int floatingNumber = 0;
    
    /** statyczne pole do przechowywania indeksu dolnego bloczka w którym będzie poprawna odpowiedź */
    private static int cloneOrder = 0;
    
    /** statyczna tablica przechowująca liczby (w kolejności) wyświetlane na bloczkach podczas każdej rozgrywki */
    static private int generatedNumbers [] = new int[5];
    
    /** statyczna tablica przechowująca systemy liczbowe (w kolejności) na które będą konwertowane liczby */
    static private int systemsQueue [] = new int[5];
    
    /** pole przechowujące informację o możliwości wystąpienia powrótki systemu liczbowego w danej rozgrywce (jest 5 bloczków i 4 systemy liczbowe) */
    static private boolean canRepeat = true;

    /** maksymalny, początkowy zakres liczb */
    public static int range = 10;
    
    /**
    * Konstruktor klasy nadrzędnej dla BottomBlocks i FloatingBlock    
    * @param jpanel przesłany JPanel z klasy MainWindow w celu dodania do panelu bloczków        
    */
    Blocks(JPanel jpanel){
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
       
    /**     
     * @return zwraca losowy numer indeksu bloczka na którym będzie prawidłowa odpowiedź
     */
    public int cloneAnswer(){
        return randomClone.nextInt(4) + 1;
    }
    
    /**
     * Metoda zwiększająca zakres liczb
     */
    public void setRange(){
        range += 2;
    }
    
    /**
     *  nadpisuje wszsytkie wartości po każdym poziomie
     */
    private void resetValues(){
        floatingNumber = -1;
        cloneOrder = -1;
        canRepeat = true;
        
        for(int i = 0; i < 5; i++){
            generatedNumbers[i] = -1;
            systemsQueue[i] = -1;
        }
    }
    
    /**
     * generuje liczbę dla aktualnego bloczka; sprawdza czy dana liczba się nie powrótrzyła
     * @param order numer bloczka
     */
    public void generateNumbers(int order){
        int MIN = 1, MAX = range, tmp;
        
        if(order == 0)
            resetValues();
        
        //System.out.println("===================\nCURRENT ORDER = " + order);
        
        if(order == 0){
            floatingNumber = (int)(Math.random() * ((MAX - MIN) + 1)) + MIN;
            generatedNumbers[order] = floatingNumber;
            cloneOrder = cloneAnswer();
            //System.out.println("Clone order = " + cloneOrder);
        }
        else if(order == cloneOrder){
            generatedNumbers[cloneOrder] = floatingNumber;
            //System.out.println("Clone here = " + cloneOrder);
        }
        else{
            tmp = randomNumber.nextInt((MAX - MIN) + 1) + MIN;

            
            for(int i = 1; i <= order; i++){
                //System.out.println("gn = " + tmp + " | test = " + generatedNumbers[i]);
                if(i == cloneOrder){
                    //System.out.println("BEZ i = " + i);
                    continue;                    
                }
                    
                //System.out.println("  Z i = " + i + " | tmp = " + tmp + " | gn["+i+"] = " + generatedNumbers[i] + " | gn[0] = " + generatedNumbers[0]);
                if(tmp == generatedNumbers[i] || tmp == generatedNumbers[0]){
                    //System.out.println("tmp = " + tmp + " | Wykryłem powtórzenie\n");
                    i = 0;
                    tmp = randomNumber.nextInt((MAX - MIN) + 1) + MIN;
                }
            }

            generatedNumbers[order] = tmp;
        }
                        
        //System.out.println("order = " + order + " | generatedNumbers[order] = " + generatedNumbers[order]);        
    }

    /**
     * generuje system liczbowy dla aktualnego bloczka; sprawdza czy dany system się nie powrótrzył
     * @param order numer bloczka
     */
    public void ganerateSystems(int order){
        int MAX = 3;
        int MIN = 0;
        int answerSys, tmp;

        if(order == 0){
            answerSys = randomSystem.nextInt((MAX - MIN) + 1) + MIN;
            //System.out.println("order = " + order + " | systemsQueue[order] = " + answerSys);            
            systemsQueue[0] = answerSys;
        }
        else if(order == 1){
            tmp = randomSystem.nextInt((MAX - MIN) + 1) + MIN;
            
            while(tmp == systemsQueue[0])
                tmp = randomSystem.nextInt((MAX - MIN) + 1) + MIN;
                        
            //System.out.println("order = " + order + " | systemsQueue[order] = " + tmp);
            systemsQueue[order] = tmp;            
        }
        else{
            tmp = randomSystem.nextInt((MAX - MIN) + 1) + MIN;
            
            for(int i = 1; i < order; i++){                                
                //System.out.println("    i = " + i + " | tmp = " + tmp + " | sQ["+i+"] = " + systemsQueue[i] + " | sQ[0] = " + systemsQueue[0]);
                
                if(tmp != systemsQueue[0]){
                    if(tmp == systemsQueue[i]){
                        if(!canRepeat){
                            //System.out.println("tmp = " + tmp + " | Brak powtórzeń!\n");
                            i = 0;
                            tmp = randomSystem.nextInt((MAX - MIN) + 1) + MIN;
                        }
                        else{
                            canRepeat = false;
                        }                   
                    }
                }
                else{
                    i = 0;
                    tmp = randomSystem.nextInt((MAX - MIN) + 1) + MIN;
                }
            }

            //System.out.println("order = " + order + " | systemsQueue[order] = " + tmp);
            systemsQueue[order] = tmp;
        }                
    }

    /**
     *  generuje liczbę dla poruszającej się platformy
     */
    public void ganerateIndex(){
        floatingNumber = randomSystem.nextInt(4);
        generatedNumbers[0] = floatingNumber;
    }
    
    /**
     * Sprawdza poprawność odpowiedzi
     * @param hitBox numer bloczka z którym zderzyła się platforma
     * @return wartość logiczną czy zaszło zderzenie z poprawnym bloczkiem
     */
    public boolean isCorrect(int hitBox){        
        int question = generatedNumbers[0];
        int answer = generatedNumbers[hitBox];
        //System.out.println("--CHECK-- " + question + " " + answer + " hb = " + hitBox);
        
        if(question == answer)
            return true;        
        else
            return false;        
    }
    
    /**     
     * @param order jest indeksem aktualnego bloczka
     * @return zwraca tekst będący przekonwertowaną liczbą w zależności od aktualnego indeksu
     */
    public String getNumber(int order){
        //System.out.println("order " + order + ", number" + Integer.toString(generatedNumbers[order]));        
        String returnValue = "";
        
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
    
    /**
     * Ładuje z pliku i ustawia nową czcionkę
     */
    public void newFont(){
        try {            
            fontUrl = getClass().getResource("img/kimberley.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontUrl.getPath())).deriveFont(22f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();            
            ge.registerFont(myFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
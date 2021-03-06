package binarybuster;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.swing.Timer;
import sun.audio.AudioPlayer;

/**
 * Klasa tworząca okno i wszystkie elementy składowe interfejsu; generuje i zarządza obiektami gry
 * @author Dominika Wysocka
 */
public class MainWindow extends javax.swing.JFrame implements ActionListener{

    /** ścieżka dostepu do czcionki */
    private URL fontUrl;
    
    /** czcionki do różnych zastosowań */
    public Font p, H2, H1;
    
    /** zegar, który odmierza określony czas */
    public Timer timer;
    
    /** flaga oznaczająca, czy w danej chwili odbywa się rozgrywka */
    private boolean gameFlag = false;
    
    /** obiekt poruszajacej się platformy */
    private FloatingBlock fb;
    
    /** tablicy obiektów dolnych bloczków */
    private BottomBlocks bb[];
    
    /** obiektów do wyświetlania liczby żyć */
    public LivesGUI lives;
    
    /** obiektów do wyświetlania aktualnego poziomu rozgrywki */
    public LevelGUI level;        
    
    /** obiektów do wyświetlania pozostałego czasu */
    public GameTimer tm;
    
    /**
     * Konstrukor klasy; ustawia czcionki elementów oraz ustawia inne parametry obiektów interfejsu graficznego
     */
    public MainWindow() {
        initComponents();
        newFont(16f, 36f, 60f);
        title.setFont(H1);
        score.setFont(H2);
        endBtn.setFont(p);
        startBtn.setFont(p);
        helpBtn.setFont(p);
        exitBtn.setFont(p);
        backBtn.setFont(p);
        
        gui.setVisible(false);
        score.setVisible(false);
        endBtn.setVisible(false);
        help.setVisible(false);
        backBtn.setVisible(false);
        
        help.setBackground(null);
        help.setForeground(null);
        
        score.setOpaque(false);
        score.setBorder(null);
                
        score.setBackground(null);
        score.setForeground(new java.awt.Color(255, 255, 255));
        score.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        score.setBorder(null);
        score.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        score.setFocusable(false);
        score.setOpaque(false);
        
        jPanel1.setComponentZOrder(background, 1);
        jPanel1.setComponentZOrder(gui, 1);
        jPanel1.setComponentZOrder(title, 1);
        jPanel1.setComponentZOrder(score, 1);
        jPanel1.setComponentZOrder(help, 1);
        jPanel1.setComponentZOrder(startBtn, 1);
        jPanel1.setComponentZOrder(helpBtn, 1);
        jPanel1.setComponentZOrder(exitBtn, 1);
        jPanel1.setComponentZOrder(backBtn, 1);
        jPanel1.setComponentZOrder(endBtn, 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        help = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        endBtn = new javax.swing.JButton();
        startBtn = new javax.swing.JButton();
        helpBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        gui = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(30, 30, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backBtn.setText("Back");
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(false);
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backBtnMousePressed(evt);
            }
        });
        jPanel1.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 630, 260, 60));

        help.setBackground(new java.awt.Color(0, 0, 0));
        help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/binarybuster/img/help.png"))); // NOI18N
        help.setOpaque(true);
        jPanel1.add(help, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        score.setText("jLabel1");
        jPanel1.add(score, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 260, 50));

        endBtn.setText("Game over!");
        endBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                endBtnMousePressed(evt);
            }
        });
        jPanel1.add(endBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 260, 60));

        startBtn.setText("Start");
        startBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startBtn.setFocusPainted(false);
        startBtn.setFocusable(false);
        startBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                startBtnMousePressed(evt);
            }
        });
        jPanel1.add(startBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 260, 60));

        helpBtn.setText("Help");
        helpBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        helpBtn.setFocusPainted(false);
        helpBtn.setFocusable(false);
        helpBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                helpBtnMousePressed(evt);
            }
        });
        jPanel1.add(helpBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, 260, 60));

        exitBtn.setText("Exit");
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.setFocusPainted(false);
        exitBtn.setFocusable(false);
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitBtnMousePressed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 630, 260, 60));

        title.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 60)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("binary buster");
        jPanel1.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 199, -1, 90));

        gui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/binarybuster/img/gui.png"))); // NOI18N
        gui.setPreferredSize(new java.awt.Dimension(1240, 80));
        jPanel1.add(gui, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 90));

        background.setBackground(new java.awt.Color(0, 0, 0));
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/binarybuster/img/dark_wall_bg.png"))); // NOI18N
        background.setOpaque(true);
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Cykliczna metoda sprawdzająca, czy gracz przechodzi do następnego poziomu; monitorowanie czy gracz może wykonywać ruch
     */
    public void actionPerformed(ActionEvent evt){        
        
        // jeśli wszystkie warunki są spełnione do kontynuowania rozgrywki
        if(gameFlag && tm.canCount && lives.currentLives > 0){
            fb.canMove = true;
        }
        else{
            AudioPlayer.player.stop(tm.audio);
            fb.canMove = false;
            gameFlag = false;
            fb.range = 10;
        }
        
        // jeśli odpowiedź jest prawidłowa i gracz przechodzi do kolejnego poziomu
        if(fb.newLevel){
            AudioPlayer.player.stop(tm.audio);
            fb.newLevel = false;
            level.addLevel();
            tm.resetTimer();
                                    
            // ...wygeneruj i ustaw wszystkie wartości dla poruszającej się platformy
            fb.generateNumbers(0);
            fb.ganerateSystems(0);
            fb.setterText();
            fb.setRange();
            
            // ...i dolnych bloczków
            for(int i = 1; i <= 4; i++){
                bb[i-1].generateNumbers(i);
                bb[i-1].ganerateSystems(i);                
                bb[i-1].setterText(i);
            }
        }
    }
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
     int capturedKey = evt.getKeyCode();
        
        if(capturedKey == evt.VK_ESCAPE){
            // esc w menu
            if(!gameFlag)
                System.exit(0);
            // esc podczas rozgrywki
            else{   
                tm.changeFlag(false);
                setBtnVisibility(true);
                gameFlag = false;
                deleteGameplay();                
            }                
        }

        // alternatywna możliwość uruchomienia rozgrywki
        if(!gameFlag && !endBtn.isVisible() && capturedKey == evt.VK_ENTER){
            createGameplay();
            gameFlag = true;
        }
        
        // obsługa klawisza spacji do opuszczania platformy
        if(gameFlag){
            if(capturedKey == evt.VK_SPACE)
                fb.fallingFlag = true;
        }
    }//GEN-LAST:event_formKeyPressed
   
    /**
     * Obsługa wyjścia z aplikacji
     * @param evt brak obsługi
     */
    private void exitBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMousePressed
        System.exit(0);
    }//GEN-LAST:event_exitBtnMousePressed

    /**
     * Przycisk rozpoczęcia rozgrywki
     * @param evt brak obsługi
     */
    private void startBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startBtnMousePressed
        if(!gameFlag && !endBtn.isVisible())
            createGameplay();
    }//GEN-LAST:event_startBtnMousePressed
    
    /**
     * Obsługa przycisku występującego po zakończonej rozgryce
     * @param evt brak obsługi
     */
    private void endBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endBtnMousePressed
        gameFlag = false;
        deleteGameplay();
        endBtn.setVisible(false);
    }//GEN-LAST:event_endBtnMousePressed

    /**
     * Metoda ustawia widoczność przycisków
     * @param evt brak obsługi
     */
    private void helpBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpBtnMousePressed
        help.setVisible(true);
        backBtn.setVisible(true);
        
        startBtn.setVisible(false);
        helpBtn.setVisible(false);
        exitBtn.setVisible(false);
        title.setVisible(false);
        score.setVisible(false);
    }//GEN-LAST:event_helpBtnMousePressed

    /**
     * Metoda ustawia widoczność przycisków
     * @param evt brak obsługi
     */
    private void backBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMousePressed
        help.setVisible(false);
        backBtn.setVisible(false);
        score.setVisible(false);
        
        startBtn.setVisible(true);
        helpBtn.setVisible(true);
        exitBtn.setVisible(true);
        title.setVisible(true);
    }//GEN-LAST:event_backBtnMousePressed
   
    /**
     * Metoda tworzy i przygotowuje elementy rozgryki
     */
    private void createGameplay(){        
        int i;
        timer = new javax.swing.Timer(3, this);
        timer.start();
        level = new LevelGUI(jPanel1);
        tm = new GameTimer(jPanel1, endBtn);
        setBtnVisibility(false);

        lives = new LivesGUI(jPanel1, endBtn);        
        bb = new BottomBlocks[4];
        fb = new FloatingBlock(jPanel1, lives, level);

        for(i = 1; i <= 4; i++){
            bb[i-1] = new BottomBlocks(jPanel1, i);
        }
                        
        pack();
        gameFlag = true;        
    }
    
    /**
     * Metoda usuwa wszystkie bloczki i inne obiekty występujące w rozgryce
     */
    private void deleteGameplay(){        
        int i;
        setBtnVisibility(true);
        AudioPlayer.player.stop(tm.audio);
        
        score.setText("Score " + Integer.toString(level.lvl - 1));
        
        for(i = 0; i < 4; i++)
            jPanel1.remove(bb[i]);           
                
        jPanel1.remove(lives);
        jPanel1.remove(level);
        jPanel1.remove(fb);
        jPanel1.remove(tm);
        jPanel1.repaint();
        gameFlag = false;
    }
    
    /**
     * Metoda ustawia widoczność przycisków
     * @param flag przesyła wartość true lub false
     */
    private void setBtnVisibility(boolean flag){
        startBtn.setVisible(flag);
        helpBtn.setVisible(flag);
        exitBtn.setVisible(flag);
        title.setVisible(flag);
        score.setVisible(flag);
        gui.setVisible(!flag);
        level.setVisible(!flag);
    }
    
    /**
     * Metoda tworzy nową czcionkę
     * @param sizeP wielkość najmniejszego tekstu
     * @param sizeH2 wielkość średniego nagłówka
     * @param sizeH1 wielkość największego nagłówka
     */
    public void newFont(float sizeP, float sizeH2, float sizeH1){
        try {            
            fontUrl = getClass().getResource("img/kimberley.ttf");
            p = Font.createFont(Font.TRUETYPE_FONT, new File(fontUrl.getPath())).deriveFont(sizeP);
            H2 = Font.createFont(Font.TRUETYPE_FONT, new File(fontUrl.getPath())).deriveFont(sizeH2);
            H1 = Font.createFont(Font.TRUETYPE_FONT, new File(fontUrl.getPath())).deriveFont(sizeH1);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(p);
            ge.registerFont(H2);
            ge.registerFont(H1);
        } catch (IOException e){
            e.printStackTrace();
        } catch(FontFormatException e){
            e.printStackTrace();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel background;
    private javax.swing.JButton endBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel gui;
    private javax.swing.JLabel help;
    private javax.swing.JButton helpBtn;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JLabel score;
    private javax.swing.JButton startBtn;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import items.Item;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;
/**
 *
 * @author Marga Torralba
 */
public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int ogTileSize = 48;
    //final int scale = 3;
    
    public final int tileSize = ogTileSize;//*scale if not 48*48
    //how many tiles can it display
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol; 
    final int screenHeight = tileSize*maxScreenRow;
    
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    // dapat may time
    Thread gameThread;
    Player player = new Player(this, keyH);
    Item item = new Item(this);
    Item book = new Item("book.png", "hbook", 20, 50, false, true, 45);
    Item apple = new Item("book2.png", "wblock", 100,0, true, false, -75);
     
    /*int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;*/
    public GamePanel(){
        //from JPanel extension 
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // for movement
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override//override runnable
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        //loop core of game eto yung time
        while(gameThread != null){
           currentTime = System.nanoTime();
           delta += (currentTime - lastTime)/drawInterval;
           timer+=(currentTime - lastTime);
           lastTime = currentTime;
           if(delta>= 1){
           update();
           repaint();//calls paintComp
           delta--;
           drawCount++;
           }
           if(timer>= 1000000000){
             
               drawCount = 0;
               timer = 0;
           }
           
           
        }    
    }
   
   
       
    public void update(){
     player.update();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
   
       
        player.draw(g2);
        item.draw(g2);
       
        g2.dispose();
    }
}

package game;

import items.Item;
import entity.Enemy;
import entity.Player;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame {
    public static void main(String[] args) {
        // TODO code application logic here
      
        GamePanel gamePanel = new GamePanel();
        
        ArrayList<Item> inventory = Item.getInventory();
        Player player = new Player(25);
        Enemy enemy = new Enemy(50);
        
        
//        JFrame mainMenu = new MainMenuLayout();
//        mainMenu.setVisible(true);
        
       JFrame battle = new BattleLayout(enemy, player);
//        battle.setVisible(true);
//         JFrame about = new About();
        JFrame overworld = new OverworldLayout(gamePanel);
        overworld.setVisible(true);
        
        gamePanel.startGameThread();
     
    }
    
}

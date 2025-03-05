package items;
//add Item class in game package and icon to player package
import entity.Enemy;
import entity.Player;
import entity.Entity;
import game.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import javax.imageio.ImageIO;
public final class Item {
    GamePanel gp;
    private BufferedImage img;
    private String imgPath;
    String name;
    boolean weapon;
    boolean heal;
    int x, y, hpDifference; // static for hpDifference
    static ArrayList<Item> inventory = new ArrayList<Item>();
    static Item [] imgArray;
    boolean collision = false;
    public Item(GamePanel gp){
       this.gp = gp;    
    }
    public Item(String imgPath, String name, int x, int y, boolean weapon, boolean heal, int hpDifference){
      
        this.name = name;
        this.x = x;
        this.y = y;
        this.weapon = weapon;
        this.heal = heal;
        this.hpDifference = hpDifference;
        this.imgPath = imgPath;
        
       
        inventory.add(this);
        setItemImage();
    } 
    public void setItemImage(){
        try{
            for(int i=0; i<inventory.size(); i++){
            inventory.get(i).setImg(ImageIO.read(getClass().getResourceAsStream( inventory.get(i).getImgPath())));
            
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        //used for moving item 
    }
    public void draw(Graphics2D g2){  
    //Graphics2D di pwede static
    
       for(int i=0; i<inventory.size();i++){
       g2.drawImage(inventory.get(i).getImg(),inventory.get(i).getX(),inventory.get(i).getY(),48,48, null);
        }
    }
    public String getName(){
        return name;
    }
    public static void add(Item item){
       inventory.add(item);
   }
   public static void delete(Item item){
       inventory.remove(item);
   }
   
    public static void display(){
        for(int i = 0; i < inventory.size(); i++){
           System.out.println((i+1) + ") " + inventory.get(i).getName());
        }
        System.out.println((inventory.size()+1)+") won't use any item");
   }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    //Item class
    public static void useItem(Player player, Entity enemy, int index){
        Item item = null;
        for(int i = 0; i<inventory.size(); i++){
            if((index-1)==i){
                item=inventory.get(index-1);
            }
        }
        if(item != null){
         
        inventory.remove(item);
        }
        else{/*throw exception*/}
    }
    public boolean getWeapon(){
        return weapon;
    }
    public boolean getHeal(){
        return heal;
    }
    public int getHpDifference(){
        return hpDifference;
    }   
    public static int geInventoryLength(){
        return inventory.size();
    }  
  
    public static ArrayList<Item> getInventory(){
        return inventory;
    }
    public static Item searchInventory(String search) throws NullPointerException{
          for(Item i : getInventory()){
            if(i.getName().equalsIgnoreCase(search)){ return i;}
        }
        throw new NullPointerException();
    }
    
    public static void use(Item item, Entity enemy, Entity player){
       if(item.getHeal()){
        int newHp = player.getHp() + item.getHpDifference();
        player.setHp(newHp);
        System.out.print(player.getHp());
       }
       else{
        int newHp = enemy.getHp() + item.getHpDifference();
        enemy.setHp(newHp);
        System.out.print(enemy.getHp());
       }
       
       
    }

    /**
     * @param img the img to set
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /**
     * @return the img
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * @return the imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

}

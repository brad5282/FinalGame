
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class bullet {
    private Image image;
    private Rectangle hitbox;
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    private boolean inGame;
    
    public bullet(int x, int y) throws SlickException{
        image = new Image("images/bullet.png");
        hitbox = new Rectangle(x,y,image.getWidth(),image.getHeight());
        inGame = true;
    }
    
    public Rectangle getHitbox(){
        return hitbox;
    }
    
    public boolean inGame(){
        return inGame;
    }
    
    public static void setGameSize(int x, int y){
        GAME_HEIGHT=x;
        GAME_WIDTH=y;
    }
    
   public void move(){
        hitbox.setY(hitbox.getY() -10);
    }
    
    public void draw(){
        image.draw(hitbox.getX(), hitbox.getY());
    }
}

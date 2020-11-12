
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class rocket {
    private Image image;
    private Rectangle hitbox;
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    
    public rocket(int x, int y) throws SlickException{
        image = new Image("images/rocket.png");
        hitbox = new Rectangle(x,y,image.getWidth(),image.getHeight());
    }
    
    public Rectangle getHitbox(){
        return hitbox;
    }
    
    public static void setGameSize(int x, int y){
        GAME_HEIGHT=x;
        GAME_WIDTH=y;
    }
    
    
    

    
   public void move(int mx, int my){
        
        hitbox.setX(mx - (image.getWidth()/2));
        hitbox.setY(my - (image.getHeight()/2));
    }
    
    public void draw(){
        image.draw(hitbox.getX(), hitbox.getY());
    }
}


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

public class Enemy {

    private Image image;
    private Rectangle hitbox;
    private int xdir, ydir;

    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;

    public Enemy(int x, int y) throws SlickException {
        image = new Image("images/enemy.png");
        hitbox = new Rectangle(x, y, image.getWidth(), image.getHeight());

        //randomly assign from  -3 to +3 with 0 nt allowed
        while (true) {
            xdir = (int) (Math.random() * 7 - 3);//-3,-2,-1,0,1,2,3
            ydir = (int) (Math.random() * 7 - 3);
            if (xdir != 0 && ydir != 0) {
                break;
            }
        }

    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public static void setGameSize(int x, int y) {
        GAME_HEIGHT = x;
        GAME_WIDTH = y;
    }

       public boolean hit(Rectangle x){
        if(hitbox.intersects(x)) return true;
        else return false;
    }

    public void move() {
        hitbox.setX(hitbox.getX() + xdir);
        hitbox.setY(hitbox.getY() + ydir);

        //bounce off of wall
        if (hitbox.getX() <= 0 || hitbox.getX() > GAME_WIDTH - image.getWidth()) {
            xdir = -xdir;
        }
        if (hitbox.getY() <= 40 || hitbox.getY() > GAME_HEIGHT - image.getHeight()) {
            ydir = -ydir;
        }
    }

    public void draw() {
        image.draw(hitbox.getX(), hitbox.getY());
    }

}

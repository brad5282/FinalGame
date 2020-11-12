
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Level_1 extends BasicGameState {

    ArrayList<Enemy> enemies;
    ArrayList<bullet> bullets;

    int timer;
    int timer2;
    int score;
    Color blueish;
    rocket player;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        enemies = new ArrayList();
        bullets = new ArrayList();
        timer = 0;
        timer2 = 0;
        score = 0;
        Enemy.setGameSize(600, 800);
        //spawn initial "wave" of enemies
        for (int i = 0; i < 20; i++) {
            int rx = (int) (Math.random() * 750);//initial spawns
            int ry = (int) (Math.random() * 400);
            enemies.add(new Enemy(rx, ry));
        }
        player = new rocket(0, 800);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input in = gc.getInput();
        int mx = gc.getInput().getMouseX();
        int my = gc.getInput().getMouseY();
        player.move(mx, my);

        if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {//pew pew
            bullets.add(new bullet(mx, my));
        }
        ///////////////////////////////////////////////////////////////////////////////disable for "godmode"    
        /*
        for (Enemy e : enemies) {
            if (e.hit(player.getHitbox())) {
                sbg.enterState(6, new FadeOutTransition(), new FadeInTransition());
            }
        }*/
        ///////////////////////////////////////////////////////////////////////////////

        for (bullet b : bullets) {// hit detection for enemies
            b.move();
            for (Enemy e : enemies) {
                if (e.hit(b.getHitbox())) {
                    enemies.remove(e);
                    score+=10;
                    break;
                }
            }
        }

        for (Enemy e : enemies) {
            e.move();
        }

        if (enemies.size() > 0) {
            blueish = new Color(67, 86, 153);
            timer2++; // levels track time
            
            timer++;
            if (timer == 1000) {// spawn every 10 seconds
                timer = 0;
                int rx = (int) (Math.random() * 750); // spawn limits
                int ry = (int) (Math.random() * 400);
                enemies.add(new Enemy(rx, ry));

            }
        }

        if (enemies.size() <= 0) {
            if (in.isKeyPressed(Input.KEY_SPACE)) {
                sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(blueish);
        player.draw();
        g.fill(new Rectangle(0, 0, 800, 40));
        g.setColor(Color.red);
        for (Enemy e : enemies) {
            e.draw();
        }

        for (bullet b : bullets) {// ha ha spaceship go brrrrr
            b.draw();
        }
        g.setColor(Color.yellow);
        g.drawString("Time: " + (double) timer2 / 100, 10, 10);
        g.drawString("Score: " + (double) score, 200, 10);
        g.drawString("Attackers remaining: " + enemies.size(), 550, 10);
        if (enemies.size() <= 0) {
            g.drawString("Press SPACE to continue.", 200, 400);
        }
    }

    public int getID() {
        return 1;  //this id will be different for each screen
    }

}


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

public class Level_2 extends BasicGameState {

    ArrayList<Enemy> enemies;
    ArrayList<bullet> bullets;
    int timer;
    int timer2;
    int score;
    rocket player;
    Color redish;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        enemies = new ArrayList();
        bullets = new ArrayList();
        timer = 0;
        score = 0;
        player = new rocket(0, 800);
        timer2 = 0;
        Enemy.setGameSize(600, 800);
        //spawn initial "wave" of enemies
        for (int i = 0; i < 25; i++) {// becomes bigger with each level
            int rx = (int) (Math.random() * 750);// initial spawns
            int ry = (int) (Math.random() * 400);
            enemies.add(new Enemy(rx, ry));
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input in = gc.getInput(); // mouse listening
        int mx = gc.getInput().getMouseX();
        int my = gc.getInput().getMouseY();
        player.move(mx, my);

        if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            bullets.add(new bullet(mx, my));
        }
        ///////////////////////////////////////////////////////////////////////////////disable for "godmode"
       
        for (Enemy e : enemies) {
            if (e.hit(player.getHitbox())) {
                sbg.enterState(6, new FadeOutTransition(), new FadeInTransition());
            }
        }
        ///////////////////////////////////////////////////////////////////////////////

        for (bullet b : bullets) {
            b.move();
            for (Enemy e : enemies) {
                if (e.hit(b.getHitbox())) {
                    enemies.remove(e);
                    score += 10;
                    break;
                }
            }
        }

        for (Enemy e : enemies) {
            e.move();
        }

        if (enemies.size() > 0) {
            redish = new Color(179, 62, 27);

            timer2++;
            timer++;
            if (timer == 500) {//enemies spawn more often with each level
                timer = 0;
                int rx = (int) (Math.random() * 750);
                int ry = (int) (Math.random() * 400);
                enemies.add(new Enemy(rx, ry));

            }
        }

        if (enemies.size() <= 0) {
            if (enemies.size() <= 0) {
                if (in.isKeyPressed(Input.KEY_SPACE)) {
                    sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
                }
            }

        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(redish);
        player.draw();
        g.fill(new Rectangle(0, 0, 800, 40));
        for (Enemy e : enemies) {
            e.draw();
        }

        for (bullet b : bullets) {
            b.draw();
        }

        g.setColor(Color.yellow);
        g.drawString("Time: " + (double) timer2 / 100, 10, 10);
        g.drawString("Score: " + score, 200, 10);
        g.drawString("Attackers remaining: " + enemies.size(), 550, 10);
        if (enemies.size() <= 0) {
            g.drawString("Press SPACE to continue.", 200, 400);
        }
    }

    public int getID() {
        return 2;  //this id will be different for each screen
    }

}

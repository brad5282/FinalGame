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

public class Level_3 extends BasicGameState {
     ArrayList<Enemy> enemies;
     ArrayList<bullet> bullets;
     rocket player;
    int timer;
    int timer2;
    int score;
    Color greeney;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       enemies = new ArrayList();
       bullets = new ArrayList();
       player = new rocket(0,800);
        timer = 0;
        timer2=0;
        score =0;
        Enemy.setGameSize(600, 800);
        //spawn initial "wave" of enemies
        for (int i = 0; i < 36; i++) {// becomes bigger with each level
            int rx = (int) (Math.random() * 750);//initial spawns
            int ry = (int) (Math.random() * 300);
            enemies.add(new Enemy(rx, ry));
    }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException { 
        Input in = gc.getInput();
        int mx = gc.getInput().getMouseX();
        int my = gc.getInput().getMouseY();
        player.move(mx, my);
        
        if (in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                bullets.add(new bullet(mx,my));
            }
        ///////////////////////////////////////////////////////////////////////////////disable for "godmode"
        for (Enemy e: enemies){
            if (e.hit(player.getHitbox())){
                sbg.enterState(6, new FadeOutTransition(), new FadeInTransition());
            }   
        }
        ///////////////////////////////////////////////////////////////////////////////
        
        for (bullet b : bullets) {
            b.move();
            for (Enemy e : enemies) {
                if (e.hit(b.getHitbox())) {
                    enemies.remove(e);
                    score +=10;
                    break;
                }
            }
        }

        for (Enemy e : enemies) {
            e.move();
        }

        if (enemies.size() > 0) {
            greeney = new Color(59, 168, 88);//colour used to differentiate between levels
            
            timer2++;
            timer++;
            if (timer == 25) {//enemies spawn more frequently, further into the game
                timer = 0;
                int rx = (int) (Math.random() * 750);
                int ry = (int) (Math.random() * 400);
                enemies.add(new Enemy(rx, ry));

            }
        }
        
        if (enemies.size() <= 0) {
            if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
            }
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       g.setColor(greeney);
       player.draw();
        g.fill(new Rectangle(0,0,800,40));
        for (Enemy e : enemies) {
            e.draw();
        }
        
        for(bullet b: bullets){
            b.draw();
        }
        
        g.setColor(Color.yellow);
        g.drawString("Time: " + (double)timer2/100, 10, 10);
        g.drawString("Time: " + score, 200, 10);
        g.drawString("Attackers remaining: " + enemies.size(), 550, 10);
    }
    
    public int getID() {
       return 4;  //this id will be different for each screen
    }

    
}

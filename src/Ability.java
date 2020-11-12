
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Ability extends BasicGameState {
    Image img;
  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
      img = new Image("images/ability.png");

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
         Input in = gc.getInput();
        
        if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
        }
        
        if(in.isKeyPressed(Input.KEY_SPACE)){
            sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw(0,0);
        g.setColor(Color.black);
        g.drawString("Press SPACE to continue", 300, 400);

    }

    public int getID() {
        return 3;  //this id will be different for each screen
    }

}   
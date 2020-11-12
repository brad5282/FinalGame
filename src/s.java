import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class s extends BasicGameState {
    Image img;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image("images/s.png");

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input in = gc.getInput();
        
        if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            System.exit(0);
        }
        
        if(in.isKeyPressed(Input.KEY_SPACE)){
            sbg.enterState(8, new FadeOutTransition(), new FadeInTransition());//SHHHHHHHHH, secret
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw(0,0);
    }

    public int getID() {
        return 8;  //this id will be different for each screen
    }

}
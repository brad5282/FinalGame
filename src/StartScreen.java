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

public class StartScreen extends BasicGameState {
    
    Image img;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image("images/title.png");
    
    }
        

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException { 
        Input in = gc.getInput();
        
        if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }

        /*
        you can decide how to proceed to the game
        if(in.isKeyDown(Input.KEY_SPACE)){}
        */
       

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        img.draw(0,0);
        g.setColor(Color.red);
        g.drawString("Move using the mouse cursor.", 500, 400);
        g.drawString("Leftclick to shoot.", 500, 450);
        g.setColor(Color.green);
        g.drawString("Click to Start", 400, 550);
        
       
    }
    
    public int getID() {
       return 0;  //this id will be different for each screen
    }

    
}
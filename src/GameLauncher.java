import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class GameLauncher extends StateBasedGame {

    public GameLauncher(String title) {
        super(title);
    }
    
    public void initStatesList(GameContainer gc) throws SlickException {
       //list other screens in here - first one is opening screen
       this.addState(new StartScreen());
       this.addState(new Level_1());
       this.addState(new Level_2());
       this.addState(new Ability());
       this.addState(new Level_3());
       this.addState(new Victory());
       this.addState(new Death());
       this.addState(new Level_4());
       this.addState(new s());
       
    }

    public static void main(String args[]) throws SlickException {
        GameLauncher game = new GameLauncher("SPACEyest 2 - Electric Boogaloo");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
package GameStateManager;

import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Death extends GameState {

    /**
     * default constructor
     * @param gsm - super for the game state manager
     */
    public Death(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init(Pane gamePane) {
        //set background
        gsm.setBackground(new Image("/Assets/Backgrounds/died.png"));
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Pane gamePane) {

    }

    @Override
    public void handle(Event event) {
        if(event.getEventType() ==  KeyEvent.KEY_RELEASED){
            gsm.setState(GameStateManager.STATE.MENUSTATE);
        }
    }
}

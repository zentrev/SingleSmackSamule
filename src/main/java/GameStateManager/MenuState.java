package GameStateManager;

import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class MenuState extends GameState{

    private String[] options = {
            "Play",
            "Options",
            "Quit"
    };
    private int currentChoice;

    public MenuState(GameStateManager gsm){
        super(gsm);
    }

    private Text text;

    @Override
    public void init(Pane gamePane) {
        currentChoice = 0;
        for(int i = 0; i < options.length; i++){
            text = new Text();
            text.setText(options[i]);
            text.setX(100);
            text.setY(50+(i*20));
            gamePane.getChildren().add(text);
        }


    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Pane gamePane) {

    }

    @Override
    public void handle(Event event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            gsm.setState(GameStateManager.STATE.ROOMSTATE);
        }
    }
}

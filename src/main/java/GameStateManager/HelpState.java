package GameStateManager;

import Logic.Game;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HelpState extends GameState{
    private String moveHelp = "Samuel is controlled by using W A S keys.\n" +
            "A is to move left,\n D is to move right,\n and W is to jump.";
    private String otherHelp = "The K and J keys are used to attack and use items.\n" +
            "The K key lets Samuel attack in the direction he's facing,\n and the J key " +
            "lets him use an item in his inventory";

    private Text text;


    public HelpState(GameStateManager gsm){super(gsm);}

    @Override
    public void init(Pane gamePane) {
        text = new Text();
        text.setFill(Color.WHITESMOKE);
        text.setText(moveHelp);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTranslateX(Game.WIDTH/2-3);
        text.setY(Game.HEIGHT/6);
        gamePane.getChildren().add(text);

        text = new Text();
        text.setFill(Color.WHITESMOKE);
        text.setText(otherHelp);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTranslateX(Game.WIDTH/2-3);
        text.setY(Game.HEIGHT/1.7);
        gamePane.getChildren().add(text);

        text = new Text();
        text.setFill(Color.WHITESMOKE);
        text.setText("Press Escape to return to the Main Menu");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTranslateX(Game.WIDTH/2-3);
        text.setY(Game.HEIGHT/1.4);
        gamePane.getChildren().add(text);

        gsm.setBackground(new Image(getClass().getResourceAsStream("/Assets/Backgrounds/helpscreen.png")));
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Pane gamePane) {

    }

    @Override
    public void handle(Event event) {
        if(event.getEventType() == KeyEvent.KEY_RELEASED){
            KeyEvent keyEvent = (KeyEvent)event;
            switch(keyEvent.getCode()){
                case ESCAPE:
                    gsm.setState(GameStateManager.STATE.MENUSTATE);
                    break;
            }
        }
    }
}

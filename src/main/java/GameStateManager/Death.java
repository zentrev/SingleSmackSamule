package GameStateManager;

import Logic.Game;
import javafx.event.Event;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Death extends GameState {

    private Text text;


    public Death(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init(Pane gamePane) {
        text = new Text();
        text.setFill(Color.WHITESMOKE);
        text.setText("YOU HAVE DIED");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTranslateX(Game.WIDTH/2-6);
        text.setY(Game.HEIGHT/2);
        gamePane.getChildren().add(text);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Pane gamePane) {

    }

    @Override
    public void handle(Event event) {

    }
}

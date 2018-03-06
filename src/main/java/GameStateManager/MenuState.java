package GameStateManager;

import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

        gsm.setBackground(new Image(getClass().getResourceAsStream("/Assets/SpriteSheets/Google_Ultron.png")));


    }

    @Override
    public void update() {
        for(int i = 0; i < options.length; i++){
            text = new Text();
            text.setText(options[i]);
            text.setX(100);
            text.setY(50+(i*20));
            if(currentChoice == i){
                text.setFill(Color.RED);
            }
            gsm.getGamePane().getChildren().add(text);
        }
    }

    @Override
    public void draw(Pane gamePane) {

    }

    public void commitOption(){
        switch (currentChoice) {
            case 0:
            gsm.setState(GameStateManager.STATE.ROOMSTATE);
            break;
            case 1:
                System.out.println("wasd to move, k to attack");
                break;
            case 2:
                gsm.closeGame();

        }
    }

    @Override
    public void handle(Event event) {
        if(event.getEventType() == KeyEvent.KEY_RELEASED){
            KeyEvent keyEvent = (KeyEvent)event;
            switch (keyEvent.getCode()){
                case S:
                case DOWN:
                    currentChoice++;
                    if(currentChoice == options.length){
                        currentChoice = 0;
                    }
                    break;
                case UP:
                case W:
                    currentChoice--;
                    if(currentChoice < 0){
                        currentChoice = options.length-1;
                    }
                    break;
                case ENTER:
                case SPACE:
                    commitOption();
                    break;
            }
        }
    }
}

package GameStateManager;

import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static javafx.scene.text.Font.font;


public class MenuState extends GameState{

    private String[] options = {
            "Play",
            "Help",
            "Quit"
    };
    private int currentChoice;


    /**
     * default constructor
     * @param gsm - the super for the game state manager
     */
    public MenuState(GameStateManager gsm){
        super(gsm);
    }

    private Text text;

    @Override
    public void init(Pane gamePane) {
        currentChoice = 0;


        gsm.setBackground(new Image(getClass().getResourceAsStream("/Assets/Backgrounds/menu.png")));


    }

    @Override
    public void update() {
        for(int i = 0; i < options.length; i++){
            text = new Text();
            if(i == currentChoice){
                text.setFill(Color.RED);
            } else {
                text.setFill(Color.BLACK);
            }
            text.setText(options[i]);
            text.setFont(font ("Verdana", 40));
            text.setX(500);
            text.setY(500+(i*50));
            gsm.getGamePane().getChildren().add(text);
        }

    }

    @Override
    public void draw(Pane gamePane) {

    }

    /**
     * commits the user's chosen option and goes to wherever the option leads to
     */
    public void commitOption(){
        switch (currentChoice) {
            case 0:
            gsm.newRoomState();
            break;
            case 1:
                gsm.setState(GameStateManager.STATE.HELPSTATE);
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

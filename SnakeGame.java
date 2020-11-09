import com.javarush.engine.cell.*;


public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame(){
        snake = new Snake(WIDTH/2, HEIGHT/2);
        createNewApple();
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);


        // Apple apple = new Apple(7,7);
        // apple.draw(this);
    }

    private void drawScene(){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.PINK, "");
            }
        }
        
        snake.draw(this);
        apple.draw(this);
    }

    private void createNewApple(){
        Apple apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        this.apple=apple;
    }
    
    @Override
    public void onTurn(int a){
        snake.move(apple);
        if(apple.isAlive==false){
            createNewApple();
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key){
        switch (key) {
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;

            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;

            case UP:
                snake.setDirection(Direction.UP);
                break;

            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
        }
    }
}

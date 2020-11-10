import com.javarush.engine.cell.*;


public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame(){
        score = 0;
        snake = new Snake(WIDTH/2, HEIGHT/2);
        createNewApple();
        isGameStopped = false;
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        setScore(score);


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
        Apple newApple;
        do {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            newApple = new Apple(x, y);
        } while (snake.checkCollision(newApple));
        apple = newApple;
    }

    private void gameOver(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER", Color.DEEPPINK, 70);
    }

    private void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "YOU WIN", Color.DEEPPINK, 70);
    }
    
    @Override
    public void onTurn(int a){
        snake.move(apple);
        if(apple.isAlive==false){
            createNewApple();
            score = score + 5;
            setScore(score);
            turnDelay = turnDelay - 10;
            setTurnTimer(turnDelay);
        }
        if(snake.isAlive==false){
            gameOver();
        }
        if(snake.getLength()>GOAL){
            win();
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
        if(key==Key.SPACE && isGameStopped==true) {
            createGame();
        }
    }
}

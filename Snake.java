import com.javarush.engine.cell.*;
import java.util.ArrayList;
import java.util.List;

public class Snake{
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "🐲";
    private static final String BODY_SIGN = "🍀";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    private Color cellColor;
    
    
    public Snake(int x, int y){
        GameObject one = new GameObject(x, y);
        GameObject two = new GameObject(x+1, y);
        GameObject three = new GameObject(x+2,y);
        
        snakeParts.add(one);
        snakeParts.add(two);
        snakeParts.add(three);
    }
    
    public void draw(Game game){
        if(isAlive){
            cellColor=Color.BLACK;
        }
        else{
            cellColor=Color.RED;
        }
            
        game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, cellColor, 75);
        game.setCellValueEx(snakeParts.get(1).x, snakeParts.get(1).y, Color.NONE, BODY_SIGN, cellColor, 75);
        game.setCellValueEx(snakeParts.get(2).x, snakeParts.get(2).y, Color.NONE, BODY_SIGN, cellColor, 75);
  
    }
    
    public void setDirection(Direction direction){
        if(snakeParts.get(0).x == snakeParts.get(1).x && (this.direction == Direction.LEFT || this.direction == Direction.RIGHT)) {
            return;  //if the snake is going left/right and we press "LEFT/RIGHT" - do nothing(return)
        }
        if(snakeParts.get(0).y == snakeParts.get(1).y && (this.direction == Direction.UP || this.direction == Direction.DOWN)){
            return; //if the snake is going up and we press "UP/DOWN" - do nothing(return)
        }
        if(direction == Direction.UP && this.direction == Direction.DOWN) {
            return;
        }
        if(direction == Direction.RIGHT && this.direction == Direction.LEFT) {
            return;
        }
        if(direction == Direction.DOWN && this.direction == Direction.UP) {
            return;
        }
        if(direction == Direction.LEFT && this.direction == Direction.RIGHT) {
            return;
        }


        this.direction = direction;
    }


    public void move(Apple apple){
        GameObject newHead = createNewHead();
        if(newHead.x == apple.x  && newHead.y == apple.y){
            apple.isAlive=false;
        }
        else if(newHead.x>=SnakeGame.WIDTH ||
        newHead.x<0                   ||
        newHead.y>=SnakeGame.HEIGHT   ||
        newHead.y<0
        ){
            isAlive=false;
        }
        else{
            if(checkCollision(newHead)) {
                isAlive = false;
                return;
            }
            snakeParts.add(0, newHead);
            removeTail();
        }
    }
    
    public GameObject createNewHead(){
        GameObject object = snakeParts.get(0);
        switch (direction){
            
            case UP:
            object= new GameObject(object.x, object.y-1);
            break;
            
            case DOWN: 
            object= new GameObject(object.x, object.y+1);
            break;
            
            case RIGHT: 
            object= new GameObject(object.x+1, object.y);
            break;
            
            case LEFT: object= new GameObject(object.x-1, object.y);
            break;
        }
    
        return object;
    }
    
    public void removeTail(){
      int removeLastIndex = snakeParts.size() - 1;
      snakeParts.remove(removeLastIndex); 
    }

    public boolean checkCollision(GameObject object){
        boolean collision=false;
        for(GameObject each: snakeParts){
        if(each.x == object.x && each.y == object.y) {
            collision=true;
            }
        }
        return collision;
    }

    public int getLength(){
        return snakeParts.size();
    }
    
}

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
        this.direction = direction;
    }
    
    public void move(){
   
        GameObject newHead = createNewHead();
        
        if(newHead.x>=SnakeGame.WIDTH ||
        newHead.x<0                   ||
        newHead.y>=SnakeGame.HEIGHT   ||
        newHead.y<0
        ){
            isAlive=false;
        }
        else{
        snakeParts.set(0, newHead);
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
    
}

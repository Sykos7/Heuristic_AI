import java.lang.annotation.Target;


public class Heuristics {

    /*Don't look the neighbors heuristic
    * Also we could implement additional class to create A* and/or Best First
    * TODO: Implement a heuristic
    * You CANNOT change the input parameters and return type.
    * The value returned can ONLY be based on the current state and the target state, NOT intermediate states.
    */


    //Heuristic distance (Find the shorter path)
    public static float Heuristic1(State currentState, State targetState, float[][] map){
        float posX, posY, add;
        float avg, result;

        posX = (float)currentState.getX();
        posY = (float)currentState.getY();


        result = (float)Math.sqrt( (targetState.getX()-posX)*2 + (targetState.getY()-posY)*3 );

        return result;
    }

    //Heuristic economic (Find the path spending the lower quantity of money)
    public static float Heuristic2(State currentState, State targetState, float[][] map){

        return (float) Math.abs(currentState.getX() - targetState.getX()) + Math.abs(currentState.getY() - targetState.getY());

    }

    //Heuristic economic & distance (find  the path with the lowest average between coins and distance)
    public static float Heuristic3(State currentState, State targetState, float[][] map){
        float value1 = Heuristic2(currentState, targetState, map);
        float value2 = currentState.getPrice();

        float rang = 0.4f, rang2 = 0.5f;
        if(( (float)currentState.getY() < (float)targetState.getY()*rang && (float)currentState.getX() < (float)targetState.getX()*rang)){
            return ((value1) + (value2) * 0.01f);
        } else if ((float)currentState.getY() > (float)targetState.getY()*rang2 || (float)currentState.getX() > (float)targetState.getX()*rang2) {
            return ((value1) + (value2)*0.2f);
        }else{
            return ((value1) + ((value2)*0f));
        }
    }
}

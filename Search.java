import java.util.List;

public abstract class Search {
    private float[][] costMap;
    private Heuristic heuristic; 

    public Search(float[][] costMap, Heuristic heuristic){
        this.costMap = costMap;
        this.heuristic = heuristic;
    }


    /* TODO: Obtain the states that can be accessed from the current state.
     * Consider their cost, heuristic...
     * Feel free to change the input parameters and/or return type.
     */

    public List<State> DoSearch(State initialState, State targetState){

    }    

    //Se usa para encontrar operadores de estados derivados
    protected List<State> EvaluateOperators(State currentState, State targetState){

    }
}

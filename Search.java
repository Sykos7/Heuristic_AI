import Exceptions.PositionNotAllowedException;

import java.util.ArrayList;
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

    //Se usa para a partir de un estado obtenemos una lista con los posibles estados continuos
    //implementar best_first y A_estrella
    public List<State> DoSearch(State initialState, State targetState){
        //cojo la lista de EvaluateOperators y con cada nodo miro si está en pendientes o tratados y sinó los añado a pendientes
    }    

    //Find all possible paths for a State saving all results in a List;
    protected List<State> EvaluateOperators(State currentState, State targetState){
        if(currentState.equals(targetState)){
            return null;
        }
        List<State> State_list = new ArrayList<>();

        int x = currentState.getPosition()[0];
        int y = currentState.getPosition()[1];
        try{
            State state1 = new State(x-1,y);
            State_list.add(state1);
        }catch (PositionNotAllowedException e){}
        try{
            State state2 = new State(x+1,y);
            State_list.add(state2);
        }catch (PositionNotAllowedException e){}
        try{
            State state3 = new State(x,y-1);
            State_list.add(state3);
        }catch (PositionNotAllowedException e){}
        try{
            State state4 = new State(x,y+1);
            State_list.add(state4);
        }catch (PositionNotAllowedException e){}

        return State_list;
    }
}

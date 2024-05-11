
import java.util.ArrayList;
import java.util.List;

public abstract class Search {
    private float[][] costMap;
    private Heuristic heuristic;
    private int star;
    public List<State> ListDone = new ArrayList<>();
    public Search(float[][] costMap, Heuristic heuristic, int star){
        this.costMap = costMap;
        this.heuristic = heuristic;
        this.star=star;
    }



    /* Obtain the states that can be accessed from the current state.
     * Consider their cost, heuristic...
     * Feel free to change the input parameters and/or return type.
     */


    //Se usa para a partir de un estado obtenemos una lista con los posibles estados continuos
    public List<State> DoSearch(State initialState, State targetState){
        //cojo la lista de EvaluateOperators y con cada nodo miro si está en pendientes o tratados y sinó los añado a pendientes
        if(star==0){
            Best_Fisrt bf = new Best_Fisrt(costMap, heuristic);
            ListDone = bf.do_BF(initialState, targetState);
        }else{
            A_Star as = new A_Star(costMap, heuristic);
            ListDone = as.do_AStar(initialState, targetState);
        }
        return ListDone;
    }    

    //Find all possible paths for a State saving all results in a List;
    protected List<State> EvaluateOperators(State currentState, State targetState, List<State> list){
        int size_X = Main.MapInUse.getCharMap()[0].length;
        int size_Y = Main.MapInUse.getCharMap().length;
        List<State> State_list = new ArrayList<>();

        int x = currentState.getX();
        int y = currentState.getY();


            if( (x-1) >= 0 && (x-1) < size_X && y >= 0 && y < size_Y){
                State state1 = new State(x-1,y);
                if(state1.getLetter() != 'M' && !list.contains(state1))
                    State_list.add(state1);
            }
            if( (x+1) >= 0 && (x+1) < size_X && y >= 0 && y < size_Y){
                State state2 = new State(x + 1, y);
                if (state2.getLetter() != 'M' && !list.contains(state2))
                    State_list.add(state2);
            }
            if( x >= 0 && x < size_X && (y-1) >= 0 && (y-1) < size_Y){
                State state3 = new State(x, y - 1);
                if (state3.getLetter() != 'M' && !list.contains(state3))
                    State_list.add(state3);
            }
            if( x >= 0 && x < size_X && (y+1) >= 0 && (y+1) < size_Y){
                State state4 = new State(x, y + 1);
                if (state4.getLetter() != 'M' && !list.contains(state4))
                    State_list.add(state4);
            }
        return State_list;
    }

}

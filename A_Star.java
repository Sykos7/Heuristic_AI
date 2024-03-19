/*
* 1. Inicializar una lista abierta
* 2. Inicializar una lista cerrada
*       Meter el nodo de inicio en la lista abierta
* 3. Mintras la lista abierta no este vacía:
*   a. Encontrar el nodo con menor F en la lista abierta y llamarlo Q
*   b. Quitar Q de la lista abierta y generar todos sus sucesores (y diagonales)
*   c. Para cada sucesor:
*       i. Si el sucesor es el final (PARAR)
*       ii. Sinó, llamar a G(Heuristic) y H(distancia) para cada sucesor
*       iii. Si un nodo tiene la misma posicion que un sucesor esta en la lista abierta con un F menor se salta ese sucesor  por otra parte se añade
*       iv. Si un nodo tiene la misma posición que un sucesor en la lista cerrada con un F menor se salta, de lo contrario se añade a la lista abierta
*   fin loop (for)
*
*   d. añadir q en la lista cerrada
*   fin loop (while)
*
* */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class A_Star extends Search{
    List<State> visited = new ArrayList<>();
    State q = new State(0, 0);
    private float[][] costMap;
    private Heuristic heuristic;
    boolean find = false;
    public A_Star(float[][] costMap, Heuristic heuristic){
        super(costMap, heuristic, 1);
        this.costMap = costMap;
        this.heuristic = heuristic;
    }
    Comparator<State> compareHeuristic = Comparator.comparing(State::getHeuristic);
    PriorityQueue<State> Open = new PriorityQueue<>(compareHeuristic);
    List<State> Close = new ArrayList<>();
    public List<State> do_AStar(State initialState, State targetState){


        Open.add(initialState);

        while(!Open.isEmpty()){
            q = Open.poll();
            Close.add(q);
            List<State> TempList = EvaluateOperators(q, targetState, Close);
            if(!find){
                for (State aux : TempList) {
                    float heur = heuristic.Evaluate(aux, targetState, costMap);
                    aux.setHeuristic(heur);
                    aux.addHeuristic(aux.Distance_X() + aux.Distance_Y());
                    Open.add(aux);
                    if (aux.compareTo(targetState) == 0) {
                        find = true;
                        Close.add(aux);
                        break;
                    }

                }
            }
            if(find)
                break;

        }

        return Close;
    }

}

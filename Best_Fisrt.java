
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/*
@param Grafo, Nodo inicio, Nodo final, Int numero_nodos
1. crear cola de prioridad
2. insertar el estado inicial
3. mientras la cola de prioridad este vacía
    3.1. Sacar valor con menor heuristica y meterlo en la Lista de salia
    3.2 De este valor obtenemos sus hijos (si alguno es el resultado final salir)
    3.3 Miramos los que no hayan sido visitados (Los marcamos como visitados)
    3.4 Y los metemos por orden de heuristica en la cola de prioridad
4. Repetimos el paso 3 hasta que hayamos encontrado el final

 */

public class Best_Fisrt extends Search{
    private float[][] costMap;
    private Heuristic heuristic;
    Comparator<State> compareHeuristic = Comparator.comparing(State::getHeuristic);
    PriorityQueue<State> pq = new PriorityQueue<>(compareHeuristic);

    List<State> result = new ArrayList<>();
    List<State> visited = new ArrayList<>();
    State aux = new State(0, 0);
    public Best_Fisrt(float[][] costMap, Heuristic heuristic){
        super(costMap, heuristic, 0);
        this.costMap = costMap;
        this.heuristic = heuristic;
    }
    public List<State> do_BF(State initialState, State targetState) {
        pq.add(initialState);
        aux = initialState;
        while(!pq.isEmpty()){
            aux = pq.poll();
            result.add(aux);
            if(!visited.contains(aux)){
                visited.add(aux);
            }
            if(aux.compareTo(targetState) == 0){
                int index = 1;
                System.out.println("VISITED PATH");
            for (State state : visited) {
                System.out.println(index +" -> (" + state.getX() + "-" + state.getY()+"/ "+state.getPrice() + ") " + state.getHeuristic());
                index++;
            }
                return result;
            }
            List<State> TempList = EvaluateOperators(aux, targetState, visited);
            for(State aux2 : TempList) {
                float heur = heuristic.Evaluate(aux2, targetState, costMap);
                aux2.setHeuristic(heur);
                pq.add(aux2);
                visited.add(aux2);
            }
        }
        return result;
    }

}

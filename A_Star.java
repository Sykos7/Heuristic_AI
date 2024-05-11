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

// TODO:    - A* no descarta tractats,
//          - no sobreescriu, incorrecte o inexistent 
//          - afegiment a visitats
//          - utilitza la nomenclatura d'Pendientes/Tratados, que no és consistent amb el fet al Best First.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;



public class A_Star extends Search{
   

 
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
    List<State> Pendientes = new ArrayList<>();
    List<State> Tratados = new ArrayList<>();
    List<State> Camino = new ArrayList<>();
    public List<State> do_AStar(State initialState, State targetState){


        Pendientes.add(initialState);
        List<State> visitedList = new ArrayList<>();
        visitedList.add(initialState);
        while(!Pendientes.isEmpty()){
            int cont = 0;
            for(State k : Pendientes){
                if(cont == 0){
                    q = k;
                }else{
                    if(k.getHeuristic() < q.getHeuristic()){
                        q = k;
                    }
                }
                cont++;
            }
            Pendientes.remove(q);
            Tratados.add(q);
            List<State> TempList = EvaluateOperators(q, targetState, Tratados);
            if(!find){
                for (State aux : TempList) {
                    if(visitedList.contains(aux)){
                        visitedList.add(aux);
                    }
                    if(!Tratados.contains(aux)){
                        float heur = heuristic.Evaluate(aux, targetState, costMap);
                        aux.setHeuristic(q.getHeuristic());
                        aux.addHeuristic(aux.Distance_X() + aux.Distance_Y() + heur);

                        if(Pendientes.contains(aux)){
                            cont = 0;
                            for(State y : Pendientes){
                                if(aux.equals(y) && aux.getHeuristic() < y.getHeuristic()){
                                    Pendientes.remove(cont);
                                    Pendientes.add(aux);
                                    cont--;
                                }
                                cont++;
                            }
                        }else{
                            Pendientes.add(aux);
                        }
                        aux.setPadre(q);
                    }
                    if (aux.equals(targetState)) {
                        Tratados.add(aux);
                        find = true;
                        break;
                    }
                }
            }else{
                q = Tratados.get(Tratados.size()-2);
                Camino.add(q);
                while(q.getPadre() != null){
                    q = q.getPadre();
                    Camino.add(q);
                }
                Collections.reverse(Camino);
                break;
            }
                
        }
        return Camino;
    }

}

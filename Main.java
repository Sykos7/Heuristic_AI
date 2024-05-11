
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static char[][] OriginalCharMap = {
      {'P','N','N','N','P','P','P','P','P','P'},
      {'P','N','N','N','M','M','P','P','N','P'},
      {'P','N','N','N','M','M','N','N','N','P'},
      {'P','A','A','A','A','A','A','N','N','N'},
      {'P','N','A','C','A','A','A','A','A','N'},
      {'P','A','A','C','M','C','C','A','A','A'},
      {'P','A','M','A','M','M','C','A','A','A'},
      {'A','A','M','A','M','C','C','P','M','P'},
      {'A','A','M','C','M','C','P','P','P','P'},
      {'A','A','C','C','M','C','C','C','C','C'},
    };

    public static Map OriginalMap = new Map(OriginalCharMap);
    public static int AvgCost=0;
    public static Heuristic UseHeuristic;
    public static char[][] CustomCharMap = {
      {'N','A','N','N','M'},
      {'N','C','N','A','M'},
      {'A','P','M','C','C'},
      {'N','A','M','C','C'},
      {'N','M','C','A','P'},
    };
    public static Map CustomMap = new Map(CustomCharMap);
    public static Map MapInUse;
    public static List<State> result = new ArrayList<>();
    public static void main(String args[]){      

        MapInUse = new Map(OriginalCharMap);
        try{
            AvgCost = MapInUse.averageCost(MapInUse.getCostMap());
        }catch (Exception e){
            System.out.println("Error 1 : Reading map all COST are 0");
        }


      // Declare heuristics
      Heuristic[] heuristics = new Heuristic[3];
      heuristics[0] = Heuristics::Heuristic1;
      heuristics[1] = Heuristics::Heuristic2;
      heuristics[2] = Heuristics::Heuristic3;

        List<State> Final1 = new ArrayList<>();
        List<State> Final2 = new ArrayList<>();
        List<State> Final3 = new ArrayList<>();
        List<State> Final4 = new ArrayList<>();
        List<State> Final5= new ArrayList<>();
        List<State> Final6 = new ArrayList<>();


        //TODO: La quantitat de nodes tractats és mesura de manera incorrecte.
        System.out.println("BEST FIRST");
        System.out.println("Euclidean:");
        
        Search search1 = new Best_Fisrt(MapInUse.getCostMap(), heuristics[0]);
        Final1 = search1.DoSearch(MapInUse.getInitialState(), MapInUse.getFinalState());
        printStateList(Final1);
        
        System.out.println("\nManhattan:");
        Search search2 = new Best_Fisrt(MapInUse.getCostMap(), heuristics[1]);
        Final2 = search2.DoSearch(MapInUse.getInitialState(), MapInUse.getFinalState());
        printStateList(Final2);

        System.out.println("\nHybrid between Distance Euclidean & Economy:");
        Search search3 = new Best_Fisrt(MapInUse.getCostMap(), heuristics[2]);
        Final3 = search3.DoSearch(MapInUse.getInitialState(), MapInUse.getFinalState());
        printStateList(Final3);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("A STAR");
        System.out.println("Euclidean:");
        Search search4 = new A_Star(MapInUse.getCostMap(), heuristics[0]);
        Final4 = search4.DoSearch(MapInUse.getInitialState(), MapInUse.getFinalState());
        printStateList(Final4);
        
        System.out.println("\nManhattan:");
        Search search5 = new A_Star(MapInUse.getCostMap(), heuristics[1]);
        Final5 = search5.DoSearch(MapInUse.getInitialState(), MapInUse.getFinalState());
        printStateList(Final5);
        
        System.out.println("\nHybrid between Distance Euclidean & Economy:");
        Search search6 = new A_Star(MapInUse.getCostMap(), heuristics[2]);
        Final6 = search6.DoSearch(MapInUse.getInitialState(), MapInUse.getFinalState());
        printStateList(Final6);
    }
    public static void printStateList(List<State> list){
        for (State state : list) {
            System.out.print(" -> (" + state.getX() + "-" + state.getY()+"/ "+state.getPrice() + ") " + state.getHeuristic()+"\n");
        }
        System.out.println("\n Steps \t: "+list.size());
        float valor=0;
        for (State state : list) {
            valor += state.getPrice();
        }
        System.out.println(" Cost \t: "+valor);
    }

}




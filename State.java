public class State implements Comparable<State>{

    int[] position = new int[2];
    Map map;
    float price;
    int size_X;
    int size_Y;
    float heuristic = 0;
    State padre = null;


    public State getPadre() {
        return padre;
    }

    public void setPadre(State padre) {
        this.padre = padre;
    }

    public State(int Position_X, int Position_Y) {

            position[0] = Position_X;
            position[1] = Position_Y;
            map = Main.MapInUse;
            size_X = map.getCharMap()[0].length;
            size_Y = map.getCharMap().length;

            float[][] costMap = map.getCostMap();
            this.price = costMap[Position_X][Position_Y];
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof State) {
            return (position[0] == ((State)other).getX() && position[1] == ((State)other).getY());
        }
        return false;
    }

    @Override
    public int compareTo(State other){
        return Integer.compare(this.hashCode(), other.hashCode());
    }

    /*  if using data structures leveraging hash.
     *  IMPORTANT: It MUST be coherent with the "equals" method. That is, if two States are equal, they MUST have the same hashcode.
     *  However, due to collisions, two States with the same hashcode are not necessarily equal.
     */
    @Override
    public int hashCode() {

        String value1 = String.valueOf(position[0]);
        String value2 = String.valueOf(position[1]);

        String linked = value1 + value2;
        return Integer.parseInt(linked) + 7000;
    }
    public char getLetter(){
        return map.getCharMap()[position[0]][position[1]];
    }
    public int getX() {return position[0];}
    public int getY() {return position[1];}
    public float getPrice() {return price;}
    public int Distance_X() {
        return size_X - position[0];
    }
    public int Distance_Y() {
        return size_Y - position[1];
    }
    public float getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(float heuristic) {
        this.heuristic = heuristic;
    }
    public void addHeuristic(float heuristic) {
        this.heuristic = this.heuristic + heuristic;
    }
}

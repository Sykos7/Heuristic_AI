import Exceptions.LimitMapException;
import Exceptions.PositionNotAllowedException;

public class State{

    int[] position = new int[2];
    Map map;
    int size_X, size_Y;
    public State(int Position_X, int Position_Y) throws PositionNotAllowedException {
        position[0] = Position_X;
        position[1] = Position_Y;
        map = Main.MapInUse;
        size_X = map.getCharMap()[0].length;
        size_Y = map.getCharMap().length;
        if(position[0] < 0 || position[0] >= size_X || position[1] < 0 || position[1] >= size_X){
            throw new PositionNotAllowedException();
        }

    }
    public void go_X() throws LimitMapException {
        if(position[0] >= 0 || position[0] < size_X-1){
            position[0] = position[0]++;
        }else{
            throw new LimitMapException("go_X");
        }
    }
    public void back_X() throws LimitMapException {
        if(position[0] > 0 || position[0] < size_X){
            position[0] = position[0]--;
        }else{
            throw new LimitMapException("back_X");
        }
    }
    public void go_Y() throws LimitMapException {
        if(position[1] >= 0 || position[1] < size_Y-1){
            position[1] = position[1]++;
        }else{
            throw new LimitMapException("go_Y");
        }
    }
    public void back_Y() throws LimitMapException {
        if(position[1] > 0 || position[1] < size_X){
            position[1] = position[1]--;
        }else{
            throw new LimitMapException("back_Y");
        }
    }
    @Override
    public boolean equals(Object other) {
        if (other instanceof State) {
            return (position[0] == ((State)other).getPosition()[0] && position[1] == ((State)other).getPosition()[1]);
        }
        return false;
    }


    /* TODO if using data structures leveraging hash.
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

    public int[] getPosition() {
        return position;
    }

    public int Distance_X() {
        return (size_X - 1) - position[0];
    }
    public int Distance_Y() {
        return (size_Y - 1) - position[1];
    }
}

package Exceptions;

public class PositionNotAllowedException extends Exception{
    public PositionNotAllowedException(){
        super("Error: Position out of the actual map");
    }
}

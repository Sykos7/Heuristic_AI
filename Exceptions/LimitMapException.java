package Exceptions;

public class LimitMapException extends Exception{
    public LimitMapException(String sentence){
        super(sentence + " : Ties to go out of map");
    }
}

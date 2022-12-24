package services;

public class NotValidPINException extends Exception{
    public NotValidPINException(String message){
        super(message);
    }
}

package exceptions;

public class IncorrectArgumentException extends Exception{

    public IncorrectArgumentException(String message) {
        super(message);
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

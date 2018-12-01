package projeto.exception;

public class EmailRegistradoException extends Exception {

    public EmailRegistradoException(){
        super();
    }
    public EmailRegistradoException(String message){
        super(message);
    }
}

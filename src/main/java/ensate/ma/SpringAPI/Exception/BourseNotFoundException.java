package ensate.ma.SpringAPI.Exception;

public class BourseNotFoundException extends RuntimeException {
    public BourseNotFoundException(String message) {
        super(message);
    }
}
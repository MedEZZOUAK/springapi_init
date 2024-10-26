package ensate.ma.SpringAPI.Exception;

public class CandidatureNotFoundException extends RuntimeException {
    public CandidatureNotFoundException(String message) {
        super(message);
    }
}
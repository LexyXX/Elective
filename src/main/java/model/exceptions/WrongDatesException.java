package model.exceptions;

/**
 * Created by lexy on 01.02.17.
 */
public class WrongDatesException extends Exception {
    public WrongDatesException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "starting date > ending date or starting date < today";
    }
}

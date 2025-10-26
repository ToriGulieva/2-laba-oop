public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String message) {
        super("Модель с именем '" + message + "' не найден");
    }
}
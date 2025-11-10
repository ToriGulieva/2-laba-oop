public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(String modelName) {
        super("Модель с именем '" + modelName + "' уже существует");
    }
}
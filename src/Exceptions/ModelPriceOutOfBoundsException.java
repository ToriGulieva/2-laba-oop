public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException() {
        super("Цена модели не может быть отрицательной");
    }
}
public class ByteReadException extends RuntimeException {
    public ByteReadException() {
        super("Ошибка записи в байтовый поток");
    }
}

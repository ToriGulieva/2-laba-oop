public class ByteWriteException extends RuntimeException {
    public ByteWriteException() {
        super("Ошибка чтения из байтового потока");
    }
}

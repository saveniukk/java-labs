package task3;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptionFilterOutputStream extends FilterOutputStream {
    private final int key;

    public EncryptionFilterOutputStream(OutputStream out, int key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b + key);
    }
}

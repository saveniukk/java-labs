package task3;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptionFilterInputStream extends FilterInputStream {
    private final int key;

    public DecryptionFilterInputStream(InputStream in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int b = super.read();

        if (b == -1) {
            return -1;
        }

        return b - key;
    }
}
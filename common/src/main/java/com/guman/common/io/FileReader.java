package com.guman.common.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author duanhaoran
 * @since 2019/12/19 8:11 PM
 */
public class FileReader {

    private FileChannel fileChanne;

    private String charset;

    private ByteBuffer byteBuffer;

    private int bufferSize;

    public FileReader(FileChannel fileChannel, int bufferSize, String charset) {
        this.fileChanne = fileChannel;
        this.charset = charset;
        this.bufferSize = bufferSize;
        // byteBuffer = ByteBuffer.allocate(bufferSize) ;
    }

    public String readline() throws IOException {

        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(bufferSize);

            int len = fileChanne.read(byteBuffer);

            if (len == -1)
                return null;

            byteBuffer.flip();
        }

        byte[] bb = new byte[bufferSize];

        int i = 0;

        while (true) {

            while (byteBuffer.hasRemaining()) {

                byte b = byteBuffer.get();

                if ('\r' == b || '\n' == b) {

                    if (byteBuffer.hasRemaining()) {
                        byte n = byteBuffer.get();

                        if ('\n' != n) {
                            byteBuffer.position(byteBuffer.position() - 1);
                        }

                    } else {

                        byteBuffer.clear();

                        int len = fileChanne.read(byteBuffer);

                        byteBuffer.flip();

                        if (len != -1) {
                            byte n = byteBuffer.get();

                            if ('\n' != n) {
                                byteBuffer.position(byteBuffer.position() - 1);
                            }
                        }

                    }

                    return new String(bb, 0, i, charset);

                } else {

                    if (i >= bb.length) {

                        bb = Arrays.copyOf(bb, bb.length + bufferSize + 1);
                    }

                    bb[i++] = b;
                }

            }

            byteBuffer.clear();
            int len = fileChanne.read(byteBuffer);
            byteBuffer.flip();

            if (len == -1 && i == 0) {
                return null;
            }

        }

    }

    public void close() throws IOException {
        this.fileChanne.close();
    }

}

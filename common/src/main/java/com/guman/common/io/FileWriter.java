package com.guman.common.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author duanhaoran
 * @since 2019/12/19 8:38 PM
 */
public class FileWriter {

    private FileChannel fileChanne;

    public FileWriter(FileChannel fileChannel) {
        this.fileChanne = fileChannel;
    }

    public void writeLine(String line) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(line.concat("\n").getBytes());
        fileChanne.write(buffer);
    }

    public void close() throws IOException {
        fileChanne.close();
    }


}

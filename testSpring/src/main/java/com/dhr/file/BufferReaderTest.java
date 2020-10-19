package com.dhr.file;

import sun.tools.java.Environment;
import sun.tools.java.ScannerInputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author duanhaoran
 * @since 2020/10/15 4:35 PM
 */
public class BufferReaderTest {

    public static void main(String[] args) {
        int[] arrays = null;
        int n = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s = br.readLine();
            String[] split = s.split(" ");
            arrays = new int[Integer.valueOf(split[0])];
            n = Integer.valueOf(split[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

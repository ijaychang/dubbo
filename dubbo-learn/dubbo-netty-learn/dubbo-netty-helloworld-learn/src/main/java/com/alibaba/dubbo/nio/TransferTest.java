package com.alibaba.dubbo.nio;

import java.io.*;
import java.nio.channels.FileChannel;

public class TransferTest {
    public static void main(String[] args) throws IOException {
        //transferToTest();
        transferFromTest();
    }

    private static void transferFromTest() throws IOException {
        File file = new File("E:\\tmp\\logs\\spring.log");
        FileInputStream fis = new FileInputStream(file);

        File file2 = new File("E:\\tmp\\logs\\spring2.log");
        FileOutputStream fos = new FileOutputStream(file2);


        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        fosChannel.transferFrom(fisChannel,0,file.length());


        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
    }

    private static void transferToTest() throws IOException {
        File file = new File("E:\\tmp\\logs\\spring.log");
        FileInputStream fis = new FileInputStream(file);

        File file2 = new File("E:\\tmp\\logs\\spring2.log");
        FileOutputStream fos = new FileOutputStream(file2);


        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        fisChannel.transferTo(0,file.length(),fosChannel);


        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
    }
}

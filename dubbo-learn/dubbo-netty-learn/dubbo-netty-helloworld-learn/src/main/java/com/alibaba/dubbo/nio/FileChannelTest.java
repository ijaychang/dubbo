package com.alibaba.dubbo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        //simulateSmallFileTransfer();
        simulateLargeFileTransfer();


    }

    private static void simulateLargeFileTransfer() throws IOException {
        File file = new File("E:\\tmp\\logs\\spring.log");
        FileInputStream fis = new FileInputStream(file);

        File file2 = new File("E:\\tmp\\logs\\spring2.log");
        FileOutputStream fos = new FileOutputStream(file2);

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer buff = ByteBuffer.allocate(8);

        int bytesCount  = -1;
        while ((bytesCount  = fisChannel.read(buff)) > 0) {
            buff.flip();
            fosChannel.write(buff);
            buff.clear();
        }
        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
        buff.clear();
        System.out.println("FileChannelTest.simulateLargeFileTransfer end");
    }

    private static void simulateSmallFileTransfer() throws IOException {
        File file = new File("E:\\tmp\\logs\\spring.log");
        FileInputStream fis = new FileInputStream(file);

        File file2 = new File("E:\\tmp\\logs\\spring2.log");
        FileOutputStream fos = new FileOutputStream(file2);


        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        fisChannel.read(byteBuffer);

        byteBuffer.flip();

        fosChannel.write(byteBuffer);

        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
    }
}

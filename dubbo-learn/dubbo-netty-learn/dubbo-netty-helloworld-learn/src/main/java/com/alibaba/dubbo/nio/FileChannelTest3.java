package com.alibaba.dubbo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest3 {
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) throws IOException {
        File file = new File("F:\\Downloads\\SystemOperate\\os\\ubuntu-21.04-live-server-amd64.iso");
        FileInputStream fis = new FileInputStream(file);

        File file2 = new File("F:\\Downloads\\SystemOperate\\os\\ubuntu-21.04-live-server-amd64-2.iso");
        FileOutputStream fos = new FileOutputStream(file2);

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        long start = System.currentTimeMillis();
        ByteBuffer buff = ByteBuffer.allocate(1024 * 1024);
        //ByteBuffer buff = ByteBuffer.allocateDirect(1024 * 1024);

        int read = -1;
        while ((read = fisChannel.read(buff)) != -1) {
            buff.flip();
            fosChannel.write(buff);
            buff.clear();
        }
        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}

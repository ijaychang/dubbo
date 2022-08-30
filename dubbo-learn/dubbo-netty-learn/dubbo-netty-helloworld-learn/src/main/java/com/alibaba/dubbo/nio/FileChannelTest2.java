package com.alibaba.dubbo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class FileChannelTest2 {
    /**
     * FileChannel 分散读，聚合写示例
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FileChannelTest2.class.getResource("/1.txt").getFile());
        File file2 = new File(FileChannelTest2.class.getResource("/").getPath() + "2.txt");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file2);

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();


        ByteBuffer buff1 = ByteBuffer.allocate(5);
        ByteBuffer buff2 = ByteBuffer.allocate(5);
        ByteBuffer buff3 = ByteBuffer.allocate(5);
        ByteBuffer[] buffs = new ByteBuffer[]{buff1, buff2, buff3};

        long readCount = -1;
        long sumLength = 0;
        while ((readCount = fisChannel.read(buffs)) != -1) {
            System.out.println("readCount:" + readCount + ",sumLength:" + sumLength);
            sumLength += readCount;
            Arrays.stream(buffs).map(buff -> "position:" + buff.position() + ",limit:" + buff.limit() + ",capacity:" + buff.capacity()).forEach(System.out::println);
            Arrays.stream(buffs).forEach(ByteBuffer::flip);
            fosChannel.write(buffs);
            Arrays.stream(buffs).forEach(ByteBuffer::clear);
        }

        System.out.println("sumLength = " + sumLength);
        fis.close();
        fos.close();
        fisChannel.close();
        fosChannel.close();
    }

}

package com.allinpay.controller.ali;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@Slf4j
public class AnnotationController {
    public static void main(String[] args) throws IOException {
        readSingle();

        readBytes();

        readBytesByCondition();

        writeBytes();
    }

    private static void writeBytes() {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("D:/writer.txt");
            String str = "hello world";
            byte[] bytes = str.getBytes();
            //将字节数组写入io流中
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void readBytesByCondition() throws IOException {
        InputStream inputStream = new FileInputStream("D:/iotest.txt");
        byte[] b = new byte[6];
        //读取多个字节到字节数组中
        while ((inputStream.read(b, 1, 5)) != -1) {
            System.out.println(new String(b, "gbk"));
            b = new byte[6];
        }
        inputStream.close();
    }

    private static void readBytes() throws IOException {
        InputStream inputStream = new FileInputStream("D:/iotest.txt");
        byte[] b = new byte[6];
        //读取多个字节到字节数组中
        while ((inputStream.read(b)) != -1) {
            System.out.println(new String(b, "gbk"));
        }
        inputStream.close();
    }

    private static void readSingle() throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\iotest.txt");
        //读取一个字节并返回
        int ch;
        while ((ch = inputStream.read()) != -1) {
            System.out.println(ch);
        }
        inputStream.close();
    }
}

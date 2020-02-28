package com.allinpay.controller.ali;

import com.allinpay.repository.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@Slf4j
public class AnnotationController {
    public static void main(String[] args) throws Exception {
//        readSingle();
//
//        readBytes();
//
//        readBytesByCondition();
//
//        writeBytes();
//        writeDouble();
//        writeBuffer();
//        writeObject();
//        encodeStr();
//        writeString();
//        System.out.println(System.getProperty("file.encoding"));
//        System.out.println(Charset.defaultCharset());
//        writeMiddle();
//        readLine();
//        printStr();
        buffer();
    }

    private static void buffer() throws Exception {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("d://print1.txt"));
        int content;
        byte[] bytes = new byte[4];
        while ((inputStream.read(bytes)) != -1) {

        }
        inputStream.close();
    }

    private static void printStr() throws Exception {
        //当做过滤流使用
        OutputStream outputStream = new FileOutputStream("d://print.txt");
        Writer out = new OutputStreamWriter(outputStream);
        PrintWriter writer = new PrintWriter(out);
        writer.println("我在清华园");
        writer.println("我在通联支付");
        writer.println("我在技术部");
        writer.close();
        //当做节点流使用，内部做了包装
        PrintWriter writer1 = new PrintWriter("d://print1.txt");
        writer1.println("我是谁");
        writer1.close();
        //当做桥转换使用
        PrintWriter writer2 = new PrintWriter(new FileOutputStream("d://print2.txt"));
        writer2.println("我在哪里");
        writer2.close();
    }

    private static void readLine() throws IOException {
        //创建字节流
        InputStream inputStream = new FileInputStream("d://string.txt");
        //通过桥转换转为字符流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
        //封装过滤流
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //读数据
        String content = null;
        while ((content = bufferedReader.readLine()) != null) {
            System.out.println(content);
        }
        //关闭外层流
        bufferedReader.close();
    }

    private static void writeMiddle() throws IOException {
        OutputStream outputStream = new FileOutputStream("d://string.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "GBK");
        outputStreamWriter.write("你好，中国，java");
        outputStreamWriter.close();

        Reader reader = new FileReader("d://string.txt");
        char[] chars = new char[1];
        while (reader.read(chars) != -1) {
            System.out.println(new String(chars));
        }
    }

    private static void writeString() throws IOException {
        Writer writer = new FileWriter("d://string.txt");
        writer.write("你好，中国，java");
        writer.close();

        Reader reader = new FileReader("d://string.txt");
        char[] chars = new char[1];
        while (reader.read(chars) != -1) {
            System.out.println(new String(chars));
        }
    }

    private static void encodeStr() throws UnsupportedEncodingException {
        String str = "我爱中国 java";
        byte[] bytes = str.getBytes();
        String decode = new String(bytes, "gbk");
        System.out.println(decode);
    }

    private static void writeObject() throws Exception {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("d://object.dat"));
        User user = new User();
        user.setAge(12);
        user.setName("tg");
        outputStream.writeObject(user);

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("d://object.dat"));
        User readUser = (User) inputStream.readObject();
        System.out.println(readUser.getName() + "=" + readUser.getAge());
    }

    private static void writeBuffer() throws IOException {
        OutputStream out = new FileOutputStream("d://buffer.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out, 6);
        bufferedOutputStream.write("hello".getBytes());
        bufferedOutputStream.write("hello".getBytes());
        bufferedOutputStream.close();
//        bufferedOutputStream.flush();
    }

    private static void writeDouble() throws FileNotFoundException {
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        try {
            outputStream = new DataOutputStream(new FileOutputStream("D:/double.dat"));
            outputStream.writeDouble(3.14);

            inputStream = new DataInputStream(new FileInputStream("D:/double.dat"));
            double pai = inputStream.readDouble();
            System.out.println(pai);
        } catch (IOException e) {
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

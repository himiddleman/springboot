package com.allinpay.controller.ali;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * author: tanguang
 * data: 2021/3/5
 * 加密机工具类
 **/
public class SocketUtil {
    private static String host = "192.168.12.243";
    private static int port = 6666;

    // 执行加密机指令
    public static boolean execute(HSMCommand command) throws Exception {
        Socket sock = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            // 建立Socket连接
            sock = new Socket(host, port);
            sock.setSoTimeout(5000);
            // 封装消息报文，并送给加密机
            os = sock.getOutputStream();
            command.packetInputField(os);
            os.flush();
            // 从加密机接受响应报文
            is = sock.getInputStream();
            return command.parseOutputField(is);
        } finally {
            // 关闭加密机读写流和连接
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
                if (sock != null) {
                    sock.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String str = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzhKAnOKrf0OaU62Er0jLpt62GGdww8QE82EFTdcHXQiAHfgspI20AcRE4FiBxGnpBQOJ7c19c3ZjdxtR3d+NjUaqt+jva0RvieONQ1gjkNb/PQsAQbhEtSML5M9xhw+BjlbGVUmFk2Ckcx0KFZMtqiB/3DprKZ7M/7jD3ZB63BKd87agHb/bYYjPhEQTU0ojWgzUF2QM9ldHgA9EIhqLe9cXZ/fE7QbY3txKRBSVZrBiowpfrgLWWG/i20fXSPtszO/ratz/zIGgdZlHoq9AxntkxXWhjJ/lYdjfh26ZdqJ7qPBVWXQFeY5dJjgTqyPwkBhyxPpJ4a8xKB72aqHzBQIDAQAB";
            System.out.println(SocketUtil.encrypt(str));
            System.out.println(SocketUtil.decrypt(encrypt(str)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String source) throws Exception {
        HSMCommand command = new Command0X72(source.getBytes(), (byte) 1);
        execute(command);
        byte[] md = ((Command0X72) command).getMd();
        return bytesToHexString(md);
    }

    public static String decrypt(String target) throws Exception {
        byte[] bytes = hexStringToBytes(target);
        HSMCommand command = new Command0X72(bytes, (byte) 0);
        execute(command);
        byte[] md = ((Command0X72) command).getMd();
        return new String(md);
    }

    private static byte[] hexStringToBytes(String target) {
        byte[] bytes = new byte[target.length() / 2];
        //ed aa 93 6e 6a b0 ea 77
        int first = 0;
        int last = first + 2;
        for (int i = 0; i < bytes.length; i++) {
            String hexString = target.substring(first, last);
            bytes[i] = Integer.valueOf(hexString, 16).byteValue();
            first = last;
            last += 2;
        }
        return bytes;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String sTmp;
        for (int i = 0; i < bytes.length; i++) {
            sTmp = Integer.toHexString(0xFF & bytes[i]);
            if (sTmp.length() < 2)
                sb.append(0);
            sb.append(sTmp.toUpperCase());
        }
        return sb.toString();
    }

}

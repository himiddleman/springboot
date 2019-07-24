package com.allinpay.core.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

public class FTPUtil {

//    private static Logger logger = Logger.getLogger(FTPUtil.class);
//
//    private static String classPath=CameraListener.class.getResource("/").getPath();
//    private static String filesPath = classPath.substring(0, classPath.indexOf("WEB-INF")) + "file" ;

    //从本地文件获取各种属性
//    private static String ftpIP="47.99.172.60";
    private static String ftpIP = "10.48.1.253";
    private static Integer ftpPort = 21;
    private static String ftpUserName = "etcprouser";
    private static String ftpPassword = "etcprouser";
    private static String ftpEncode = "UTF-8";
    private FTPClient ftpClient;
//    private static String localPath=FTPConfig.getLocalFilePath();

    public FTPUtil() {

    }

    public static void main(String[] args) {
        FTPUtil ftpClient = new FTPUtil();
        ftpClient.connectServer();

        //文件上传
        try {
            File file = new File("D:/lala.txt");
            InputStream stream = new FileInputStream(file);
            String ftpPath = "/home/etcprouser";
            String fileName = "ftp";
            boolean b = ftpClient.checkSubfolder(ftpPath, fileName);
            if (stream != null && b) {
                ftpClient.upload(stream, "test1.txt", fileName);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //文件下载
        FTPUtil ftpClient1 = new FTPUtil();
        ftpClient1.connectServer();
        ftpClient1.download("/home/etcprouser/ftp");
    }

    public synchronized boolean connectServer() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding(ftpEncode);//解决上传文件时文件名乱码
        ftpClient.setConnectTimeout(30 * 1000);
        int reply = 0;
        try {
            // 连接至服务器
            ftpClient.connect(ftpIP, ftpPort);
            // 登录服务器
            ftpClient.login(ftpUserName, ftpPassword);
            //登陆成功，返回码是230
            reply = ftpClient.getReplyCode();
            // 判断返回码是否合法
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return false;
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    //判断ftp服务器文件是否存在
    public boolean existFile(String path) {
        boolean flag = false;
        FTPFile[] ftpFileArr;
        try {
            ftpFileArr = ftpClient.listFiles(path);
            if (ftpFileArr.length > 0) {
                flag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //删除ftp文件
    public synchronized boolean deleteFile(String pathname, String filename) {
        boolean flag = false;
        try {
            System.out.println("开始删除文件");
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            System.out.println("删除文件成功");
        } catch (Exception e) {
            System.out.println("删除文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    //从FTP server下载到本地文件夹
    public synchronized boolean download(String path) {
        boolean flag = false;
        FTPFile[] fs = null;
        try {
            fs = ftpClient.listFiles(path);
            if (fs.length < 0) {
                return flag;
            }
            //1、遍历FTP路径下所有文件
            for (FTPFile file : fs) {

                File localFile = new File("C:/Users/Administrator/Desktop/临时/" + file.getName());
                //2、保存到本地
                OutputStream os = new FileOutputStream(localFile);
                //retrieveFile(FTP服务端的源路径),这个路径要和listFiles的路径一致
                ftpClient.retrieveFile("/home/etcprouser/ftp/" + file.getName(), os);
                //3、删除FTP中的上面保存的文件
                //循环外关闭，读一个关闭一次挺慢的
//                is.flush();
//                is.close();
                os.flush();
                os.close();
            }
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public synchronized boolean upload(InputStream inputStream, String fileName, String path) {
        try {

            //切换工作路径，设置上传的路径
            ftpClient.changeWorkingDirectory(path);
            //设置1M缓冲
            ftpClient.setBufferSize(1024);
            // 外网ip上传主动模式(默认) 内网ip上传被动模式
//             ftpClient.enterLocalPassiveMode();
            // 设置以二进制方式传输
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            /*
             * 第一个参数：服务器端文档名
             * 第二个参数：上传文档的inputStream
             * 在前面设置好路径，缓冲，编码，文件类型后，开始上传
             */
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeClient();
        }

    }

    public boolean checkSubfolder(String path, String subfolderName) {
        try {
            //切换到FTP根目录
            ftpClient.changeWorkingDirectory(path);
            //查看根目录下是否存在该文件夹
            InputStream is = ftpClient.retrieveFileStream(new String(subfolderName.getBytes("UTF-8")));
            if (is == null || ftpClient.getReplyCode() == FTPReply.FILE_UNAVAILABLE) {
                //若不存在该文件夹，则创建文件夹
                return createSubfolder(path, subfolderName);
            }
            if (is != null) {
                is.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized boolean createSubfolder(String path, String subfolderName) {
        try {
            ftpClient.changeWorkingDirectory(path);
            ftpClient.makeDirectory(subfolderName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 断开与远程服务器的连接
     */
    public void closeClient() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
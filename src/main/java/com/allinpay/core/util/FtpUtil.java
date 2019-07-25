package com.allinpay.core.util;

import com.allinpay.core.constant.CommonConstant;
import com.allinpay.core.exception.AllinpayException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Slf4j
public class FtpUtil {
    private static FTPClient ftpClient = new FTPClient();

    private FtpUtil() {
    }

    public static void main(String[] args) {
        try {
            FtpUtil.upload("/usr/local/ftp", "HLQSMX20190725.txt", "D:/lala.txt");

            FtpUtil.download("/usr/local/ftp", "HLQSMX20190725.txt", "C:/Users/Administrator/Desktop/临时/");

            FtpUtil.download("/usr/local/ftp", "C:/Users/Administrator/Desktop/临时/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载目标目录下的所有文件到本地
     *
     * @param remotePath ftp目录
     * @param localPath  本地目录
     */
    public static void download(String remotePath, String localPath) {
        OutputStream os = null;
        try {
            connectServer();
            ftpClient.changeWorkingDirectory(remotePath);
            FTPFile[] fs = ftpClient.listFiles(remotePath);
            for (FTPFile file : fs) {
                String fileName = file.getName();
                //找到当天的清算文件和退款文件,一般就两个文件
                if (fileName.contains(CommonConstant.ETC_PLATFORM) && fileName.contains(yesterdayStr())) {
                    File localFile = new File(localPath);
                    localFile.mkdirs();
                    //2、保存到本地
                    os = new FileOutputStream(localPath + fileName);
                    //retrieveFile(FTP服务端的源路径),这个路径要和listFiles的路径一致
                    ftpClient.retrieveFile(file.getName(), os);
                }
            }
        } catch (IOException e) {
            log.error("文件下载IO异常！", e);
            throw new AllinpayException("80004", "文件下载IO异常！");
        } finally {
            if (Objects.nonNull(os)) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            closeClient();
        }
    }

    /**
     * ftp目录下下载指定文件
     *
     * @param remotePath ftp目录
     * @param remoteFile ftp文件名
     * @param localPath  本地目录
     */
    public static void download(String remotePath, String remoteFile, String localPath) {
        try {
            connectServer();
            ftpClient.changeWorkingDirectory(remotePath);
            File localFile = new File(localPath);
            localFile.mkdirs();
            OutputStream os = new FileOutputStream(new File(localPath + remoteFile));
            ftpClient.retrieveFile(remoteFile, os);
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error("文件下载IO异常！", e);
            throw new AllinpayException("80004", "文件下载IO异常！");
        } finally {
            closeClient();
        }
    }

    /**
     * 文件上传
     *
     * @param remotePath ftp目录
     * @param remoteFile ftp文件名
     * @param localPath  本地目录
     */
    public static void upload(String remotePath, String remoteFile, String localPath) {
        InputStream inputStream = null;
        try {
            connectServer();
            //切换工作路径，设置上传的路径
            ftpClient.changeWorkingDirectory(remotePath);
            //设置1M缓冲
            ftpClient.setBufferSize(1024);
            // 外网ip上传主动模式(默认) 内网ip上传被动模式
            //ftpClient.enterLocalPassiveMode();
            // 设置以二进制方式传输
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            inputStream = new FileInputStream(new File(localPath));
            ftpClient.storeFile(remoteFile, inputStream);
        } catch (IOException e) {
            log.error("文件上传IO异常！", e);
            throw new AllinpayException("80002", "文件上传IO异常！");
        } finally {
            if (Objects.nonNull(inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            closeClient();
        }
    }

    private static void connectServer() {
        try {
            //解决上传文件时文件名乱码
            ftpClient.setControlEncoding(CommonConstant.FTP_ENCODE);
            //设置超时时间为30s
            ftpClient.setConnectTimeout(30 * 1000);
            // 连接至服务器
            ftpClient.connect(CommonConstant.FTP_HOST, CommonConstant.FTP_PORT);
            // 登录服务器
            ftpClient.login(CommonConstant.FTP_USERNAME, CommonConstant.FTP_PASSWORD);
            //登陆成功，返回码是230
            int reply = ftpClient.getReplyCode();
            // 判断返回码是否合法
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            log.error("连接FTP服务器失败！", e);
            throw new AllinpayException("80001", "连接FTP服务器失败！");
        }
    }

    /**
     * 断开与远程服务器的连接
     */
    private static void closeClient() {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error("断开与FTP服务器连接异常！", e);
                throw new AllinpayException("80003", "断开与FTP服务器连接异常！");
            }
        }
    }

    private static String yesterdayStr() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, -1);
        return dateFormat.format(instance.getTime());
    }

}
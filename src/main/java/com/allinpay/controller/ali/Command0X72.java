package com.allinpay.controller.ali;

import com.allinpay.core.exception.AllinpayException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * author: tanguang
 * data: 2021/3/5
 * 用区域主密钥对数据加/解密
 **/
@Data
@Slf4j
public class Command0X72 extends HSMCommand {
    // 命令，0x72 1个字节
    private byte command = 0x72;
    // 区域主密钥索引529 2个字节
    private short secretIndex = 529;
    // 初始向量 8个字节
    private long initVect = 0;
    // 加/解密标识 1个字节
    private byte enOrDe;
    // 算法标识 1个字节
    private byte algorithm = 0;
    // 数据长度LEN 2个字节
    private short dataLen;
    // 加解密数据
    private byte[] data;
    //响应数据
    private byte[] md;


    public Command0X72(byte[] data, byte enOrDe) {
        this.data = data;
        this.enOrDe = enOrDe;
    }

    @Override
    public void packetInputField(OutputStream os) throws Exception {
        ByteBuffer buf = ByteBuffer.allocate(data.length + 15);
        buf.put(command);
        buf.putShort(secretIndex);
        buf.putLong(initVect);
        buf.put(enOrDe);
        buf.put(algorithm);
        buf.putShort((short) data.length);
        buf.put(data);
        os.write(buf.array());
    }

    @Override
    public boolean parseOutputField(InputStream is) throws Exception {
        DataInputStream dis = new DataInputStream(is);
        super.parseOutputField(dis);
        //成功后解析数据
        //数据长度
        short dataLen = dis.readShort();
        log.info("数据长度：{}", dataLen);
        //读响应数据
        md = new byte[dataLen];
        dis.read(md);
        return true;
    }

    @Override
    public void parseErrorCode(int errorCode) {
        if (errorCode == 12) {
            throw new AllinpayException("10010", "非法银行主密钥索引号");
        } else if (errorCode == 16) {
            throw new AllinpayException("10011", "无效的DES算法模式");
        } else if (errorCode == 44) {
            throw new AllinpayException("10012", "非法银行主密钥");
        } else if (errorCode == 89) {
            throw new AllinpayException("10013", "输入的数据长度错误");
        }
    }
}

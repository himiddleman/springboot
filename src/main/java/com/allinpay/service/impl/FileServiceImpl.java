package com.allinpay.service.impl;

import com.allinpay.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * 文件增量更新
 */
@Service
@Slf4j
public class FileServiceImpl implements IFileService {
    @Override
    public void doCollect(HttpServletRequest request) {
        MultipartHttpServletRequest mpRequest
                = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = mpRequest.getMultiFileMap().get("upload");
        int size = fileList.size();
        log.info("需要处理的文件的个数：", size);
        //对文件进行批量处理
        if (CollectionUtils.isEmpty(fileList)) {
            log.warn("未上传文件，无需操作！");
        }
        for (int i = 0; i < fileList.size(); i++) {
            //对文件进行处理,存入数据
            dealFile(fileList.get(i));
        }
    }

    /**
     * 文件操作
     *
     * @param file
     */
    private void dealFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();

        } catch (Exception e) {

        }
    }
}

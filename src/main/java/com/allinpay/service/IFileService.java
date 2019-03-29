package com.allinpay.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件增量更新
 */
public interface IFileService {

    void doCollect(HttpServletRequest request);
}

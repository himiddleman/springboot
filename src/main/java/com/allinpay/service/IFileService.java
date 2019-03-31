package com.allinpay.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 文件增量更新
 */
public interface IFileService {

    Map<String, String> doCollect(HttpServletRequest request);
}

package com.allinpay.controller.dev;

import com.allinpay.core.common.ResponseData;
import com.allinpay.core.constant.enums.BizEnums;
import com.allinpay.core.exception.AllinpayException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.OutputStream;
import java.util.Random;

@Controller
@RequestMapping("/sys")
@Slf4j
public class LoginController {
    /**
     * @Description: 用户登录
     * @Param: session, captcha, username, password
     * @Return: ResponseData
     */
    @RequestMapping("login")
    @ResponseBody
    public ResponseData login(HttpSession session, String captcha,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password) {
        if (StringUtils.isNotBlank(captcha)) {
            if (captcha.equalsIgnoreCase((String) session.getAttribute("captcha"))) {
                session.setAttribute("captcha", null);
            } else {
                session.setAttribute("captcha", null);
                throw new AllinpayException(BizEnums.CAPTCHA_INVALIDATE.getCode(), BizEnums.CAPTCHA_INVALIDATE.getMsg());
            }
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            //设置认证时间的时效,即超过该时间需要重新登录。 设置为10分钟，单位毫秒
            subject.getSession().setTimeout(600000);
            return ResponseData.success().setData(subject.getPrincipal());
        } catch (AuthenticationException e) {
            log.error("认证失败:{}", e.getMessage());
            throw new AllinpayException(BizEnums.USER_AUTHENTICATION_FAIL.getCode(), BizEnums.USER_AUTHENTICATION_FAIL.getMsg());
        }
    }

    /**
     * @Description: 退出
     * @Param:
     * @Return: ResponseData
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/web/login";
    }

    /**
     * @Description: 获取验证码
     * @Param: response，request
     * @Return: ResponseData
     */
    @GetMapping(value = "/captcha")
    @ResponseBody
    public ResponseData captcha(HttpServletResponse response, HttpServletRequest request) {
        try {
            ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
            ColorFactory cf = new SingleColorFactory(new Color(25, 60, 170));
            RandomWordFactory wf = new RandomWordFactory();
            Random r = new Random();
            CurvesRippleFilterFactory crff = new CurvesRippleFilterFactory(cf);
            DiffuseRippleFilterFactory dirff = new DiffuseRippleFilterFactory();
            MarbleRippleFilterFactory mrff = new MarbleRippleFilterFactory();
            cs.setWordFactory(wf);
            cs.setColorFactory(cf);
            cs.setWidth(140);
            cs.setHeight(60);
            response.setContentType("image/png");
            response.setHeader("cache", "no-cache");
            wf.setMaxLength(4);
            wf.setMinLength(4);
            HttpSession session = request.getSession(true);
            OutputStream os = response.getOutputStream();
            switch (r.nextInt(3)) {
                case 0:
                    cs.setFilterFactory(crff);
                    break;
                case 1:
                    cs.setFilterFactory(mrff);
                    break;
                default:
                    cs.setFilterFactory(dirff);
                    break;
            }
            String captcha = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
            session.setAttribute("captcha", captcha.toLowerCase());
            os.flush();
            os.close();
            return ResponseData.success().setData(captcha);
        } catch (Exception e) {
            log.error("验证码获取失败", e);
            throw new AllinpayException(BizEnums.CAPTCHA_GET_EXCEPTION.getCode(), BizEnums.CAPTCHA_GET_EXCEPTION.getMsg());
        }
    }
}

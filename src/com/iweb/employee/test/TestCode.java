package com.iweb.employee.test;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

public class TestCode {

    public static void main(String[] args) {
        //定义图形验证码的长和宽
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha
                (200, 100, 4, 20);

        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/圆圈.png");
    }
}

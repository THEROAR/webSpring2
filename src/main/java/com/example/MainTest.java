package com.example;

import com.example.core.config.IConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class MainTest {

    /**
     * 进行rebase测试
     * @param args
     */
    public static void main(String args[]) throws UnsupportedEncodingException {
        /**Object o = IConfig.class.getResourceAsStream("/spring/config.properties");
        if (o == null) {
            System.out.print("失败");
        }**/
        String strUrlEn = URLEncoder.encode("http://www.baidu.com", "utf-8");
        System.out.println(strUrlEn);
        String strUrlDe = URLDecoder.decode(strUrlEn, "utf-8");
        System.out.println(strUrlDe);

    }
}

package com.example;

import com.example.core.config.IConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainTest {

    /**
     * 进行rebase测试
     * @param args
     */
    public static void main(String args[]) {
        Object o = IConfig.class.getResourceAsStream("/spring/config.properties");
        if (o == null) {
            System.out.print("失败");
        }

    }
}

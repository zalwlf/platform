package com.zalwlf.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import org.junit.jupiter.api.Test;

/**
 * platform
 * <p>lib目录转pom测试</p>
 *
 * @author : lcq
 * @date : 2020-09-08 21:08
 **/
public class MakePomFromJarsTestUnit {
    @Test
    public void JarsToPom() throws IOException {
        MakePomFromJars.generatePom("E:\\ProjectOfAll\\workSpaceForIdea\\bossint_new\\together_dev\\WebContent\\WEB-INF\\lib");
    }
    @Test
    public void JarsInfo() throws IOException {
        java.io.File dir = new java.io.File("E:\\ProjectOfAll\\workSpaceForIdea\\bossint_new\\together_dev\\WebContent\\WEB-INF\\lib");
        for (java.io.File jar : Objects.requireNonNull(dir.listFiles())) {
            JarInputStream jis = new JarInputStream(new FileInputStream(jar));
            Manifest mainManiFest = jis.getManifest();
            jis.close();
            if (mainManiFest != null) {
                Attributes attributes = mainManiFest.getMainAttributes();
                System.out.println("------------------------"+jar.getName()+"------------------------");
                attributes.forEach((key, value) -> System.out.println(key + "||" + value));
            }
        }
    }
}

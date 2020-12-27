package com.zalwlf.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.jsoup.Jsoup;
import com.alibaba.fastjson.JSONObject;

/**
 * together_boot
 * <p>lib转pom</p>
 * @author : lcq
 * @date : 2020-09-08 19:03
 **/
public class MakePomFromJars {
    public static void generatePom(String libFilePath) throws IOException {
        Element dependencies = new DOMElement("dependencies");
        //"E:\\ProjectOfAll\\workSpaceForIdea\\bossint_new\\together_dev\\WebContent\\WEB-INF\\lib"
        //需生成pom.xml 文件的 lib路径
        java.io.File dir = new java.io.File(libFilePath);
        StringBuilder excludedJars = new StringBuilder();
        for (java.io.File jar : Objects.requireNonNull(dir.listFiles())) {
            JarInputStream jis = new JarInputStream(new FileInputStream(jar));
            Manifest mainManiFest = jis.getManifest();
            jis.close();
            if(mainManiFest==null) {
                continue;
            }
            String bundleNameS = mainManiFest.getMainAttributes().getValue("Bundle-Name");
            String bundleVersionS = mainManiFest.getMainAttributes().getValue("Bundle-Version");
            if (bundleNameS == null || bundleNameS.length() == 0){
                excludedJars.append(jar.getName()).append("\t");
                continue;
            }
            if (bundleVersionS == null || bundleVersionS.length() == 0){
                excludedJars.append(jar.getName()).append("\t");
                continue;
            }
            StringBuilder bundleName = new StringBuilder(bundleNameS);
            StringBuilder bundleVersion = new StringBuilder(bundleVersionS);
            StringBuilder sb = new StringBuilder(jar.getName());
            bundleName = new StringBuilder(bundleName.toString().toLowerCase().replace(" ", "-"));
            sb.append(bundleName).append("\t").append(bundleVersion);
            Element ele = getDependencies(bundleName.toString(), bundleVersion.toString());
            if (ele.elements().size() == 0) {
                bundleName = new StringBuilder();
                bundleVersion = new StringBuilder();
                String[] ns = jar.getName().replace(".jar", "").split("-");
                for (String s : ns) {
                    if (Character.isDigit(s.charAt(0))) {
                        bundleVersion.append(s).append("-");
                    } else {
                        bundleName.append(s).append("-");
                    }
                }
                if (bundleVersion.toString().endsWith("-")) {
                    bundleVersion = new StringBuilder(bundleVersion.substring(0, bundleVersion.length() - 1));
                }
                if (bundleName.toString().endsWith("-")) {
                    bundleName = new StringBuilder(bundleName.substring(0, bundleName.length() - 1));
                }
                ele = getDependencies(bundleName.toString(), bundleVersion.toString());
                sb.setLength(0);
                sb.append(bundleName).append("\t").append(bundleVersion);
                System.out.println(ele.asXML());
            }

            ele = getDependencies(bundleName.toString(), bundleVersion.toString());
            if (ele.elements().size() == 0) {
                ele.add(new DOMElement("groupId").addText("not find"));
                ele.add(new DOMElement("artifactId").addText(bundleName.toString()));
                ele.add(new DOMElement("version").addText(bundleVersion.toString()));
            }
            dependencies.add(ele);
            System.out.println();
        }
        System.out.println(dependencies.asXML());
        System.out.println(excludedJars.toString());
    }
    public static Element getDependencies(String key, String ver) {
        Element dependency = new DOMElement("dependency");
        // 设置代理
        try {
            String url = "http://search.maven.org/solrsearch/select?q=a%3A%22" + key + "%22%20AND%20v%3A%22" + ver + "%22&rows=3&wt=json";
            org.jsoup.nodes.Document doc = Jsoup.connect(url).ignoreContentType(true).timeout(30000).get();
            String elem = doc.body().text();
            JSONObject response = JSONObject.parseObject(elem).getJSONObject("response");
            if (response.containsKey("docs") && response.getJSONArray("docs").size() > 0) {
                JSONObject docJson = response.getJSONArray("docs").getJSONObject(0);
                Element groupId = new DOMElement("groupId");
                Element artifactId = new DOMElement("artifactId");
                Element version = new DOMElement("version");
                groupId.addText(docJson.getString("g"));
                artifactId.addText(docJson.getString("a"));
                version.addText(docJson.getString("v"));
                dependency.add(groupId);
                dependency.add(artifactId);
                dependency.add(version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dependency;
    }
}

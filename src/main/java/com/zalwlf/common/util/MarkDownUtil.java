package com.zalwlf.common.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * platform
 * <p>markdown工具</p>
 *
 * @author : lcq
 * @date : 2020-09-28 17:12
 **/
public class MarkDownUtil {

    /**
     * markdown格式文本转html格式文本
     * @param markdownContent markdown格式文本内容{@link String}
     * @return {@link String}
     */
    public static String markdownToHtml(String markdownContent){
        Parser build = Parser.builder().build();
        Node parse = build.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(parse);
    }

    /**
     * 增加扩展[标题锚点，表格生成]
     * Markdown转换成HTML
     * @param markdownContent markdown格式文本内容{@link String}
     * @return {@link String}
     */
    public static String markdownToHtmlExtensions(String markdownContent){
        //h标签生成id
        Set<Extension> titleTagExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //table标签转换
        List<Extension> tableTagExtensions = Collections.singletonList(TablesExtension.create());

        Parser build = Parser.builder().extensions(tableTagExtensions).build();
        Node node = build.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(titleTagExtensions)
                .extensions(tableTagExtensions)
                .attributeProviderFactory(attributeProviderContext -> new CustomAttributeProvider())
                .build();
        return renderer.render(node);
    }

    /**
     * 处理标签的属性
     */
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            //改变a标签的target属性为_blank
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }
    }

}

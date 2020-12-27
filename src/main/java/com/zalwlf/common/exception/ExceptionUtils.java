package com.zalwlf.common.exception;

import com.zalwlf.common.util.Verify;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.LoggerFactory;

/**
 * platform
 * <p>异常工具</p>
 *
 * @author : lcq
 * @date : 2020-09-04 15:42
 **/
public class ExceptionUtils {

    public static String getFullStackTrace(Throwable t){
        return getCustomStackTrace(t,null,null);
    }

    public static String getIncludedStackTrace(Throwable t,String[] packageSelectors){
        return getCustomStackTrace(t,packageSelectors,null);
    }

    public static String getExcludedStackTrace(Throwable t, String[] packageFilters){
        return getCustomStackTrace(t,null,packageFilters);
    }

    public static String getCustomStackTrace(Throwable t,String[] packageSelectors,String[] packageFilters) {
        Set<String> setSelectors = null;
        Set<String> setFilters = null;
        if (Verify.isNotNull(packageSelectors)) {
            setSelectors = new HashSet<>(Arrays.asList(packageSelectors));
        }
        if (Verify.isNotNull(packageFilters)) {
            setFilters = new HashSet<>(Arrays.asList(packageFilters));
        }
        StringBuilder customStackTrace = new StringBuilder(t.toString());
        try(StringWriter sw = new StringWriter();PrintWriter pw = new PrintWriter(sw)) {
            t.printStackTrace(pw);
            String[] stackTraceLines = sw.toString().split("\r\n\t");
            line: for (int i = 1; i < stackTraceLines.length; i++) {
                if (setFilters != null ){
                    for (String filter : setFilters) {
                        if (stackTraceLines[i].startsWith("at "+filter)){
                            continue line;
                        }
                    }
                }
                if (setSelectors != null ){
                    for (String selector : setSelectors) {
                        if (stackTraceLines[i].startsWith("at "+selector)){
                            customStackTrace.append("\r\n\t").append(stackTraceLines[i]);
                            continue line;
                        }
                    }
                }else {
                    customStackTrace.append("\r\n\t").append(stackTraceLines[i]);
                }
            }
        } catch (Exception e){
            LoggerFactory.getLogger(ExceptionUtils.class).error("Exception stack read failed:"+t.getMessage());
        }
        return customStackTrace.toString();
    }
}

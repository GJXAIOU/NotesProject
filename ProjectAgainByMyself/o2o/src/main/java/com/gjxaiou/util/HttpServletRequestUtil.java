package com.gjxaiou.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GJXAIOU
 * @create 2019-10-18-18:24
 */
public class HttpServletRequestUtil {
    // 因为 request 中有很多的 key，可以根据 key 取出对应的 value，然后将其值转换为我们需要的类型
    public static int getInt(HttpServletRequest request, String key){
        // 将 key 对应的 value 值转换为 integer类型，如果失败返回 -1；
        try {
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key){
        // 将 key 对应的 value 值转换为 integer类型，如果失败返回 -1；
        try {
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key){
        // 将 key 对应的 value 值转换为 integer类型，如果失败返回 -1；
        try {
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key){
        // 将 key 对应的 value 值转换为 integer类型，如果失败返回 -1；
        try {
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key){
        // 将 key 对应的 value 值转换为 integer类型，如果失败返回 -1；
        try {
           String  result = request.getParameter(key);
           if (result != null){
               result = result.trim();
           }
           if ("".equals(result)){
               result = null;
           }
            return result;
        }catch (Exception e){
            return null;
        }
    }
}

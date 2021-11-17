package com.small.business.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


public class BlankUtil {
    public BlankUtil() {
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static boolean isBlank(Character cha) {
        return cha == null || cha.equals(' ');
    }

    public static boolean isBlank(Object obj) {
        return obj == null;
    }

    public static boolean isBlank(Object[] objs) {
        return objs == null || objs.length <= 0;
    }

    public static boolean isBlank(Collection<?> obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isBlank(Set<?> obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isBlank(Serializable obj) {
        return obj == null;
    }

    public static boolean isBlank(Map<?, ?> obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return !pattern.matcher(str).matches();
    }
}
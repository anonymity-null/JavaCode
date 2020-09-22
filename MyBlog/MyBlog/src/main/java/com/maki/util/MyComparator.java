package com.maki.util;

import java.util.Comparator;

/*
* 自定义排序
* */
public class MyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {
        int i = str1.compareTo(str2);
        return -i;
    }
}

package com.mjxc.sudokuc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：xk on 2018/3/10
 * 版本：v1.0
 * 描述：
 */

public class Test {
    public static void  main(String[] arg){
       String str = replaceBlank(sString);
//        System.out.print(str.replace(" ",""));
        System.out.print(str);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {

            Pattern p = Pattern.compile("\\s*|\t|\r|\n");

            Matcher m = p.matcher(str);

            dest = m.replaceAll("");
        }
        return dest;
    }
    static String sString = "8\t7\t2\t1\t9\t3\t4\t6\t5\n" +
            "6\t5\t1\t2\t4\t8\t3\t7\t9\n" +
            "3\t4\t9\t6\t5\t7\t2\t8\t1\n" +
            "5\t2\t6\t3\t1\t4\t7\t9\t8\n" +
            "4\t8\t7\t9\t5\t6\t1\t3\t2\n" +
            "9\t1\t3\t7\t8\t2\t6\t5\t4\n" +
            "2\t9\t8\t4\t7\t6\t5\t1\t3\n" +
            "7\t3\t5\t8\t2\t1\t9\t4\t6\n" +
            "1\t6\t4\t5\t3\t9\t8\t2\t7";
}

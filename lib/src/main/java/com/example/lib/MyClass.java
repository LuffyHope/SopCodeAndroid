package com.example.lib;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {
    public static void main(String[] args) {

        //System.out.print(isNumeric("1.23"));
/*
        String path = "D:\\release";        //要遍历的路径
        File file = new File(path);        //获取其file对象
        func(file);
*/

        Pattern p=Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher m=p.matcher("0.");
        m.matches();//返回false,因为bb不能被\d+匹配,导致整个字符串匹配未成功.
        //System.out.println(m.matches());

                System.out.println(Double.valueOf("1.00"));
                System.out.println(String.valueOf(Double.valueOf("1.00")));
    }

    public static boolean isNumeric(String str) {
        String replace = str.replace(".", "");
        for (int i = replace.length(); --i >= 0; ) {
            if (!Character.isDigit(replace.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String formatMoney(String money) {
        DecimalFormat df2 = new DecimalFormat("#.00");
        return df2.format(Double.valueOf(money));
    }


    private static void func(File file) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isDirectory())    //若是目录，则递归打印该目录下的文件
                func(f);
            if (f.isFile()) {
                //若是文件，直接打印
                //System.out.println(f);
                copy(f.getAbsolutePath());
            }
        }
    }

    private static void copy(String old) {
        String newPath = "D:\\nativeapp\\android";
        File file = new File(newPath);
        if (file.exists() && file.isDirectory()) {
            int start = old.indexOf("o");
            int end = old.indexOf(".");
            String substring = old.substring(start, end);
            System.out.println(substring);
            new File(newPath + "\\" + substring).mkdir();
            File file1 = new File(newPath + "\\" + substring + "\\" + substring + ".apk");
            try {
                copyFileUsingFileStreams(new File(old), file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.mkdir();
        }
    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }
}

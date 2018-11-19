package com.jim.chapter3;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: Jim
 * Date: 2018/11/15:下午3:54
 * Description: Sting是一个final类，不可复写。构造好的字符串是不可变字符串。
 */

@Slf4j
public class StringsAndThings {

    /**
     * 想要获取一个字符串的一部分？
     * 方法：使用String的subString方法，你就可以得到一个新的字符串对象
     * 注意：indexStart,indexEnd，表示的是子字符串从（0-base）第几个字符开始，到第几个字符结束
     * 而且是半开区间（包含开始，不包含结束），所以如果你要取得第7，第8位置的两个字符的字符串，第二个参数
     * 应该是9
     */
    @Test
    public void testSubString() {
        String origin = "Hello World!";
        String sub1 = origin.substring(6);
        Assert.assertTrue(sub1.length() == 6);
        Assert.assertTrue(sub1.equals("World!"));

        String sub2 = origin.substring(7, 9);
        log.debug(sub2);
        Assert.assertTrue(sub2.equals("or"));
    }

    /**
     * 问题：将字符串解析成单词
     * 方法：使用空格分隔符循环或者使用StringTokenizer+分隔符或者使用正则分隔符
     */
    @Test
    public void testStringToWords() {
        String origin = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas modi dicta cumque veritatis, doloremque tempore natus ad veniam necessitatibus consequuntur quos, numquam corrupti dolore deleniti odit amet ex ab illo!";
        List<String> words = new ArrayList<>();
        for (String word : origin.split("\\s+")) {
            words.add(word);
            log.debug(word);
        }

        StringTokenizer stringTokenizer = new StringTokenizer(origin);
        words.clear();
        while (stringTokenizer.hasMoreElements()) {
            log.debug(stringTokenizer.nextToken());
            words.add(stringTokenizer.nextElement().toString());
        }
    }

    @Test
    public void testStringToWords2() {
        String origin = "Jim||Abraham||16601129301";
        List<String> words = new ArrayList<>();

        StringTokenizer stringTokenizer = new StringTokenizer(origin, "|", true);
        while (stringTokenizer.hasMoreElements()) {
            words.add(stringTokenizer.nextElement().toString());
        }

        log.debug(new Gson().toJson(words));
    }

    @Test
    public void testRegExpSplitStringToWords() {
        String money = "471,472,570";
        Matcher matcher = Pattern.compile("(\\d+)[^\\d]*(\\d+)[^\\d]*(\\d+)").matcher(money);
        if (matcher.find()) {
            int count = matcher.groupCount();
            log.debug(String.valueOf(count));
            for (int i = 0; i < count; i++) {
                log.debug(matcher.group(i));
            }
        }
    }

    /**
     * 问题：需要把字符串连起来形成一个新的字符串
     * 方法1：使用"+"
     * 编译器将会隐式的调用StringBuilder，使用append方法连接各个字符串
     * <p>
     * 方法2：使用StringBuilder
     *
     * StringBuilder每次都会返回原来的对象，可以使用流式API
     */
    @Test
    public void putPiecesToString() {
        //使用"+"连接字符串
        String s1 = "Hello" + ", " + "World!";
        log.debug(s1);

        //使用StringBuilder.append()
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Hello")
                .append(",")
                .append(" ")
                .append("World!");

        log.debug(stringBuilder.toString());

        Assert.assertTrue(s1.equals(stringBuilder.toString()));
        Assert.assertFalse(s1 == stringBuilder.toString());

        stringBuilder.deleteCharAt(0);
        Assert.assertTrue(stringBuilder.toString().indexOf("e") == 0);

        stringBuilder.delete(0,6);
        Assert.assertTrue(stringBuilder.toString().equals("World!"));
        Assert.assertFalse(stringBuilder.toString() == "World!");
        stringBuilder.insert(0, "Hello,");
        Assert.assertTrue(stringBuilder.toString().equals("Hello,World!"));
        stringBuilder.replace(0, 1, "h");
        Assert.assertTrue(stringBuilder.toString().equals("hello,World!"));
        stringBuilder.reverse();
        Assert.assertTrue(stringBuilder.toString().equals("!dlroW,olleh"));
    }

    /**
     * 字符串数组，转逗号分割的字符串
     */
    @Test
    public void listToCommaString() {
        List<String> words = new ArrayList<>(Arrays.asList("Finland", "Russia", "Latvia", "Lithuania", "Poland"));
        StringBuilder stringBuilder = new StringBuilder();
        for (String word: words) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(word);
        }

        log.debug(stringBuilder.toString());
    }

    /**
     * 逗号分割的字符串转List和反转List
     */
    @Test
    public void stringToList() {
        String str = "Finland,Russia,Latvia,Lithuania,Poland";
        List<String> words = new ArrayList<>(Arrays.asList(str.split(",")));
        log.debug(words.toString());
        Collections.reverse(words);
        log.debug(words.toString());
    }

    /**
     * 问题：给定一个字符串，一次操作只处理字符串中的一个字符
     * 方法：循环字符串，用String.charAt(int index)处理每个字符
     */

    @Test
    public void processCharOfString() {
        String name = "Abraham Jim";
        for (int i = 0; i < name.length(); i++) {
            log.debug(String.valueOf((int)name.charAt(i)));
        }

        for (char c : name.toCharArray()) {
            log.debug(String.valueOf(c));
        }
    }

    /**
     * checksum
     * 一个英文文本文件，读取所有的内容，然后将每个字符的转为
     */
}

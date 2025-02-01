package com.duckfox.duckpokemonbingo.gui;

import java.util.ArrayList;
import java.util.List;

public class Pair<T, U> {
    private T first;
    private U second;

    // 构造函数
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // 获取第一个值
    public T getFirst() {
        return first;
    }

    // 获取第二个值
    public U getSecond() {
        return second;
    }

    // 重写toString方法，方便输出
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

//    // 测试代码
//    public static void main(String[] args) {
//        // 创建一个List来存储多个Pair
//        List<Pair<String, Integer>> pairList = new ArrayList<>();
//
//        // 向List中添加不同的Pair
//        pairList.add(new Pair<>("Apple", 10));
//        pairList.add(new Pair<>("Banana", 20));
//        pairList.add(new Pair<>("Orange", 30));
//
//        // 遍历List并打印每一对值
//        for (Pair<String, Integer> pair : pairList) {
//            System.out.println("First: " + pair.getFirst() + ", Second: " + pair.getSecond());
//        }
//    }
}

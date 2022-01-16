package com.dreamfarm.farmmain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Test {

    // String 可以为任意类型 也可以自定义类型
    static Map<String, Integer> keyChanceMap = new HashMap<String, Integer>();
    static {
        keyChanceMap.put("aaa", 500);
        keyChanceMap.put("bbb", 1500);
        keyChanceMap.put("ccc", 2000);
        keyChanceMap.put("ddd", 3000);
        keyChanceMap.put("eee", 3000);
    }

    public static void main(String[] args) {
        Map<String, Integer> count = new HashMap<String, Integer>();
        List<String> list = new ArrayList<>();
        String item = null;
        for (int i = 0; i < 10000; i++) {
            item = chanceSelect(keyChanceMap);
            list.add(item);

            if (count.containsKey(item)) {
                count.put(item, count.get(item) + 1);
            } else {
                count.put(item, 1);
            }

        }
        for (String id : count.keySet()) {
            System.out.println(id + "\t出现了 " + count.get(id) + " 次");
        }

        Random rand = new Random();
        int num = rand.nextInt(10000);
        System.out.print("最终选择的随机数为："+list.get(num));
    }

    public static String chanceSelect(Map<String, Integer> keyChanceMap) {
        if (keyChanceMap == null || keyChanceMap.size() == 0)
            return null;

        Integer sum = 0;
        for (Integer value : keyChanceMap.values()) {
            sum += value;
        }
        // 从1开始
        Integer rand = new Random().nextInt(sum) + 1;

        for (Map.Entry<String, Integer> entry : keyChanceMap.entrySet()) {
            rand -= entry.getValue();
            // 选中
            if (rand <= 0) {
                String item = entry.getKey();
                return item;
            }
        }
        return null;
    }
}
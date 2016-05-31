package com.company.liao.test;

import java.util.*;

/**给定数组和K，找出出现次数排前K名的元素
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * Created by liaoqianwen on 2016/5/31.
 */
public class TopKFrequentElements {

    public static void main(String args[]) {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] arr = {1,2,2,2,3,4,4,5,2,6,8};
        List<Integer> result = test.topKFrequent(arr,2);
        System.out.println(result);

    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        if(nums == null)
            return null;

        Map<Integer,Integer> map = new HashMap<>();  // key为数组元素，value为该元素在数组中出现的次数
        List<Integer> result = new LinkedList<>();

        for(int i: nums) {
            if(map.get(i) != null){  // 在map中找到了对应的元素
                int j = map.get(i);
                map.put(i, ++j);
            }else {                  // 在map中还未记录该元素
                map.put(i,1);
            }
        }

        List<Map.Entry<Integer,Integer>> listMap = new ArrayList<>();
        listMap.addAll( map.entrySet());  // 将Map<Integer,Integer>转换为List<Map.Entry<Integer,Integer>>

        Collections.sort(listMap, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());  // 将listMap队列进行降序排列
            }
        });

        for(int i = 0 ;i<k; i++){
            result.add(listMap.get(i).getKey());
        }

        return result;

    }

}

package com.example.user.kotlinlearning;

public class UnitTestWithJava {

    private int[] twoSum(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            int currentElement = arr[i];
            for (int j = 0; j < arr.length; j++) {
                if (i == j) continue;
                if (currentElement + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0};
    }
}

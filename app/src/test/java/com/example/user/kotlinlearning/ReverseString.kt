package com.example.user.kotlinlearning

import org.junit.Test

class ReverseString {

    /**
     *  Перевернуть строку («мама мыла раму» -> «раму мыла мама»)
     * */
    @Test
    fun reverseString() {

        val original = "мама очень долго мыла раму"

        val list = original.split(" ").toTypedArray()

        var reversed = StringBuilder("")

        for (i in list.size - 1 downTo 0) {
            reversed.append(list[i])
            if (i == 0) continue
            reversed.append(" ")
        }

        println("GETZ.ExampleUnitTest.reverseString ---> original=<<<$original>>>")
        println("GETZ.ExampleUnitTest.reverseString ---> reversed=<<<$reversed>>>")
    }
}
package com.example.user.kotlinlearning

import org.junit.Assert.assertEquals
import org.junit.Test

data class Mem(val name: String, val age: Int)

class ExampleUnitTest {

    private val digits = listOf(1, 2, 3, 4)
    private val memes = listOf(
            Mem("Garold", 73),
            Mem("Doge", 15),
            Mem("GabeTheDog", 12))

    @Test
    fun testFilterOperation() {
        println("GETZ.ExampleUnitTest.testFilter ---> ${digits.filter { it % 2 == 0 }}")
        println("GETZ.ExampleUnitTest.testFilter ---> ${memes.filter { it.age >= 30 }}")
        assertEquals(1, 1)
    }

    @Test
    fun testMapOperation() {
        println("GETZ.ExampleUnitTest.testMap ---> ${digits.map { it * it }}")
        println("GETZ.ExampleUnitTest.testMap ---> ${memes.map { it.name }}")
        //After refactoring (using ref to class member)
        println("GETZ.ExampleUnitTest.testMap ---> ${memes.map { Mem::name }}")
        //Filter and Map can be grouped.
        println("GETZ.ExampleUnitTest.testMap ---> ${memes.filter { it.age > 20 }.map { it.age }}")
        assertEquals(1, 1)
    }

    @Test
    fun testMapAndFilterForMapDataStructure() {
        val numbers = mapOf(0 to "zero", 1 to "one", 2 to "two", 3 to "three")
        println("GETZ.ExampleUnitTest.testMapAndFilterForMapDataStructure ---> " +
                "${numbers.mapValues { it.value.toUpperCase() }}")
        println("GETZ.ExampleUnitTest.testMapAndFilterForMapDataStructure ---> " +
                "${numbers.filterKeys { it >= 2 }}}")
    }

    @Test
    fun testAllAnyCountFind() {
        //"all", "any", "count" and "find" are checking each element of collection and
        // return their options if predicate and condition are equal.
        val canBeClub27 = { m: Mem -> m.age <= 27 }

        //If all elements satisfy the predicate
        println("GETZ.ExampleUnitTest.testAllAnyCountFind ---> ${memes.all(canBeClub27)}")

        //If at least one element satisfies the predicate
        println("GETZ.ExampleUnitTest.testAllAnyCountFind ---> ${memes.any(canBeClub27)}")

        //If you want to know how many elements satisfy the predicate
        println("GETZ.ExampleUnitTest.testAllAnyCountFind ---> ${memes.count(canBeClub27)}")
        //"count" does't create new collection, so it is effective then
        println("GETZ.ExampleUnitTest.testAllAnyCountFind ---> ${memes.filter(canBeClub27).size}")

        //If you want to get fist element which satisfies the predicate
        println("GETZ.ExampleUnitTest.testAllAnyCountFind ---> ${memes.find(canBeClub27)}")
    }

    @Test
    fun tramParamINetYego(){
        //построить из этого строку аж до миллиарда
        /*

            первую

            из первой во вторую
            из второй в третью
            из третьей в четвертую
            из четвертой в пятую
            из пятой в шестую
            из шестой в седьмую
            из восьмой в девятую
            из девятой в десятую
            из десятой в одиннадцатую

            из одиннадцатой в двенадцатую
            из двенадцатой в тринадцатую
            из тринадцатой в четырнадцатую
            из четырнадцатой в пятнадцатую
            из пятнадцатой в шестнадцатую
            из шестнадцатой в семнадцатую
            из семнадцатой в восемьнадцатую
            из восемьнадцатой и девятнадцатую
            из девятнадцатой в двадцатую

            из двадцатой в двадцать первую
            из двацать первой в двадцать вторую
            из двадцать второй в двадцать третью
            из двадцать третьей в двадцать четвертую

        */

        val какую = arrayListOf(
                "первую",
                "вторую",
                "третью",
                "четвертую",
                "пятую",
                "шестую",
                "седьмую",
                "восьмую",
                "девятую")

        val какой = arrayListOf(
                "первой",
                "второй",
                "третьей",
                "четвертой",
                "пятой",
                "шестой",
                "седьмой",
                "восьмой",
                "девятой")

        val десяткиКакую = arrayListOf(
                "десять",
                "двадцать",
                "тридцать",
                "сорок",
                "пятдесят",
                "шистдесят",
                "семдесят",
                "восемьдесят",
                "девяносто")

        val сотки = arrayListOf(
                "сто",
                "двесте",
                "триста",
                "четыреста",
                "пятьсот",
                "шестьсот",
                "семьсот",
                "восемьсот",
                "девятсот"
        )

        val тысячи = arrayListOf(
                "одна тысяча",
                "две тысячи",
                "три тысячи",
                "четыре тысячи",
                "пять тысяч",
                "шесть тысяч",
                "семь тысяч",
                "восемь тысяч",
                "девять тысяч"
        )

        //ya zaebalsja, slishkom dolgo...
    }
}
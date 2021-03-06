package com.example.user.kotlinlearning

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

data class Mem(val name: String, val age: Int)


//todo Reorder all methods. Perhaps, it will move to diff classes...

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

    /**
     *  5.4 Using functional interfaces of Java
     *
     *  SAM - Single Abstract Method interface (interface with one method)
     * */

    @Test
    fun testLambda() {
        //each time this method will create new instance of lambda
        callUsingObject() //$1@6f2b958e
        callUsingObject() //$1@1eb44e46

        //lambda instance was the same, it reused.
        callLambdaAsArgument() //$1@6504e3b2
        callLambdaAsArgument() //$1@6504e3b2

        //lambda with one parameter creates new instance each call
        callLambdaWithParameter(1234) //$1@515f550a
        callLambdaWithParameter(1234) //$1@626b2d4a
    }

    private fun callLambdaAsArgument() {
        val lambda = Runnable {
            println("GETZ.ExampleUnitTest.testLambda ---> callLambdaAsArgument")
        }
        println("GETZ.ExampleUnitTest.callLambdaAsArgument ---> lambda=$lambda")
        postponeComputation(1000, lambda)
    }

    private fun callLambdaWithParameter(number: Int) {
        val lambda = Runnable {
            println("GETZ.ExampleUnitTest.testLambda ---> callLambdaWithParameter number=$number")
        }
        println("GETZ.ExampleUnitTest.callLambdaWithParameter ---> lambda=$lambda")
        postponeComputation(1000, lambda)
    }

    private fun callUsingObject() {
        val l = object : Runnable {
            override fun run() {
                println("GETZ.ExampleUnitTest.run ---> ")
            }
        }
        println("GETZ.ExampleUnitTest.callUsingObject ---> l=$l")
        postponeComputation(1000, l)
    }

    private fun postponeComputation(int: Int, runnable: Runnable) {
        println("GETZ.ExampleUnitTest.postponeComputation ---> int=$int")
    }

    /**
     * 5.4.2 SAM-constructors
     *
     * There is no way to get "this" link from lambda.
     * Lambda is not an object. We can't interact with lambda like with object.
     * From compiler point of view a lambda is a piece of code and that's all.
     * */
    @Test
    fun theSAMConstructors() {
        createAllDoneRunnable().run()
        createAllDoneRunnableAnonymous().run()


    }

    fun createAllDoneRunnable(): Runnable {
        return Runnable { println("GETZ.ExampleUnitTest.createAllDoneRunnable ---> this is runnable") }
    }

    fun createAllDoneRunnableAnonymous(): Runnable {
        return object : Runnable {
            override fun run() {
                println("GETZ.ExampleUnitTest.run ---> ")
            }
        }
    }


    /**
     * 5.5 Lambda with consumers: "with" and "apply" functions.
     *
     * "With" helps us to execute some operations using the same object
     * without repeating yourself.
     * */
    @Test
    fun lambdaWithConsumers() {
        val result = alphabet()
        println("GETZ.ExampleUnitTest.lambdaWithConsumers ---> alphabet=$result")
        val resultImproved = alphabetUsingWithFunc()
        println("GETZ.ExampleUnitTest.lambdaWithConsumers ---> alphabetImproved=$resultImproved")
    }

    fun alphabet(): String {
        val sb = StringBuilder()
        return with(sb) {
            for (letter in 'A'..'Z') {
                this.append(letter)
            }
            append("\nNow I know the alphabet!!")
            this.toString()
        }
    }

    /**
     * Inside "with" I can skip key word "this".
     * */
    fun alphabetUsingWithFunc() = with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet!!")
        toString()
    }

    /**
     * 5.5.2 Function "apply"
     *
     * The "apply" func always returns object that it gets before.
     * */
    @Test
    fun testApplyFunction() {
        val result = alphabetUsingApplyFunc()
        println("GETZ.ExampleUnitTest.testApplyFunction ---> result=$result")
    }

    fun alphabetUsingApplyFunc() = StringBuilder().apply {
        for (l in 'A'..'Z') {
            append(l)
        }
        append("\nNow I've known the alphabet!11")
    }.toString()


    /**
     * 8.1 High order function.
     * High order function it's function which gets as an argument another function or lambda.
     * For example: list.filer{ x>0 }
     *
     * 8.1.1 Functions types.
     * */
    fun typesOfFunctions() {
        val sum = { x: Int, y: Int ->
            println("GETZ.ExampleUnitTest.typesOfFunctions ---> last line is returning statement")
            x + y
        }
        val action = { println("GETZ.ExampleUnitTest.typesOfFunctions ---> ") }
        var canReturnNull: (Int, Int) -> Int? = { _, _ -> null }
        var funOrNull: ((Int, Int) -> Int)? = null //var can be null (nullable)
        performRequest("https://hatersgonnahate.com") { code, content ->
            println("GETZ.ExampleUnitTest.typesOfFunctions ---> piece of code")
        }

    }

    fun performRequest(url: String, callback: (code: Int, content: String) -> Unit) {
        callback.invoke(12, "dsf")
    }



    /**
     * Try to cast
     * */

    @Test
    fun castingObj() {

        val arrayOfHashMaps = arrayListOf<List<String>>()
                .also {
                    val photoInfo = arrayListOf<String>()
//                    photoInfo["name"] = 1234
//                    photoInfo["surname"] = 2345
                    photoInfo.add("ASD")
                    photoInfo.add("SDF")
                    photoInfo.add("FGH")
                    it.add(photoInfo)
                }

        val examples = hashMapOf<Any?, Any?>(Pair("first", arrayOfHashMaps))
        val unknownType = examples["first"]

        if (unknownType is List<*>) {
//            val castedList = unknownType as List<HashMap<String, String>>
//            println("GETZ.ExampleUnitTest.castingObj ---> casted! key=${castedList[0]["name"]}  unknownType=$castedList")

            val castedList = unknownType.asListOfType<List<List<Int>>>()
            println("GETZ.ExampleUnitTest.castingObj ---> casted! castedList=$castedList")

        } else {
            println("GETZ.ExampleUnitTest.castingObj ---> not casted( ")
        }

        Assert.assertEquals(1, 1)
    }

    @Test
    fun startsWithTest() {
        val str = "asdf"
        val a = str.let {
            return@let it + "qwer"
        }

        val str1 = "asdf11111"

        val asdf = str1.also {
            return@also
        }
        println("GETZ.ExampleUnitTest.startsWithTest ---> str=$str a=$a")
        println("GETZ.ExampleUnitTest.startsWithTest ---> str=$str1 a=$a")
    }


//    Given nums = [2, 7, 11, 15], target = 9,
//    Because nums[0] + nums[1] = 2 + 7 = 9,
//    return [0, 1].

    @Test
    fun strangeSum() {
        val nums = arrayOf(2, 7, 11, 15)
        val target = 9

        val result: Array<Int> = twoSum(nums, target)
    }

    private fun twoSum(arr: Array<Int>, target: Int): Array<Int> {
        for (i in arr.indices) {
            println("GETZ.ExampleUnitTest.twoSum ---> i=$i")
            val currentElement = arr[i]
            for (j in arr.indices) {
                if (i == j) continue
                if (currentElement + arr[j] == target) {
                    println("GETZ.ExampleUnitTest.twoSum ---> i=$i j=$j")
                    return arrayOf(i, j)
                }
            }
        }
        return arrayOf(0)
    }


    @Test
    fun howToReturnRelevantViewType() {

        val massages = arrayListOf<Msg>(
                Msg("hi", SimpleDateFormat("dd-MM-yyyy").parse("01-12-2018")),
                Msg("how are you", SimpleDateFormat("dd-MM-yyyy").parse("01-12-2018")),
                Msg("wtf??", SimpleDateFormat("dd-MM-yyyy").parse("01-12-2018")),
                Msg("you are pulling my let)))", SimpleDateFormat("dd-MM-yyyy").parse("02-12-2018")),
                Msg("no way!!", SimpleDateFormat("dd-MM-yyyy").parse("03-12-2018"))

        )

        val results = arrayListOf<CommonMsg>()

        //todo add date class to the hole list of msg
        //todo filter it base on classes.

        if (massages.isNotEmpty()) {

            results.add(Dt(massages[0].date))

            var firstElementToCompare: Msg? = null
            massages.forEachIndexed { index, msg ->
                firstElementToCompare = msg
                val currentMsg = msg
                var nextMsg: Msg? = null
                val iterator = massages.iterator()
                if (iterator.hasNext()) {
                    nextMsg = iterator.next()
                }
                if (nextMsg != null) {
                    if (currentMsg.date == nextMsg.date) {
                        results.add(currentMsg)

                        return@forEachIndexed
                    } else {

                    }
                } else {
                    println("GETZ.ExampleUnitTest.howToReturnRelevantViewType ---> next element is null, oops")
                }
            }
        }

    }


    @Test
    fun intersectionOfTwoLists() {

        val listOne = listOf<Int>(1, 2, 3, 4, 5, 6, 7)
        val listTwo = listOf<Int>(6, 7, 8, 8, 8, 8)

        val intersect = listOne.intersect(listTwo.asIterable())

        val result = listOne.sameContentWith(listTwo)
        println("GETZ.ExampleUnitTest.intersectionOfTwoLists ---> result=$result intersect=$intersect")

    }
}


data class Msg(val msg: String, val date: Date) : CommonMsg {
    override fun getMsgDate(): Date {
        return date
    }
}

data class Dt(val date: Date) : CommonMsg {
    override fun getMsgDate(): Date {
        return date
    }
}

interface CommonMsg {
    fun getMsgDate(): Date
}

infix fun <T> Collection<T>.sameContentWith(collection: Collection<T>?) =
        collection?.let {

            this.intersect(collection.asIterable()).isNotEmpty()

        }

inline fun <reified T> List<*>.asListOfType(): List<T>? =
        if (all {
                    println("GETZ.<top>.asListOfType ---> it=$it")
                    it is T
                }) {
            println("GETZ.<top>.asListOfType ---> this=$this")
            @Suppress("UNCHECKED_CAST")
            this as List<T>
        } else
            null
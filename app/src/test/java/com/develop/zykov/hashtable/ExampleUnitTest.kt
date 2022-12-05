package com.develop.zykov.hashtable

import com.develop.zykov.hashtable.hashtable.HashTable
import com.develop.zykov.hashtable.hashtable.IUserType
import com.develop.zykov.hashtable.hashtable.entities.MyInteger
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val table: HashTable<MyInteger> = HashTable()

        for(i in 1..1000) table.add(MyInteger(i))

        println(table.showFillingUniformity())
    }
}
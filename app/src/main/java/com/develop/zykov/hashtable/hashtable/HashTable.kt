package com.develop.zykov.hashtable.hashtable

import java.util.*

class HashTable<V : IUserType> {

    data class Entry<V>(val key: String, val value: V)

    private var arraySize = 8
    private var numberOfEntries = 0

    private var entries: Array<LinkedList<Entry<V>>> =
        Array(arraySize) { LinkedList<Entry<V>>() }

    fun add(value: V) {
        numberOfEntries++
        if (numberOfEntries > arraySize * 0.8) {
            increaseCapacity()
        }
        add(value.key, value, entries)
    }

    private fun add(key: String, value: V, localEntries: Array<LinkedList<Entry<V>>>) {
        val index = calculateHashCode(key)
        val listAtArraySlot = localEntries[index]

        val newEntry = Entry(key, value)

        // Check if the key already exists in the LinkedList entries
        val indexOfEntryInList = listAtArraySlot.indexOfFirst { it.key == key }

        if (indexOfEntryInList >= 0) {
            listAtArraySlot[indexOfEntryInList] = newEntry
        } else {
            listAtArraySlot.offer(newEntry)
        }
    }

    fun get(key: String): V? = entries[calculateHashCode(key)].find { it.key == key }?.value

    fun remove(key: String) {
        val index = calculateHashCode(key)
        entries[index].clear()
    }

    private fun increaseCapacity() {
        arraySize *= 2

        val localEntries: Array<LinkedList<Entry<V>>> =
            Array(arraySize) { LinkedList<Entry<V>>() }

        numberOfEntries = 0

        entries.forEach {
            it.forEach { entry ->
                add(entry.key, entry.value, localEntries)
            }
        }
        entries = localEntries
    }

    fun getAll(): List<IUserType>? {
        val res: MutableList<IUserType> = mutableListOf()
        entries.forEach {
            it.forEach { entry ->
                res.add(entry.value)
            }
        }
        return res
    }

    fun showFillingUniformity(): String {
        var res = ""
        var index = 0
        var filling: MutableList<Int> = mutableListOf()
        var fillingIndex = 0
        entries.forEach {
            res += "{$index}\t\t"
            it.forEach { entry ->
                fillingIndex++
                res += "(key=" + entry.key + " val=" +
                        entry.value.toString() + ") -> "
            }
            res += "\n"
            filling.add(fillingIndex)
            fillingIndex = 0
            index++
        }
        return res + med(filling)
    }

    private fun med(list: MutableList<Int>): Double {
        var res = 0.0
        list.forEach { res += it }
        return res / list.size
    }

    override fun toString(): String {
        val sb = StringBuilder()
        entries.forEach {
            if (it.isNotEmpty())
                sb.append(it).append('\n')
        }
        return sb.toString()
    }

    private fun calculateHashCode(key: String): Int = key.hashCode() % arraySize
}
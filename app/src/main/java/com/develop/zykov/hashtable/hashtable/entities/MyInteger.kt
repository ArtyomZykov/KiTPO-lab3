package com.develop.zykov.hashtable.hashtable.entities

import com.develop.zykov.hashtable.hashtable.IUserType
import org.json.JSONException
import org.json.JSONObject


class MyInteger(val integer: Int = 0) : IUserType {


    override val key: String
        get() = integer.toString()

    override fun toString(): String {
        return "integer - $integer"
    }

    override fun copy(): IUserType {
        return MyInteger(integer)
    }

    override val className: String
        get() = this.javaClass.name

    override fun parseValue(json: JSONObject?): IUserType? {
        return try {
            MyInteger(json?.get("integer") as Int)
        } catch (e: JSONException) {
            null
        }
    }

    override fun packValue(): String {
        return "{\"integer\":" + integer.toString() + "}"
    }
}
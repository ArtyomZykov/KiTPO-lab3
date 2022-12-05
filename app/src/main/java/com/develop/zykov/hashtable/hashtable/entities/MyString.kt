package com.develop.zykov.hashtable.hashtable.entities

import com.develop.zykov.hashtable.hashtable.IUserType
import org.json.JSONException
import org.json.JSONObject


class MyString(val data: String = "") : IUserType {

    override val key: String
        get() = data

    override fun toString(): String {
        return "str - $key"
    }

    override fun copy(): IUserType {
        return MyString(key)
    }

    override val className: String
        get() = this.javaClass.name

    override fun parseValue(json: JSONObject?): IUserType? {
        return try {
            MyString(json?.get("string") as String)
        } catch (e: JSONException) {
            null
        }
    }

    override fun packValue(): String {
        return "{\"string\":\"$key\"}"
    }
}
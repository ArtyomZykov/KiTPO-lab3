package com.develop.zykov.hashtable.hashtable.entities

import com.develop.zykov.hashtable.hashtable.IUserType
import org.json.JSONException
import org.json.JSONObject

class Smartphone(val diagonal: Double = 0.0, val fiveG: Boolean = false) : IUserType {

    override val key: String
        get() = diagonal.toString()

    override fun toString(): String {
        return "diagonal - $diagonal fiveG - $fiveG"
    }

    override fun copy(): IUserType {
        return Smartphone(diagonal, fiveG)
    }

    override val className: String
        get() = this.javaClass.name

    override fun parseValue(json: JSONObject?): IUserType? {
        return try {
            Smartphone(
                json?.get("diagonal") as Double,
                json["five_g"] as Boolean
            )
        } catch (e: JSONException) {
            null
        }
    }

    override fun packValue(): String {
        return ("{\"diagonal\":" + diagonal.toString()
                + ",\"five_g\":" + fiveG + "}")
    }
}
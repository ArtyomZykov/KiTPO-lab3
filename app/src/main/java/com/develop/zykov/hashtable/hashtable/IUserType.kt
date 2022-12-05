package com.develop.zykov.hashtable.hashtable

import org.json.JSONObject


interface IUserType {
    fun copy(): IUserType?
    val className: String?
    val key: String

    // Парсинг значения из Json в объект
    fun parseValue(json: JSONObject?): IUserType?
    fun packValue(): String?
}
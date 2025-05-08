package com.example.test.utills

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson


object PreferenceManager {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("my_app", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun getSharedPreferences(): SharedPreferences = sharedPreferences

    fun getEditor(): SharedPreferences.Editor = editor

    fun clearPreferences() {
        editor.clear().apply()
    }

    fun removePref(keyToRemove: String) {
        sharedPreferences.edit() { remove(keyToRemove) }
    }

    fun setKeyValueString(key: String?, value: String?) {
        editor.putString(key, value).apply()
    }

    fun getKeyValueString(key: String?): String {
        return sharedPreferences.getString(key, "").toString()
    }

    fun setObject(key: String, obj: Any) {
        val json = Gson().toJson(obj)
        editor.putString(key, json).apply()
    }

    inline fun <reified T> SharedPreferences.getObject(key: String): T? {
        return try {
            val json = getString(key, null) ?: return null
            Gson().fromJson(json, T::class.java) // Works with `reified T`
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}

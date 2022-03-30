package com.byjusexamprep.hackathon.data.local

import android.content.Context
import android.content.SharedPreferences
import com.byjusexamprep.hackathon.data.model.User

object SharedPrefHelper {
    private var preferencesHashMap = HashMap<String, SharedPreferences>()
    private val sharedPreferencesFiles = SharedPreferencesFiles()

    private fun getSharedPreferences(preferencesFile: String, context: Context): SharedPreferences {

        var preferences: SharedPreferences? = preferencesHashMap[preferencesFile]
        if (preferences == null) {
            preferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
            preferencesHashMap[preferencesFile] = preferences
        }

        return preferences!!
    }

    fun getUserScore(context: Context?, user: String): String? {
        if (context == null) return ""
        val sharedPreferences = getSharedPreferences(sharedPreferencesFiles.USER, context)
        return sharedPreferences.getString("cookie", null)
    }

    fun setUserScore(context: Context, user : User) {
        val sharedPreferences = getSharedPreferences(sharedPreferencesFiles.USER, context)
        val edit = sharedPreferences.edit()
        edit.putString(user.name, user.score)
        edit.apply()
    }



}
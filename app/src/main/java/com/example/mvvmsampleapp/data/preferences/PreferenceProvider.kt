package com.example.mvvmsampleapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.hardware.camera2.CaptureResult
import android.security.keystore.KeyNotYetValidException
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(context: Context) {

    private val applicationContext = context.applicationContext

    private val preference : SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(applicationContext)


    fun saveLastSavedAt(savedAt : String){
        preference.edit().putString(
            KEY_SAVED_AT,savedAt
        ).apply()
    }

    fun getLastSavedAt() : String?{
        return preference.getString(KEY_SAVED_AT,null)
    }

}
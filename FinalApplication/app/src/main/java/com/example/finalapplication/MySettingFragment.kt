package com.example.finalapplication

import android.os.Bundle
import android.text.TextUtils
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference

// 설정
class MySettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        // id 값이 있을 수도 있고, 없을 수도 있음
        val idPreference: EditTextPreference? = findPreference("id")
        idPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> {
                preference ->
            val text:String? = preference.text
            if(TextUtils.isEmpty(text)){
                "ID 설정이 되지 않았습니다."
            }
            else{
                "설정된 ID는 $text 입니다."
            }
        }


        val bgColorPreference: ListPreference? = findPreference("bg_color")
        bgColorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()


        val txColorPreference:ListPreference? = findPreference("tx_color")
        txColorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
    }

}
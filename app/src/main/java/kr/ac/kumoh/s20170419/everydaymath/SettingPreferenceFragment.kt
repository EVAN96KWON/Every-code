package kr.ac.kumoh.s20170419.everydaymath

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup

class SettingPreferenceFragment : PreferenceFragmentCompat() {

    //아이콘 크기 만큼 생기는 여백 없애주기 위한 메소드, 버그인듯
    private fun Preference.removeIconSpace() {
        isIconSpaceReserved = false
        if (this is PreferenceGroup) {
            for (i in 0 until preferenceCount) {
                getPreference(i).removeIconSpace()
            }
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings_preference)
        preferenceScreen.removeIconSpace()
    }
}
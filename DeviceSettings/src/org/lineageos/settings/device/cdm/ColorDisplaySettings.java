/*
 * Copyright (C) 2018 The Xiaomi-SDM660 Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.lineageos.settings.device.cdm;

import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.PreferenceFragment;
import androidx.preference.Preference;

import org.lineageos.settings.device.FileUtils;
import org.lineageos.settings.device.R;
import org.lineageos.settings.device.preferences.SecureSettingCustomSeekBarPreference;
import org.lineageos.settings.device.preferences.SecureSettingSwitchPreference;

public class ColorDisplaySettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener, Utils {

    private SecureSettingSwitchPreference mEnabled;
    private SecureSettingSwitchPreference mSetOnBoot;
    private SecureSettingCustomSeekBarPreference mRed;
    private SecureSettingCustomSeekBarPreference mGreen;
    private SecureSettingCustomSeekBarPreference mBlue;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_cdm, rootKey);

        mRed = (SecureSettingCustomSeekBarPreference) findPreference(PREF_RED);
        mRed.setMax(RED_DEFAULT);
        mRed.setValue(RED_DEFAULT);
        mRed.setOnPreferenceChangeListener(this);

        mGreen = (SecureSettingCustomSeekBarPreference) findPreference(PREF_GREEN);
        mGreen.setMax(GREEN_DEFAULT);
        mGreen.setValue(GREEN_DEFAULT);
        mGreen.setOnPreferenceChangeListener(this);

        mBlue = (SecureSettingCustomSeekBarPreference) findPreference(PREF_BLUE);
        mBlue.setMax(BLUE_DEFAULT);
        mBlue.setValue(BLUE_DEFAULT);
        mBlue.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        final String key = preference.getKey();

        String rgbString;

        switch (key) {
            case PREF_RED:
                Settings.Secure.getInt(getContext().getContentResolver(),
                    Settings.Secure.DISPLAY_COLOR_BALANCE_RED, (int) value);
                break;

            case PREF_GREEN:
                Settings.Secure.getInt(getContext().getContentResolver(),
                    Settings.Secure.DISPLAY_COLOR_BALANCE_GREEN, (int) value);
                break;

            case PREF_BLUE:
                Settings.Secure.getInt(getContext().getContentResolver(),
                    Settings.Secure.DISPLAY_COLOR_BALANCE_BLUE, (int) value);
                break;

            default:
                break;
        }
        return true;
    }

    void applyValues(String preset) {
        String[] values = preset.split(" ");
        int red = Utils.getFinalColorValue(Integer.parseInt(values[0]));
        int green = Utils.getFinalColorValue(Integer.parseInt(values[1]));
        int blue = Utils.getFinalColorValue(Integer.parseInt(values[2]));

        mRed.refresh(red);
        mGreen.refresh(green);
        mBlue.refresh(blue);
    }
}


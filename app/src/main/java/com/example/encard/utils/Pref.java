package com.example.encard.utils;


import android.content.SharedPreferences;


import javax.inject.Inject;


public class Pref {
    private final SharedPreferences preferences;

    @Inject
    public Pref(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
    }

    public void saveState() {
        preferences.edit().putBoolean(KeyString.SHOW, true).apply();
    }

    public Boolean isBoardShow() {
        return preferences.getBoolean(KeyString.SHOW, false);
    }
}

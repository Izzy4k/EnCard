package com.example.encard.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;


public class Pref {
    private final SharedPreferences preferences;

    public Pref(@NonNull Context context) {
        this.preferences = context.getSharedPreferences(EndPoints.ABOBA, MODE_PRIVATE);
    }

    public void saveState() {
        preferences.edit().putBoolean(KeyString.SHOW, true).apply();
    }

    public Boolean isBoardShow() {
        return preferences.getBoolean(KeyString.SHOW, false);
    }
}

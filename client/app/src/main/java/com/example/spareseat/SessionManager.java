package com.example.spareseat;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.spareseat.model.UserResponse;

public class SessionManager {

    private static final String PREFS_NAME = "spareseat_session";
    private static final String KEY_LOGGED_IN = "is_logged_in";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_PHONE = "user_phone";
    private static final String KEY_USER_ROLE = "user_role";

    public static void save(Context context, UserResponse user) {
        SharedPreferences.Editor editor = prefs(context).edit();
        editor.putBoolean(KEY_LOGGED_IN, true);
        if (user != null) {
            editor.putLong(KEY_USER_ID, user.getId() != null ? user.getId() : -1);
            editor.putString(KEY_USER_NAME, user.getName());
            editor.putString(KEY_USER_EMAIL, user.getEmail());
            editor.putString(KEY_USER_PHONE, user.getPhoneNumber());
            editor.putString(KEY_USER_ROLE, user.getRole());
        }
        editor.apply();
    }

    public static void clear(Context context) {
        prefs(context).edit().clear().apply();
    }

    public static boolean isLoggedIn(Context context) {
        return prefs(context).getBoolean(KEY_LOGGED_IN, false);
    }

    public static String getUserName(Context context) {
        return prefs(context).getString(KEY_USER_NAME, "");
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}

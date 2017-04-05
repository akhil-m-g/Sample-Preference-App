package com.example.akhilmg.samplepreferenceapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.akhilmg.samplepreferenceapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKHIL MG on 25-03-2017.
 */

public class SamplePreference {

    private final String SUBJECT_LIST = "subject_list";

    SharedPreferences.Editor editor;
    /**
     * Freight preferences.
     */
    private SharedPreferences csPref;
    /**
     * Calling activity.
     */
    private Context callingActivity;

    /**
     * constructor for preferences.
     *
     * @param activity calling activity.
     */

    public SamplePreference(Context activity) {
        this.callingActivity = activity;
        csPref = callingActivity.getSharedPreferences(callingActivity
                        .getResources().getString(R.string.app_name),
                Activity.MODE_PRIVATE);
        editor = csPref.edit();
    }

    public ArrayList<String> getSubjectList() {
        Gson gson = new Gson();
        String json = csPref.getString(SUBJECT_LIST, null);
        ArrayList<String> obj;
        Type t = new TypeToken<List<String>>() {
        }.getType();
        obj = gson.fromJson(json, t);
        return obj;
    }

    public void setSubjectList(ArrayList<String> subjects) {
        String json = new Gson().toJson(subjects);
        editor.putString(SUBJECT_LIST, json);
        editor.commit();
    }
}

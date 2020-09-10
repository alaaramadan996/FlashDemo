package com.alaaramadan.flashdemo.data.local;

import android.app.Activity;
import android.content.SharedPreferences;

import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.DataGovernorate;
import com.google.gson.Gson;

public class SharedPreferencesManger {

    public static SharedPreferences sharedPreferences ;
    private static String LANG = "LANG";
    public static final String CITY_DATA = "CITY_DATA";
    public static final String GOV_DATA = "GOV_DATA";
    public static final String ADDRESS = "ADDRESS";
    public static final String ABOUT = "ABOUT";
    public static final String PRIVACY = "PRIVACY";
    public static final String LESSON_SHOW = "LESSON_SHOW";
    public static final String COURSE_DATA = "COURSE_DATA";

    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Azhry", activity.MODE_PRIVATE);
        }
    }
public static void saveDateApp(String value_Key,String value_data)
{
    SharedPreferences.Editor editor= sharedPreferences.edit();
    editor.putString(value_Key,value_data);
    editor.commit();
}
    public static void saveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }

    public static String loadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getString(data_Key, null);
    }


    public static boolean loadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static DataCity loadCityData(Activity activity) {
        DataCity dataCity = null;

        Gson gson = new Gson();
        if (loadData(activity, CITY_DATA) != null) {
            dataCity = gson.fromJson(loadData(activity, CITY_DATA), DataCity.class);
        }

        return dataCity;
    }
    public static DataGovernorate loadGovernorateData(Activity activity) {
        DataGovernorate dataGovernorate = null;

        Gson gson = new Gson();
        if (loadData(activity, CITY_DATA) != null) {
            dataGovernorate = gson.fromJson(loadData(activity, GOV_DATA), DataGovernorate.class);
        }

        return dataGovernorate;
    }
/*
    public static AuthData loadUserData(Activity activity) {
        AuthData authData = null;

        Gson gson = new Gson();
        if (loadData(activity, USER_DATA) != null) {
            authData = gson.fromJson(loadData(activity, USER_DATA), AuthData.class);
        }

        return authData;
    }





    public static CoursesData loadCourseData(Activity activity) {
        CoursesData coursesData = null;

        Gson gson = new Gson();
        if (loadData(activity, COURSE_DATA) != null) {
            coursesData = gson.fromJson(loadData(activity, COURSE_DATA), CoursesData.class);
        }

        return coursesData;
    }*/
//    public static AddressData loadAddress(Activity activity) {
//        AddressData addressData = null;
//
//        Gson gson = new Gson();
//        if (loadData(activity, ADDRESS) != null) {
//            addressData = gson.fromJson(loadData(activity, ADDRESS), AddressData.class);
//        }
//
//        return addressData;
//    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

    public static void saveLang(Activity activity, String lang) {
        saveData(activity, LANG, lang);
    }

    public static String loadLang(Activity activity) {
        setSharedPreferences(activity);

        return sharedPreferences.getString(LANG, "ar");
    }

}

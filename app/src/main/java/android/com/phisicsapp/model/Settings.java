package android.com.phisicsapp.model;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    private static final String KEY_PREFERENCES = "preferences";
    private static final String KEY_COURSE = "course";
    private static final String KEY_GROUP = "group";

    private SharedPreferences preferences;

    private static Settings instance;

    public static void init(Context context) {
        instance = new Settings(context);
    }

    public static Settings getInstance() {
        return instance;
    }

    public Settings(Context context) {
        preferences = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setGroup(Group group) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_GROUP, JsonSerializer.toJson(group));
        editor.apply();
    }

    public Group getGroup() {
        return (Group) JsonSerializer.fromJson(preferences.getString(KEY_GROUP, null), Group.class);
    }

    public void setCourse(Course course) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_COURSE, JsonSerializer.toJson(course));
        editor.apply();
    }

    public Course getCourse() {
        return (Course) JsonSerializer.fromJson(preferences.getString(KEY_COURSE, null), Course.class);
    }
}
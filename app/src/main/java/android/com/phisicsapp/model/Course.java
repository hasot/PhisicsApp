package android.com.phisicsapp.model;

import android.com.phisicsapp.database.DbHelper;
import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements ISpinner, Serializable {
    private int id;
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public static ArrayList<Course> getList() {
        final ArrayList<Course> result = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        DbHelper.getInstance().execute(sql, null, new DbHelper.OnExecutedListener() {
            @Override
            public void onExecute(Cursor cursor) {
                int idColIndex = cursor.getColumnIndex("id");
                int nameColIndex = cursor.getColumnIndex("name");
                while (cursor.moveToNext()) {
                    result.add(new Course(cursor.getInt(idColIndex), cursor.getString(nameColIndex)));
                }
            }
        });
        return result;
    }
}

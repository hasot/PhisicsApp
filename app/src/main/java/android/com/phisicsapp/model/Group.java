package android.com.phisicsapp.model;

import android.com.phisicsapp.database.DbHelper;
import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements ISpinner, Serializable {
    private int id;
    private String name;
    private int courseId;

    public Group(int id, String name, int courseId) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    public static ArrayList<Group> getList(final int courseId) {
        final ArrayList<Group> result = new ArrayList<>();
        String sql = "SELECT * FROM groups WHERE id_couse = ?";
        String[] params = new String[]{String.valueOf(courseId)};
        DbHelper.getInstance().execute(sql, params, new DbHelper.OnExecutedListener() {
            @Override
            public void onExecute(Cursor cursor) {
                int idColIndex = cursor.getColumnIndex("id");
                int nameColIndex = cursor.getColumnIndex("name");
                while (cursor.moveToNext()) {
                    result.add(new Group(cursor.getInt(idColIndex), cursor.getString(nameColIndex), courseId));
                }
            }
        });
        return result;
    }
}

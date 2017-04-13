package android.com.phisicsapp.model;

import android.com.phisicsapp.database.DbHelper;
import android.database.Cursor;

import java.util.ArrayList;

public class Schedule {
    public static final int WEEK_TYPE_TOP = 1;
    public static final int WEEK_TYPE_BOTTOM = 2;
    private int id;
    private int dayOfWeek;
    private String name;
    private String dateStart;
    private String dateEnd;
    private int weekType;
    private int groupId;

    public Schedule(int id, int dayOfWeek, String name, String dateStart, String dateEnd, int weekType, int groupId) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.weekType = weekType;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public String getName() {
        return name;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public int getWeekType() {
        return weekType;
    }

    public int getGroupId() {
        return groupId;
    }

    public static ArrayList<ArrayList<Schedule>> getList(final int groupId) {
        final ArrayList<ArrayList<Schedule>> result = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            String sql = "SELECT * FROM schedules WHERE id_group = ? AND day_of_week = ?";
            String[] params = new String[]{String.valueOf(groupId), String.valueOf(i)};
            DbHelper.getInstance().execute(sql, params, new DbHelper.OnExecutedListener() {
                @Override
                public void onExecute(Cursor cursor) {
                    int idColIndex = cursor.getColumnIndex("id");
                    int dayOfWeekColIndex = cursor.getColumnIndex("day_of_week");
                    int nameColIndex = cursor.getColumnIndex("name");
                    int dateStartColIndex = cursor.getColumnIndex("date_start");
                    int dateEndColIndex = cursor.getColumnIndex("date_end");
                    int weekTypeColIndex = cursor.getColumnIndex("week_type");
                    ArrayList<Schedule> schedules = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        schedules.add(new Schedule(cursor.getInt(idColIndex), cursor.getInt(dayOfWeekColIndex), cursor.getString(nameColIndex),
                                cursor.getString(dateStartColIndex), cursor.getString(dateEndColIndex), cursor.getInt(weekTypeColIndex), groupId));
                    }
                    if (!schedules.isEmpty())
                        result.add(schedules);
                }
            });
        }
        return result;
    }
}

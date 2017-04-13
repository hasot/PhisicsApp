package android.com.phisicsapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "physicsApp.sqlite";
    private static final int DB_VERSION = 1;
    private static DbHelper instance = null;

    private final Context context;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        createDataBase();
    }

    public static void init(Context context) {
        instance = new DbHelper(context);
    }

    public static DbHelper getInstance() {
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void createDataBase() {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            SQLiteDatabase db = getReadableDatabase();
            db.close();
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String path = context.getDatabasePath(DB_NAME).getPath();
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME);
        File file = context.getDatabasePath(DB_NAME);
        if (!file.exists())
            file.getParentFile().mkdirs();
        OutputStream output = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }

    public void execute(String sql, String[] params, OnExecutedListener listener) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            listener.onExecute(db.rawQuery(sql, params));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnExecutedListener {
        void onExecute(Cursor cursor);
    }
}

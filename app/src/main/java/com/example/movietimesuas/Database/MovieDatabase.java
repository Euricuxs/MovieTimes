package com.example.movietimesuas.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.movietimesuas.Model.MovieSaved;
import java.util.ArrayList;

public class MovieDatabase extends SQLiteOpenHelper
{
    private Context context;
    public static final String DB_NAME = "Movie_Db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "Movie";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_POSTER = "Poster";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_YEAR = "Year";
    public static final String COLUMN_IMBD_ID = "imbdId";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_POSTER + " TEXT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_YEAR + " TEXT,"
            + COLUMN_IMBD_ID + " TEXT" + ");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public MovieDatabase(@Nullable Context context)
    {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_TABLE);
    }

    public void saveMovie(String poster, String nama, String tahun, String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_POSTER,poster);
        contentValues.put(COLUMN_NAME,nama);
        contentValues.put(COLUMN_YEAR,tahun);
        contentValues.put(COLUMN_IMBD_ID,id);
        long status = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(status == -1)
        {
            Toast.makeText(context, "Failed added to saved movies",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.close();
        }
        else
        {
            Toast.makeText(context, "Success added to saved movies",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.close();
        }
    }

    public void deleteMovieID(String movieNameid)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME," imbdId=?", new String[]{movieNameid});
        Toast.makeText(context, "Success deleted from saved movies",Toast.LENGTH_SHORT).show();
        sqLiteDatabase.close();
    }

    public ArrayList<MovieSaved> getData()
    {
        ArrayList<MovieSaved> movieSaveds = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor kursor = sqLiteDatabase.rawQuery(select,null);
        if(kursor.moveToNext())
        {
            do {
               MovieSaved saved = new MovieSaved(""+kursor.getString(1), kursor
               .getString(2),kursor.getString(3), kursor.getString(4));
               movieSaveds.add(saved);
            }while (kursor.moveToNext());
        }
        return movieSaveds;
    }

    public boolean cekMovieID(String movieImbdId)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor kursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE imbdId=?", new String[]{movieImbdId});
        if (kursor.getCount() > 0)
        {
            kursor.close();
            return true;
        }
        else
        {
            kursor.close();
            return false;
        }
    }
}

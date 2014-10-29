package com.whs.hmcc;

/**
 * Created by root on 30/06/13.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {

    public static final String KEY_CROWID = "_id";
    public static final String KEY_CNAME = "name";
    public static final String KEY_CSPEED = "speed";
    public static final String KEY_CCOUNT = "count";
    public static final String KEY_CCOMBATID = "combat_id";
    public static final String KEY_CCURRENT = "continent";

    private static final String TAG = "DbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "HMCC";
    private static final String COMBAT_TABLE = "Combat";
    private static final String COMBATANT_TABLE = "Combatant";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE " +COMBAT_TABLE + " (" +
                    KEY_CROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_CNAME + " TEXT," +
                    KEY_CCOUNT + " INTEGER," +
                    KEY_CCURRENT + " INTEGER DEFAULT 0);";
    private static final String DATABASE_CREATE2 ="CREATE TABLE " +COMBATANT_TABLE + " (" +
                    KEY_CROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_CNAME + " TEXT," +
                    KEY_CSPEED + " INTEGER," +
                    KEY_CCOUNT + " INTEGER," +
                    KEY_CCOMBATID + " INTEGER);";


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*    Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);*/
        }
    }

    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public boolean deleteAllCombats() {

        int doneDelete = 0;
        doneDelete = mDb.delete(COMBAT_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public long createCombat(String name) {
        return createCombat(name,1);
    }
    public long createCombat(String name, Integer count) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CNAME, name);
        initialValues.put(KEY_CCOUNT,count);

        return mDb.insert(COMBAT_TABLE, null, initialValues);
    }

    public long createCombatant(String name, Integer speed, Integer count, long combat_id) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CNAME, name);
        initialValues.put(KEY_CSPEED, speed);
        initialValues.put(KEY_CCOUNT, count);
        initialValues.put(KEY_CCOMBATID, combat_id);

        return mDb.insert(COMBATANT_TABLE, null, initialValues);
    }

    public long createCombatant(Combatant c, long combat_id) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CNAME, c.getName());
        initialValues.put(KEY_CSPEED, c.getSpeed());
        initialValues.put(KEY_CCOUNT, c.getCount());
        initialValues.put(KEY_CCOMBATID, combat_id);

        return mDb.insert(COMBATANT_TABLE, null, initialValues);
    }

    public Cursor getCombats() {

        Cursor mCursor = mDb.query(COMBAT_TABLE, new String[] {KEY_CROWID,
                KEY_CNAME, KEY_CCOUNT},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getCombatsByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = getCombats();
        }else {
            mCursor = mDb.query(true, COMBAT_TABLE, new String[] {KEY_CROWID,
                    KEY_CNAME, KEY_CCOUNT},
                    KEY_CNAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor getCombatById(long combat_id) throws SQLException {
        Cursor mCursor = null;
        mCursor = mDb.query(true, COMBAT_TABLE, new String[] {KEY_CROWID,
                KEY_CNAME, KEY_CCOUNT},
                KEY_CROWID + " = " + String.valueOf(combat_id), null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor getCombatantsByCombat(long combat_id) throws SQLException {
        Log.w(TAG, String.valueOf(combat_id));
        Cursor mCursor = null;
        mCursor = mDb.query(true, COMBATANT_TABLE, new String[] {KEY_CROWID,
                  KEY_CNAME, KEY_CSPEED, KEY_CCOUNT, KEY_CCOMBATID},
                    KEY_CCOMBATID + " = " + String.valueOf(combat_id), null,
                    null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public void insertExampleData() {
        long c1 = createCombat("Test");
        Log.w(TAG, "test" + String.valueOf(c1));
        createCombatant("test",1,5,c1);
        createCombatant("test 2",4,8,c1);
        createCombat("234");
        createCombat("Tasddad");
        createCombat("hdsfsa");
        createCombat("111esdsd");

    }

}
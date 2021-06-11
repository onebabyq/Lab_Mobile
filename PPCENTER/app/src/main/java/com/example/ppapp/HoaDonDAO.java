package com.example.ppapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;


public class HoaDonDAO extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "HoaDonDatabase";


    // Table name: Note.
    private static final String TABLE_HOADON = "HoaDon";

    private static final String COLUMN_HOADON_ID ="id";
    private static final String COLUMN_HOADON_MAHOADON ="maHoaDon";
    private static final String COLUMN_HOADON_TRANGTHAI ="trangThai";
    private static final String COLUMN_HOADON_VITRITRANG = "viTriTrang";
    private static final String COLUMN_HOADON_TENSHIPPER = "tenShipper";
    private static final String COLUMN_HOADON_GIOXUAT = "gioXuat";
    private static final String COLUMN_HOADON_NGAYLAP = "ngayLap";
    private static final String COLUMN_HOADON_LOAIHOADON = "loaiHoaDon";

    public HoaDonDAO(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public String getDatabaseName(){
        return DATABASE_NAME;
    }
    public String getTableHoadon(){
        return TABLE_HOADON;
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_HOADON + "("
                + COLUMN_HOADON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_HOADON_MAHOADON + " TEXT,"
                + COLUMN_HOADON_TRANGTHAI + " TEXT,"
                + COLUMN_HOADON_VITRITRANG + " INTEGER,"
                + COLUMN_HOADON_TENSHIPPER + " TEXT,"
                + COLUMN_HOADON_GIOXUAT + " TEXT,"
                + COLUMN_HOADON_NGAYLAP + " TEXT,"
                + COLUMN_HOADON_LOAIHOADON + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOADON);

        // Create tables again
        onCreate(db);
    }


    // If Note table has no data
    // default, Insert 2 records.
    public void createDefaultNotesIfNeed()  {
        int count = this.getHoaDonCount();
        if(count ==0 ) {
            HoaDon hoaDon = new HoaDon();
            for(int i =10;i<60;i++){

                //hoaDon.setId(i);
                hoaDon.setMaHoaDon("2105310000"+String.valueOf(i));
                hoaDon.setTrangThai("Chưa xuất");
                hoaDon.setViTriTrang(12);
                hoaDon.setTenShipper("Sơn");
                hoaDon.setGioXuat("10:50");
                hoaDon.setNgayLap("2020/10/05");
                hoaDon.setLoaiHoaDon("Trong ngày");
                this.addHoaDon(hoaDon);
            }


        }
    }


    public void addHoaDon(HoaDon hoaDon) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + hoaDon.getMaHoaDon());

        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();
     //   values.put(COLUMN_HOADON_ID, hoaDon.getId());
        values.put(COLUMN_HOADON_MAHOADON, hoaDon.getMaHoaDon());
        values.put(COLUMN_HOADON_TRANGTHAI, hoaDon.getTrangThai());
        values.put(COLUMN_HOADON_VITRITRANG, hoaDon.getViTriTrang());
        values.put(COLUMN_HOADON_TENSHIPPER, hoaDon.getTenShipper());
        values.put(COLUMN_HOADON_GIOXUAT, hoaDon.getGioXuat());
        values.put(COLUMN_HOADON_NGAYLAP, hoaDon.getNgayLap());
        values.put(COLUMN_HOADON_LOAIHOADON, hoaDon.getLoaiHoaDon());



        // Inserting Row
        db.insert(TABLE_HOADON, null, values);

        // Closing database connection
        db.close();
    }


    public HoaDon getHoaDon(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + String.valueOf(id));

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HOADON, new String[] { COLUMN_HOADON_ID,
                        COLUMN_HOADON_MAHOADON, COLUMN_HOADON_TRANGTHAI,COLUMN_HOADON_VITRITRANG,COLUMN_HOADON_TENSHIPPER, COLUMN_HOADON_GIOXUAT,COLUMN_HOADON_NGAYLAP,COLUMN_HOADON_LOAIHOADON}, COLUMN_HOADON_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(cursor.getInt(0));
        hoaDon.setMaHoaDon(cursor.getString(1));
        hoaDon.setTrangThai(cursor.getString(2));
        hoaDon.setViTriTrang(cursor.getInt(3));
        hoaDon.setTenShipper(cursor.getString(4));
        hoaDon.setGioXuat(cursor.getString(5));
        hoaDon.setNgayLap(cursor.getString(6));
        hoaDon.setLoaiHoaDon(cursor.getString(7));


        // return note
        return hoaDon;
    }


    public List<HoaDon> getAllHoaDon() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<HoaDon> hoaDonList = new ArrayList<HoaDon>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HOADON ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(cursor.getInt(0));
                hoaDon.setMaHoaDon(cursor.getString(1));
                hoaDon.setTrangThai(cursor.getString(2));
                hoaDon.setViTriTrang(cursor.getInt(3));
                hoaDon.setTenShipper(cursor.getString(4));
                hoaDon.setGioXuat(cursor.getString(5));
                hoaDon.setNgayLap(cursor.getString(6));
                hoaDon.setLoaiHoaDon(cursor.getString(7));
              //  Log.i(TAG, "MyDatabaseHelper.HOADON ... "+hoaDon.toString() );
                // Adding note to list
                hoaDonList.add(hoaDon);
            } while (cursor.moveToNext());
        }

        // return note list
       // Log.i(TAG, "MyDatabaseHelper.LIST SIZE ... "+this.getHoaDonCount() );
        return hoaDonList;
    }

    public List<HoaDon> findListHoaDon(String maHoaDon) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<HoaDon> hoaDonList = new ArrayList<HoaDon>();
        // Select All Query
       // String selectQuery = "SELECT  * FROM " + TABLE_HOADON+ " WHERE maHoaDon like ?";

        String selectQuery = "SELECT  * FROM " + TABLE_HOADON+" WHERE maHoaDon like '%'||?||'%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{maHoaDon});
//        Cursor cursor = db.query(TABLE_HOADON, new String[] { COLUMN_HOADON_ID,
//                        COLUMN_HOADON_MAHOADON, COLUMN_HOADON_TRANGTHAI,COLUMN_HOADON_VITRITRANG,COLUMN_HOADON_TENSHIPPER, COLUMN_HOADON_GIOXUAT,COLUMN_HOADON_NGAYLAP}, COLUMN_HOADON_MAHOADON + "=?",
//                new String[] { maHoaDon }, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(cursor.getInt(0));
                hoaDon.setMaHoaDon(cursor.getString(1));
                hoaDon.setTrangThai(cursor.getString(2));
                hoaDon.setViTriTrang(cursor.getInt(3));
                hoaDon.setTenShipper(cursor.getString(4));
                hoaDon.setGioXuat(cursor.getString(5));
                hoaDon.setNgayLap(cursor.getString(6));
                hoaDon.setLoaiHoaDon(cursor.getString(7));

                // Adding note to list
                hoaDonList.add(hoaDon);
            } while (cursor.moveToNext());
        }

        // return note list
        return hoaDonList;
    }
    public int getCountHoaDonByDate(String codeDate,String loaiHoaDon) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<HoaDon> hoaDonList = new ArrayList<HoaDon>();
        // Select All Query
        // String selectQuery = "SELECT  * FROM " + TABLE_HOADON+ " WHERE maHoaDon like ?";

        String selectQuery = "SELECT  * FROM " + TABLE_HOADON+" WHERE "+COLUMN_HOADON_MAHOADON+" like ?||'%'  and "+COLUMN_HOADON_LOAIHOADON+" like ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{codeDate,loaiHoaDon});
//        Cursor cursor = db.query(TABLE_HOADON, new String[] { COLUMN_HOADON_ID,
//                        COLUMN_HOADON_MAHOADON, COLUMN_HOADON_TRANGTHAI,COLUMN_HOADON_VITRITRANG,COLUMN_HOADON_TENSHIPPER, COLUMN_HOADON_GIOXUAT,COLUMN_HOADON_NGAYLAP}, COLUMN_HOADON_MAHOADON + "=?",
//                new String[] { maHoaDon }, null, null, null, null);

        // looping through all rows and adding to list
        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;

    }
    public int getCountHoaDonByDateDaXuat(String codeDate) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<HoaDon> hoaDonList = new ArrayList<HoaDon>();
        // Select All Query
        // String selectQuery = "SELECT  * FROM " + TABLE_HOADON+ " WHERE maHoaDon like ?";

        String selectQuery = "SELECT  * FROM " + TABLE_HOADON+" WHERE "+COLUMN_HOADON_MAHOADON+" like ?||'%'  and "+COLUMN_HOADON_LOAIHOADON+" not like 'Pending mới' and "+COLUMN_HOADON_TRANGTHAI+" like ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{codeDate,"Đã xuất"});
//        Cursor cursor = db.query(TABLE_HOADON, new String[] { COLUMN_HOADON_ID,
//                        COLUMN_HOADON_MAHOADON, COLUMN_HOADON_TRANGTHAI,COLUMN_HOADON_VITRITRANG,COLUMN_HOADON_TENSHIPPER, COLUMN_HOADON_GIOXUAT,COLUMN_HOADON_NGAYLAP}, COLUMN_HOADON_MAHOADON + "=?",
//                new String[] { maHoaDon }, null, null, null, null);

        // looping through all rows and adding to list
        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;

    }
    public List<String> phanLoaiTheoNgay(String loaiHoaDon) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<String> listCodeDate = new ArrayList<String>();
        // Select All Query
        // String selectQuery = "SELECT  * FROM " + TABLE_HOADON+ " WHERE maHoaDon like ?";

        String selectQuery = "select SUBSTR("+COLUMN_HOADON_MAHOADON+",1, 6) as codeDate from "+TABLE_HOADON+"  WHERE "+COLUMN_HOADON_LOAIHOADON+" like ? GROUP by codeDate";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{loaiHoaDon});
//        Cursor cursor = db.query(TABLE_HOADON, new String[] { COLUMN_HOADON_ID,
//                        COLUMN_HOADON_MAHOADON, COLUMN_HOADON_TRANGTHAI,COLUMN_HOADON_VITRITRANG,COLUMN_HOADON_TENSHIPPER, COLUMN_HOADON_GIOXUAT,COLUMN_HOADON_NGAYLAP}, COLUMN_HOADON_MAHOADON + "=?",
//                new String[] { maHoaDon }, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // Adding note to list
                listCodeDate.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // return note list
        return listCodeDate;
    }
    public List<String> phanLoaiTheoNgayDaXuat() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<String> listCodeDate = new ArrayList<String>();
        // Select All Query
        // String selectQuery = "SELECT  * FROM " + TABLE_HOADON+ " WHERE maHoaDon like ?";

        String selectQuery = "select SUBSTR("+COLUMN_HOADON_MAHOADON+",1, 6) as codeDate from "+TABLE_HOADON+"  WHERE "+COLUMN_HOADON_TRANGTHAI+" like 'Đã xuất' GROUP by codeDate";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
//        Cursor cursor = db.query(TABLE_HOADON, new String[] { COLUMN_HOADON_ID,
//                        COLUMN_HOADON_MAHOADON, COLUMN_HOADON_TRANGTHAI,COLUMN_HOADON_VITRITRANG,COLUMN_HOADON_TENSHIPPER, COLUMN_HOADON_GIOXUAT,COLUMN_HOADON_NGAYLAP}, COLUMN_HOADON_MAHOADON + "=?",
//                new String[] { maHoaDon }, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // Adding note to list
                listCodeDate.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // return note list
        return listCodeDate;
    }

    public int getHoaDonCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_HOADON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
    public int getCountDonHuy() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_HOADON +" WHERE "+COLUMN_HOADON_LOAIHOADON+" not like 'Pending mới' and "+COLUMN_HOADON_TRANGTHAI+" like 'Đơn hủy'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
    public int getHoaDonCountByLoaiDon(String loaiHoaDon) {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_HOADON +" WHERE "+COLUMN_HOADON_LOAIHOADON+" like ? and "+COLUMN_HOADON_TRANGTHAI+" not like 'Đơn hủy'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, new String[]{loaiHoaDon});

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
    public int getHoaDonCountByLoaiDonCoDonHuy(String loaiHoaDon) {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_HOADON +" WHERE "+COLUMN_HOADON_LOAIHOADON+" like ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, new String[]{loaiHoaDon});

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
    public int getHoaDonCountByLoaiDonDaXuat() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_HOADON +" WHERE  "+COLUMN_HOADON_TRANGTHAI+" like ? and "+COLUMN_HOADON_LOAIHOADON+" not like 'Pending mới'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, new String[]{"Đã xuất"});

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updateHoaDon(HoaDon hoaDon) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + hoaDon.getMaHoaDon());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HOADON_MAHOADON, hoaDon.getMaHoaDon());
        values.put(COLUMN_HOADON_TRANGTHAI, hoaDon.getTrangThai());
        values.put(COLUMN_HOADON_VITRITRANG, hoaDon.getViTriTrang());
        values.put(COLUMN_HOADON_TENSHIPPER, hoaDon.getTenShipper());
        values.put(COLUMN_HOADON_GIOXUAT, hoaDon.getGioXuat());
        values.put(COLUMN_HOADON_NGAYLAP, hoaDon.getNgayLap());
        values.put(COLUMN_HOADON_LOAIHOADON, hoaDon.getLoaiHoaDon());

        // updating row
        return db.update(TABLE_HOADON, values, COLUMN_HOADON_ID + " = ?",
                new String[]{hoaDon.getId()+""});
    }

    public void deleteHoaDon(HoaDon hoaDon) {
        Log.i(TAG, "MyDatabaseHelper.delete ... " + hoaDon.getMaHoaDon() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOADON, COLUMN_HOADON_ID + " = ?",
                new String[] { String.valueOf(hoaDon.getId()) });
        db.close();
    }

    public void resetApp() {
        Log.i(TAG, "MyDatabaseHelper.reset app... ");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOADON, null,
                null);
        db.close();
    }

    public void chuyenPending() {
        Log.i(TAG, "MyDatabaseHelper.Chuyen PEnding ... " );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOADON, COLUMN_HOADON_LOAIHOADON + " = ?",
                new String[] { String.valueOf("Trong ngày")});

        db.delete(TABLE_HOADON, COLUMN_HOADON_LOAIHOADON + " = ?",
                new String[] { String.valueOf("Pending cũ")});

        ContentValues values = new ContentValues();
        values.put(COLUMN_HOADON_LOAIHOADON,"Pending cũ");
        db.update(TABLE_HOADON,values,null,null);
        db.close();


    }
}
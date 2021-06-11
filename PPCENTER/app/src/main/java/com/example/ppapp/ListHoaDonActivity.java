package com.example.ppapp;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.ajts.androidmads.library.ExcelToSQLite;
import com.ajts.androidmads.library.SQLiteToExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListHoaDonActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String TAG = "SQLite";
    RecyclerView recyclerView;
    List<HoaDon> listHoaDon;
    HoaDonAdapter adapter;
    HoaDonDAO hoaDonDAO;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        verifyStoragePermissions(this);
        configActionBar();

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
       // Toast.makeText(this,"query: "+query,Toast.LENGTH_SHORT).show();
        hoaDonDAO = new HoaDonDAO(this);
       // hoaDonDAO.createDefaultNotesIfNeed();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listHoaDon = new ArrayList<HoaDon>();
        adapter = new HoaDonAdapter(ListHoaDonActivity.this,listHoaDon);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if(query==null)
            listHoaDon.addAll(hoaDonDAO.getAllHoaDon());
         else listHoaDon.addAll(hoaDonDAO.findListHoaDon(query));
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //adapter.notifyDataSetChanged();
        adapter.setOnItemClickedListener(new HoaDonAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int id) {
              //  Toast.makeText(ListHoaDonActivity.this, maHoaDon, Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ListHoaDonActivity.this,HoaDonActivity.class);
                intent1.putExtra("key_id",id);
                startActivity(intent1);
            }
        });
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                startActivity(new Intent(ListHoaDonActivity.this,TrangChuActivity.class));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

    }
    public void configActionBar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Tìm kiếm"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        //     actionBar.hide(); //Ẩn ActionBar nếu muốn
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        MenuItem menuItem = menu.findItem(R.id.item1);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search People");
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemTrangChu:
                startActivity(new Intent(ListHoaDonActivity.this,TrangChuActivity.class));
                break;
            case R.id.itemXuatBaoCao:
               // startActivity(new Intent(ListHoaDonActivity.this,ScanActivity.class));
                String directory_path = Environment.getExternalStorageDirectory().getPath() + "/EmartPPcenter/";
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                // Export SQLite DB as EXCEL FILE
                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), hoaDonDAO.getDatabaseName(), directory_path);
                sqliteToExcel.exportAllTables("hoaDons.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(ListHoaDonActivity.this,"Export Excel On Starting",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Toast.makeText(ListHoaDonActivity.this,"Export Excel Successfully",Toast.LENGTH_SHORT).show();
                        Toast.makeText(ListHoaDonActivity.this,directory_path,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.i(TAG, "MyDatabaseHelper.Error ... "+e);
                        e.printStackTrace();
                        Toast.makeText(ListHoaDonActivity.this,"Export Excel Error",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.itemNhapDuLieu:
                //startActivity(new Intent(ListHoaDonActivity.this,BaoCaoActivity.class));
                directory_path = Environment.getExternalStorageDirectory().getPath() + "/EmartPPcenter/hoaDons.xls";
                File file2 = new File(directory_path);
                if (!file2.exists()) {
                    Toast.makeText(this,"Không tìm thấy file",Toast.LENGTH_SHORT).show();
                    break;
                }
                //dbQueries.open();
                // Is used to import data from excel without dropping table
                // ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), DBHelper.DB_NAME);

                // if you want to add column in excel and import into DB, you must drop the table
                ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), hoaDonDAO.getDatabaseName(), false);
                // Import EXCEL FILE to SQLite
                excelToSQLite.importFromFile(directory_path, new ExcelToSQLite.ImportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String dbName) {
                        Toast.makeText(ListHoaDonActivity.this,"Đổ dữ liệu thành công",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(ListHoaDonActivity.this,"Import thành công",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ListHoaDonActivity.this,ListHoaDonActivity.class));
                        //Log.i(TAG, "MyDatabaseHelper.Error ... "+e);
                        //e.printStackTrace();
                        //Toast.makeText(ListHoaDonActivity.this,"import Excel Error",Toast.LENGTH_SHORT).show();
                    }
                });
                //dbQueries.close();
                break;
            case R.id.itemBaoCao:
                startActivity(new Intent(ListHoaDonActivity.this,BaoCaoActivity.class));
                break;
            case R.id.itemResetApp:
               // startActivity(new Intent(ListHoaDonActivity.this,BaoCaoActivity.class));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MessageRemoveFragment messageRemoveFragment = new MessageRemoveFragment(ListHoaDonActivity.this,new HoaDon(),"Reset App");
                fragmentTransaction.add(R.id.listFragment,messageRemoveFragment,"fragment");
                fragmentTransaction.commit();
                break;
            case R.id.itemDangXuat:
                startActivity(new Intent(ListHoaDonActivity.this,MainActivity.class));
                break;

            default:

        }
        return super.onOptionsItemSelected(item);
    }
    public void filter(String queryText)
    {
        List<HoaDon> copyList = new ArrayList<>();
        copyList.addAll(listHoaDon);
        listHoaDon.clear();

        if(queryText.isEmpty())
        {
            listHoaDon.addAll(copyList);
        }
        else
        {

            for(HoaDon hd: copyList)
            {
                if(hd.getMaHoaDon().toLowerCase().contains(queryText.toLowerCase()))
                {
                    listHoaDon.add(hd);
                }
            }

        }

        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        // This method can be used when a query is submitted eg. creating search history using SQLite DB

        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ListHoaDonActivity.this,ListHoaDonActivity.class);
        // int count =0;
//        for(HoaDon i : listHoaDon) {
//            intent.putExtra("key_" + count, i);
//            count++;
//        }

        //intent.putParcelableArrayListExtra("listHoaDon", (ArrayList) listHoaDon);
        //intent.putExtra("listHoaDon", (Parcelable) listHoaDon);
        intent.putExtra("query",query);
        startActivity(intent);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        filter(newText);
        return true;
    }


}
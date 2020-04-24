package com.example.app_mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.app_mahasiswa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    ListView listView;
    public static TextView text2,teks3;
    Button btnAdd;
    private TextView textView;
    private String JSON_STRING;
    private String URL_GET_ALL = "http://192.168.43.138/utsMobile/tampilSemuaMhs.php";
    //JSON Tags
    private String TAG_JSON_ARRAY="result";
    private String TAG_KODE = "Kode_mhs";
    private String TAG_NAMA = "Nama_mhs";
    private String TAG_TGL = "Tgl_lhr";
    private String TAG_JENKEL = "Jns_kel";
    private String TAG_AGAMA = "Agama";
    private String TAG_GOLDARAH = "Gol_darah";
    private String TAG_STATUS = "Status";
    private String TAG_KOTA = "Kota";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listMahasiswa);
        btnAdd =  (Button) findViewById(R.id.btn_add);
        listView.setOnItemClickListener(this);
        getJSON();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahMahasiswa.class);
                startActivity(intent);
            }
        });
    }
    private void showMahasiswa(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String kode = jo.getString(TAG_KODE);
                String nama = jo.getString(TAG_NAMA);
                String tgl = jo.getString(TAG_TGL);
                String jenkel, agama, gol, status;
                String kota = jo.getString(TAG_KOTA);

                if (jo.getInt(TAG_JENKEL) == 0){
                    jenkel = "Perempuan";
                }else {
                    jenkel = "Laki-Laki";
                }

                if (jo.getInt(TAG_AGAMA) == 1){
                    agama = "Islam";
                }else if (jo.getInt(TAG_AGAMA) == 2){
                    agama = "Kristen";
                }else if (jo.getInt(TAG_AGAMA) == 3){
                    agama = "Katholik";
                }else if (jo.getInt(TAG_AGAMA) == 4){
                    agama = "Hindu";
                }else if (jo.getInt(TAG_AGAMA) == 5){
                    agama = "Budha";
                }else{
                    agama = "Congfucu";
                }

                if (jo.getInt(TAG_GOLDARAH) == 1){
                    gol = "A";
                }else if (jo.getInt(TAG_GOLDARAH) == 2){
                    gol = "B";
                }else if (jo.getInt(TAG_GOLDARAH) == 3){
                    gol = "AB";
                }else {
                    gol = "O";
                }

                if (jo.getInt(TAG_STATUS) == 0){
                    status = "Tidak Aktif";
                }else {
                    status = "Aktif";
                }

                HashMap<String,String> employees = new HashMap<>();
                employees.put(TAG_KODE,kode);
                employees.put(TAG_NAMA,nama);
                employees.put(TAG_TGL,tgl);
                employees.put(TAG_JENKEL,jenkel);
                employees.put(TAG_AGAMA,agama);
                employees.put(TAG_GOLDARAH,gol);
                employees.put(TAG_STATUS,status);
                employees.put(TAG_KOTA,kota);
                list.add(employees);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, list, R.layout.list_item,
                new String[]{TAG_KODE,TAG_NAMA,TAG_TGL, TAG_JENKEL, TAG_AGAMA, TAG_GOLDARAH, TAG_STATUS, TAG_KOTA},
                new int[]{R.id.kode, R.id.nama,R.id.tanggal,R.id.jeniskel,R.id.agama,R.id.goldar,R.id.status,R.id.kota});
        listView.setAdapter(adapter);
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(URL_GET_ALL);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMahasiswa();
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, UpdateMahasiswa.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String kode = map.get(TAG_KODE).toString();
        String nama = map.get(TAG_NAMA).toString();
        String tanggal = map.get(TAG_TGL).toString();
        String kota = map.get(TAG_KOTA).toString();

        intent.putExtra(TAG_KODE,kode);
        intent.putExtra(TAG_NAMA,nama);
        intent.putExtra(TAG_TGL, tanggal);
        intent.putExtra(TAG_KOTA, kota);
        startActivity(intent);
    }
}

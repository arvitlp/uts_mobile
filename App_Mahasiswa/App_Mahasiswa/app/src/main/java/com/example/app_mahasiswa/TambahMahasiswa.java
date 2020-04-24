package com.example.app_mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.app_mahasiswa.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class TambahMahasiswa extends AppCompatActivity {
    private TextInputEditText teksKode, teksNama, tanggal, kota;
    private RadioGroup rgjeniskel, rggoldar, rgstatus;
    private RadioButton rbjeniskel, rbgoldar, rbstatus;
    private Button btn_simpan, btn_batal;
    private Spinner agama;
    private String URL_ADD = "http://192.168.43.138/utsMobile/tambahMhs.php";
    private String KEY_KODE = "Kode_mhs";
    private String KEY_NAMA = "Nama_mhs";
    private String KEY_TGL = "Tgl_lhr";
    private String KEY_JENKEL = "Jns_kel";
    private String KEY_AGAMA = "Agama";
    private String KEY_GOLDARAH = "Gol_darah";
    private String KEY_STATUS = "Status_mhs";
    private String KEY_KOTA = "Kota";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);

        agama = (Spinner) findViewById(R.id.sp_agama);
        teksKode =  findViewById(R.id.ti_KodeMhs);
        teksNama =  findViewById(R.id.ti_NamaMhs);
        tanggal =  findViewById(R.id.ti_Tgllahir);
        kota =  findViewById(R.id.ti_Kota);
        btn_simpan = (Button) findViewById(R.id.btn_save);
        btn_batal = (Button) findViewById(R.id.btn_back);

        rgjeniskel = (RadioGroup) findViewById(R.id.rg_jeniskel);
        rggoldar = (RadioGroup) findViewById(R.id.rg_goldar);
        rgstatus = (RadioGroup) findViewById(R.id.rg_status);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMahasiswa();
                Intent intent = new Intent(TambahMahasiswa.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahMahasiswa.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addMahasiswa(){
        String bjenis_kel, b_gol, bstatus, agm;
        int selectedRgJenkel = rgjeniskel.getCheckedRadioButtonId();
        rbjeniskel = (RadioButton) findViewById(selectedRgJenkel);

        int selectedRgGol = rggoldar.getCheckedRadioButtonId();
        rbgoldar = (RadioButton) findViewById(selectedRgGol);

        int selectedRgStatus = rgstatus.getCheckedRadioButtonId();
        rbstatus = (RadioButton) findViewById(selectedRgStatus);

        if (rbjeniskel.getText().equals("Perempuan")){
            bjenis_kel = "0";
        }else {
            bjenis_kel = "1";
        }

        if (rbgoldar.getText().equals("A")){
            b_gol = "1";
        }else if (rbgoldar.getText().equals("B")){
            b_gol = "2";
        }else if (rbgoldar.getText().equals("AB")){
            b_gol = "3";
        }else {
            b_gol = "4";
        }

        if (rbstatus.getText().equals("Tidak Aktif")){
            bstatus = "0";
        }else {
            bstatus = "1";
        }

        if (agama.getSelectedItem().equals("Islam")){
            agm = "1";
        }else if (agama.getSelectedItem().equals("Kristen")){
            agm = "2";
        }else if (agama.getSelectedItem().equals("Katholik")){
            agm = "3";
        }else if (agama.getSelectedItem().equals("Hindu")){
            agm = "4";
        }else if (agama.getSelectedItem().equals("Budha")){
            agm = "5";
        }else {
            agm = "6";
        }

        final String kode = teksKode.getText().toString();
        final String nama = teksNama.getText().toString();
        final String tgl = tanggal.getText().toString();
        final String jenkel = bjenis_kel;
        final String fAgm = agm;
        final String gol = b_gol;
        final String status = bstatus;
        final String kt = kota.getText().toString();

        class SimpanData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(KEY_KODE, kode);
                params.put(KEY_NAMA, nama);
                params.put(KEY_TGL, tgl);
                params.put(KEY_JENKEL, jenkel);
                params.put(KEY_AGAMA, fAgm);
                params.put(KEY_GOLDARAH, gol);
                params.put(KEY_STATUS, status);
                params.put(KEY_KOTA, kt);

                RequestHandler rh = new RequestHandler();
                String hs = rh.sendPostRequest(URL_ADD, params);
                return hs;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }
        SimpanData sd = new SimpanData();
        sd.execute();
    }
}


package com.example.app_mahasiswa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

public class UpdateMahasiswa extends AppCompatActivity {
    private TextInputEditText teksKode, teksNama, tekstanggal, tekskota;
    private RadioGroup rgjeniskel, rggoldar, rgstatus;
    private RadioButton rbjeniskel, rbgoldar, rbstatus;
    private Button btn_simpan, btn_batal,btn_hapus;
    private String kode,nama,tanggal, kota;
    private Spinner agama;
    private String URL_UPDATE = "http://192.168.43.138/utsMobile/updateMhs.php";
    private String URL_DELETE = "http://192.168.43.138/utsMobile/hapusMhs.php?kode=";
    private String TAG_KODE = "Kode_mhs";
    private String TAG_NAMA = "Nama_mhs";
    private String TAG_TGL = "Tgl_lhr";
    private String TAG_JENKEL = "Jns_kel";
    private String TAG_AGAMA = "Agama";
    private String TAG_GOLDARAH = "Gol_darah";
    private String TAG_STATUS = "Status_mhs";
    private String TAG_KOTA = "Kota";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        Intent intent = getIntent();
        kode= intent.getStringExtra(TAG_KODE);
        nama = intent.getStringExtra(TAG_NAMA);
        tanggal = intent.getStringExtra(TAG_TGL);
        kota = intent.getStringExtra(TAG_KOTA);

        agama = (Spinner) findViewById(R.id.sp_agama);
        teksKode =  findViewById(R.id.ti_KodeMhs);
        teksNama =  findViewById(R.id.ti_NamaMhs);
        tekstanggal =  findViewById(R.id.ti_Tgllahir);
        tekskota =  findViewById(R.id.ti_Kota);
        btn_simpan = (Button) findViewById(R.id.btn_save);
        btn_hapus = (Button) findViewById(R.id.btn_delete);

        rgjeniskel = (RadioGroup) findViewById(R.id.rg_jeniskel);
        rggoldar = (RadioGroup) findViewById(R.id.rg_goldar);
        rgstatus = (RadioGroup) findViewById(R.id.rg_status);

        teksKode.setText(kode);
        teksNama.setText(nama);
        tekstanggal.setText(tanggal);
        tekskota.setText(kota);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMahasiswa();
                Intent intent1 = new Intent(UpdateMahasiswa.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });
    }
    private void updateMahasiswa(){
        final String kode = teksKode.getText().toString();
        final String nama = teksNama.getText().toString();
        final String tanggal = tekstanggal.getText().toString();
        final String kota = tekskota.getText().toString();

        String bjenkel, bgol, bstatus, agm;

        int selectedRgJenkel = rgjeniskel.getCheckedRadioButtonId();
        rbjeniskel = (RadioButton) findViewById(selectedRgJenkel);

        int selectedRgGol = rggoldar.getCheckedRadioButtonId();
        rbgoldar = (RadioButton) findViewById(selectedRgGol);

        int selectedRgStatus = rgstatus.getCheckedRadioButtonId();
        rbstatus = (RadioButton) findViewById(selectedRgStatus);

        if (rbjeniskel.getText().equals("Perempuan")){
            bjenkel = "0";
        }else {
            bjenkel = "1";
        }

        if (rbgoldar.getText().equals("A")){
            bgol = "1";
        }else if (rbgoldar.getText().equals("B")){
            bgol = "2";
        }else if (rbgoldar.getText().equals("AB")){
            bgol = "3";
        }else {
            bgol = "4";
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

        final String jenkel = bjenkel;
        final String fAgm = agm;
        final String gol = bgol;
        final String status = bstatus;

        class update_Mhs extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(TAG_KODE, kode);
                params.put(TAG_NAMA, nama);
                params.put(TAG_TGL, tanggal);
                params.put(TAG_JENKEL, jenkel);
                params.put(TAG_AGAMA, fAgm);
                params.put(TAG_GOLDARAH, gol);
                params.put(TAG_STATUS, status);
                params.put(TAG_KOTA, kota);
                RequestHandler rh = new RequestHandler();
                String hs = rh.sendPostRequest(URL_UPDATE, params);
                return hs;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }
        update_Mhs um = new update_Mhs();
        um.execute();
    }
    private void deleteMhs(){
        class DeleteMhs extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateMahasiswa.this, "Update...", "Tunggu...", false, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(URL_DELETE, kode);
                return s;
            }
        }
        DeleteMhs dm = new DeleteMhs();
        dm.execute();
    }

    private void confirmDelete(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Yakin Ingin Menghapus Barang ini ?");
        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteMhs();
                        startActivity(new Intent(UpdateMahasiswa.this,MainActivity.class));
                        finish();
                    }
                });
        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

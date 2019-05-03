package com.kodingindonesia.mycrud;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class TampilLaptop extends AppCompatActivity implements View.OnClickListener{

    private EditText txtId, txtNama, txtWarna, txtStok;
    private RadioButton ultrabook, high, low;
    private String jenis;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pegawai);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID);

        txtId = (EditText) findViewById(R.id.id_laptop);
        txtNama = (EditText) findViewById(R.id.nama_laptop);
        txtWarna = (EditText) findViewById(R.id.warna_laptop);
        txtStok = (EditText) findViewById(R.id.stok);

        ultrabook = findViewById(R.id.ultrabook);
        high = findViewById(R.id.high_performance);
        low = findViewById(R.id.low_performance);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        txtId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilLaptop.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama = c.getString(konfigurasi.TAG_NAMA);
            String warna = c.getString(konfigurasi.TAG_WARNA);
            String stok = c.getString(konfigurasi.TAG_STOK);
            String jenis = c.getString(konfigurasi.TAG_JENIS);

            if (jenis.equals("Ultrabook")){
                ultrabook.setChecked(true);
            } else if (jenis.equals("High Performance")){
                high.setChecked(true);
            } else if (jenis.equals("Low Performance")) {
                low.setChecked(true);
            }

            txtNama.setText(nama);
            txtWarna.setText(warna);
            txtStok.setText(stok);
//            jenis.setText(jenis);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String nama = txtNama.getText().toString().trim();
        final String warna = txtWarna.getText().toString().trim();
        final String stok = txtStok.getText().toString().trim();

        if (nama.equals("")) {
            txtNama.setError("Nama Laptop Kosong");
        } else if (warna.equals("")){
            txtWarna.setError("Warna Laptop Kosong");
        } else if (stok.equals("")){
            txtStok.setError("Stok Kosong");
        }

        if (ultrabook.isChecked()){
            jenis = ultrabook.getText().toString().trim();
        } else if (high.isChecked()){
            jenis = high.getText().toString().trim();
        } else if (low.isChecked()){
            jenis = low.getText().toString().trim();
        } else {
            Toast.makeText(TampilLaptop.this, "Jenis laptop belum dipilih", Toast.LENGTH_SHORT).show();
        }

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilLaptop.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilLaptop.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID,id);
                hashMap.put(konfigurasi.KEY_EMP_NAMA,nama);
                hashMap.put(konfigurasi.KEY_EMP_WARNA,warna);
                hashMap.put(konfigurasi.KEY_EMP_STOK,stok);
                hashMap.put(konfigurasi.KEY_EMP_JENIS,jenis);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilLaptop.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilLaptop.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(TampilLaptop.this, TampilSemuaLaptop.class));
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

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}

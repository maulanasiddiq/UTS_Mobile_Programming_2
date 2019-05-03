package com.kodingindonesia.mycrud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText txtNama, txtWarna, txtStok;
    private RadioButton ultrabook, high, low;
    private String jenis;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi dari View
        txtNama = (EditText) findViewById(R.id.nama_laptop);
        txtWarna = (EditText) findViewById(R.id.warna_laptop);
        txtStok = (EditText) findViewById(R.id.stok);

        ultrabook = findViewById(R.id.ultrabook);
        high = findViewById(R.id.high_performance);
        low = findViewById(R.id.low_performance);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

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
            Toast.makeText(MainActivity.this, "Jenis laptop belum dipilih", Toast.LENGTH_SHORT).show();
        }



        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA,nama);
                params.put(konfigurasi.KEY_EMP_WARNA,warna);
                params.put(konfigurasi.KEY_EMP_STOK,stok);
                params.put(konfigurasi.KEY_EMP_JENIS,jenis);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this, TampilSemuaLaptop.class));
        }
    }
}

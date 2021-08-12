package com.example.praktikkum8_datapersistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements
        View.OnClickListener {
        public static final String FILENAME = "namafile.txt";
        Button BuatFile, UbahFile, BacaFile, HapusFile;
        TextView HasilBacaFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        BuatFile = findViewById(R.id.btnbuatfile);
        UbahFile = findViewById(R.id.btnubahfile);
        BacaFile = findViewById(R.id.btnbacafile);
        HapusFile = findViewById(R.id.btnhapusfile);
        HasilBacaFile = findViewById(R.id.txtbaca);

        BuatFile.setOnClickListener(this);
        UbahFile.setOnClickListener(this);
        BacaFile.setOnClickListener(this);
        HapusFile.setOnClickListener(this);
    }

    void BuatFile(){
        String isiFile = "Coba Isi Data File Text";
        File file = new File(getFilesDir(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }   catch (Exception e){
            e.printStackTrace();
        }
    }

    void UbahFile(){
        String ubah = "Update Isi Data File Text";
        File file = new File(getFilesDir(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        }   catch (Exception e){
            e.printStackTrace();
        }
    }

    void BacaFile(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);

        if(file.exists()){

            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();

                while (line!= null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            }   catch (IOException e){
                System.out.println("Error" +e.getMessage());
            }
            HasilBacaFile.setText(text.toString());
        }
    }

    void HapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }

    @Override
    public void onClick(View v){
        jalankanPerintah(v.getId());
    }

    public void jalankanPerintah(int id){
        switch (id){
            case R.id.btnbuatfile:
                BuatFile();
                break;
            case R.id.btnbacafile:
                BacaFile();
                break;
            case R.id.btnubahfile:
                UbahFile();
                break;
            case R.id.btnhapusfile:
                HapusFile();
                break;
        }
    }
}
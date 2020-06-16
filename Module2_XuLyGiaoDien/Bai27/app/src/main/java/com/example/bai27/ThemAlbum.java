package com.example.bai27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.Album;

import java.util.ArrayList;

public class ThemAlbum extends AppCompatActivity {
    EditText editMa, editTen;
    Button btnXoa, btnLuu;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        //getSupportActionBar().hide(); // hide the title bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_them_album);

        editMa = findViewById(R.id.editMa);
        editTen = findViewById(R.id.editTen);
        btnLuu = findViewById(R.id.btnLuu);
        btnXoa = findViewById(R.id.btnXoaTrang);
        editMa.requestFocus();

        intent = new Intent();

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMa.setText("");
                editTen.setText("");
                editMa.requestFocus();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMa.getText().toString().isEmpty()){
                    Toast.makeText(ThemAlbum.this, "Mã album đang trống",Toast.LENGTH_SHORT).show();
                }
                else if (editTen.getText().toString().isEmpty()){
                    Toast.makeText(ThemAlbum.this, "Tên album đang trống",Toast.LENGTH_SHORT).show();
                }
                else {
                    intent.putExtra("Ma", editMa.getText().toString());
                    intent.putExtra("Ten", editTen.getText().toString());
                    setResult(MainActivity.SAVE_NEW_ALBUM, intent);
                    finish();
                }
            }
        });
    }
}
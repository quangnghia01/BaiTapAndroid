package com.example.bai27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.Album;

import java.util.ArrayList;

public class SuaAlbum extends AppCompatActivity {
    EditText editMa, editTen;
    Button btnXoa, btnCapNhat;
    Intent intent;
    Bundle bundle;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_album);

        editMa = findViewById(R.id.editMaSua);
        editTen = findViewById(R.id.editTenSua);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoaTrangSua);
        editMa.requestFocus();

        intent = getIntent();
        bundle = intent.getBundleExtra("data");
        album = (Album) bundle.getSerializable("alb");
        editMa.setText(album.getMa());
        editTen.setText(album.getTen());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMa.setText("");
                editTen.setText("");
                editMa.requestFocus();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMa.getText().toString().isEmpty()){
                    Toast.makeText(SuaAlbum.this, "Mã album đang trống",Toast.LENGTH_SHORT).show();
                }
                else if (editTen.getText().toString().isEmpty()){
                    Toast.makeText(SuaAlbum.this, "Tên album đang trống",Toast.LENGTH_SHORT).show();
                }
                else {
                    album.setMa(String.valueOf(editMa.getText()));
                    album.setTen(String.valueOf(editTen.getText()));
                    bundle.putSerializable("alb",album);
                    intent.putExtra("data",bundle);

                    setResult(MainActivity.SAVE_EDIT_ALBUM, intent);
                    finish();
                }
            }
        });

    }
}
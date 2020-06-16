package com.example.bai27;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.model.Album;
import com.example.model.BaiHat;

import java.util.ArrayList;
//test change
public class MainActivity extends AppCompatActivity {
    Button btnThem, btnXem, btnQuanLy;
    public static ArrayList<Album> dsAlbum;
    public static ArrayList<BaiHat> dsBH;
    public static final int NEW_ALBUM = 11;
    public static final int SAVE_NEW_ALBUM = 16;
    public static final int EDIT_ALBUM = 12;
    public static final int SAVE_EDIT_ALBUM = 19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int testGit = 120;
        addControls();
        addEvent();
    }

    private void addControls() {
        btnQuanLy = (Button)findViewById(R.id.btnQuanLy);
        btnThem =  (Button)findViewById(R.id.btnThem);
        btnXem =  (Button)findViewById(R.id.btnXem);
        dsAlbum = new ArrayList<Album>();
        dsBH = new ArrayList<BaiHat>();
    }

    private void addEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemAlbum.class);
                startActivityForResult(intent,MainActivity.NEW_ALBUM);
            }
        });
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, XemAlbum.class);
                startActivity(intent);
            }
        });
        btnQuanLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuanLyAlbum.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case MainActivity.NEW_ALBUM:
                if(resultCode==MainActivity.SAVE_NEW_ALBUM){
                    String ma, ten;
                    ma = data.getStringExtra("Ma");
                    ten = data.getStringExtra("Ten");
                    dsAlbum.add(new Album(ma,ten));
                    Toast.makeText(MainActivity.this,"Lưu thành công!", Toast.LENGTH_LONG).show();
                }
                break;
            case MainActivity.EDIT_ALBUM:
                if(resultCode==MainActivity.SAVE_EDIT_ALBUM){

                }
                break;
            default:
                break;
        }
    }
}
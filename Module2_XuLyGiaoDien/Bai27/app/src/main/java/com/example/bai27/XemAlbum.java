package com.example.bai27;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.AlbumAdapter;
import com.example.model.Album;

import java.util.ArrayList;

public class XemAlbum extends AppCompatActivity {
    int pos_selected = -1;
    ListView lvAlbum;
    AlbumAdapter albumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_album);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos_selected = position;
                Intent intent = new Intent(XemAlbum.this,SuaAlbum.class);
                Album album = MainActivity.dsAlbum.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("alb",album);
                intent.putExtra("data",bundle);
                startActivityForResult(intent,MainActivity.EDIT_ALBUM);
            }
        });

        lvAlbum.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(XemAlbum.this);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xóa Album");
                builder.setMessage("Bạn chắc chắn muốn xóa?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.dsAlbum.remove(position);
                        albumAdapter.notifyDataSetChanged();
                        Toast.makeText(XemAlbum.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                //return false; mặc định khi set sự kiện sẽ trả về false
                return true; //trả về true sẽ không thực hiện setOnItemClickListener cùng lúc với onItemlongClick
            }
        });
    }

    private void addControls() {
        lvAlbum = this.<ListView>findViewById(R.id.list);
        albumAdapter = new AlbumAdapter(this,R.layout.custom_list,MainActivity.dsAlbum);
        lvAlbum.setAdapter(albumAdapter);
        albumAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case MainActivity.EDIT_ALBUM:
                if(resultCode == MainActivity.SAVE_EDIT_ALBUM){
                    String ma, ten;
                    Bundle bundle = new Bundle();
                    bundle = data.getBundleExtra("data");
                    Album album = (Album) bundle.getSerializable("alb");
                    ma = data.getStringExtra("Ma");
                    ten = data.getStringExtra("Ten");
                    MainActivity.dsAlbum.set(pos_selected,  album);
                    albumAdapter.notifyDataSetChanged();
                    Toast.makeText(XemAlbum.this,"Cập nhật thành công!", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}
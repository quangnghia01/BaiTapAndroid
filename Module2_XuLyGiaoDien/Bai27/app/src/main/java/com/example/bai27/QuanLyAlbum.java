package com.example.bai27;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.model.Album;
import com.example.model.BaiHat;

import java.util.ArrayList;

public class QuanLyAlbum extends AppCompatActivity {
    EditText editNgay, editTenBH;
    Spinner spinAlbum;
    Button btnThemBH;
    ImageButton btnNgay;
    ListView lvBH;
    ArrayList<String> dsAlbumSpinner;
    ArrayAdapter<String> albumAdapter;
    ArrayAdapter<BaiHat> baihatAdpater;
    int posSpinSelected = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_album);

        addControls();
        addEvent();
    }

    private void addControls() {
        editNgay = findViewById(R.id.editNgay);
        editTenBH = findViewById(R.id.editTenBH);
        btnNgay = findViewById(R.id.btnNgay);
        btnThemBH = findViewById(R.id.btnThemBH);
        spinAlbum = (Spinner)findViewById(R.id.spinAlbum);
        lvBH = findViewById(R.id.lvTenBH);

        dsAlbumSpinner = new ArrayList<>();
        for(Album alb:MainActivity.dsAlbum){
            dsAlbumSpinner.add(alb.getTen());
        }
        albumAdapter = new ArrayAdapter<String>(QuanLyAlbum.this,android.R.layout.simple_spinner_item, dsAlbumSpinner);
        albumAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinAlbum.setAdapter(albumAdapter);
        albumAdapter.notifyDataSetChanged();
    }

    private void addEvent() {
        spinAlbum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posSpinSelected = position;
                editNgay.setText("");
                editTenBH.setText("");
                editTenBH.requestFocus();
                ArrayList<BaiHat> dsBH_temp = new ArrayList<BaiHat>();
                for(BaiHat bh:MainActivity.dsBH){
                    if(bh.getMaAlbum()==MainActivity.dsAlbum.get(position).getMa()){
                        dsBH_temp.add(bh);
                        baihatAdpater = new ArrayAdapter<BaiHat>(QuanLyAlbum.this, android.R.layout.simple_list_item_1, dsBH_temp);
                        lvBH.setAdapter(baihatAdpater);
                        baihatAdpater.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editNgay.setText(dayOfMonth+1+"/"+month+1+"/"+year);
                    }
                };
                DatePickerDialog pickerDialog = new DatePickerDialog(QuanLyAlbum.this,callback,2020,6,14);
                pickerDialog.setTitle("Chọn ngày");
                pickerDialog.show();
            }
        });

        btnThemBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posSpinSelected == -1)
                {
                    Toast.makeText(QuanLyAlbum.this, "Chưa có Album nào được chọn!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (editTenBH.getText().toString().isEmpty()) {
                        Toast.makeText(QuanLyAlbum.this, "Tên bài hát rỗng!", Toast.LENGTH_SHORT).show();
                    } else if (editNgay.getText().toString().isEmpty()) {
                        Toast.makeText(QuanLyAlbum.this, "Ngày ra đĩa rỗng!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            String maAlbum, tenBH, ngay;
                            maAlbum = MainActivity.dsAlbum.get(posSpinSelected).getMa();
                            tenBH = editTenBH.getText().toString();
                            ngay = editNgay.getText().toString();
                            MainActivity.dsBH.add(new BaiHat(tenBH, ngay, maAlbum));
                            baihatAdpater = new ArrayAdapter<BaiHat>(QuanLyAlbum.this, android.R.layout.simple_list_item_1, MainActivity.dsBH);
                            lvBH.setAdapter(baihatAdpater);
                            baihatAdpater.notifyDataSetChanged();
                            Toast.makeText(QuanLyAlbum.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }catch (Exception ex){
                            Toast.makeText(QuanLyAlbum.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        lvBH.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyAlbum.this);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xóa bài hát");
                builder.setMessage("Bạn chắc chắn muốn xóa?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.dsBH.remove(position);
                        baihatAdpater.notifyDataSetChanged();
                        Toast.makeText(QuanLyAlbum.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                return false;
            }
        });
    }
}
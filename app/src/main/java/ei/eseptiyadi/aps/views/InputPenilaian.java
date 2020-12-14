package ei.eseptiyadi.aps.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.model.addData.ResponseUpdateNilai;
import ei.eseptiyadi.aps.model.inrombel.ResponseSiswaDetail;
import ei.eseptiyadi.aps.module.Preferences;
import ei.eseptiyadi.aps.network.ApiServices;
import ei.eseptiyadi.aps.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputPenilaian extends AppCompatActivity {

    TextView tv_tugas,tv_teori,tv_praktikum,tv_presensi;
    TextView tv_nama,tv_nis,tv_mapel,tv_pjg_mapel,cancel,edit;
    EditText et_edit_nilai;
    SwipeRefreshLayout swp_list_nilai;
    String IntentkodeMapel,IntentdescMapel,IntentGetnis,IntentGetname;

    String namaMapel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_penilaian);

        tv_nama = (TextView)findViewById(R.id.nama_murid);
        tv_nis = (TextView)findViewById(R.id.nis);
        tv_pjg_mapel = (TextView)findViewById(R.id.pjg_mapel);

        tv_tugas = (TextView) findViewById(R.id.edit_tugas);
        tv_teori = (TextView) findViewById(R.id.edit_teori);
        tv_praktikum = (TextView) findViewById(R.id.edit_praktikum);
        tv_presensi = (TextView) findViewById(R.id.edit_presensi);
        tv_mapel = (TextView) findViewById(R.id.nama_mapel);

        if (getIntent().getExtras() != null) {
            Bundle getPackage = getIntent().getExtras();
            IntentkodeMapel = getPackage.getString("KODEMAPEL");
            IntentdescMapel = getPackage.getString("DESCMAPEL");
            IntentGetnis = getPackage.getString("GETNIS");
            IntentGetname = getPackage.getString("GETNAME");
//            Toast.makeText(InputPenilaian.this,"contoh" + IntentGetname,Toast.LENGTH_SHORT).show();
            tv_nama.setText(IntentGetname);
            tv_nis.setText(IntentGetnis);
        }else{

        }
        swp_list_nilai = (SwipeRefreshLayout)findViewById(R.id.swrListNilai);
        swp_list_nilai.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDataDetailSiswa();
            }
        });

        showdDataDetailSiswa();

    }

    private void showdDataDetailSiswa() {

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseSiswaDetail> responseSiswaDetailCall = apiServices.siswaDetail(Preferences.getKey_KodePenilain(getBaseContext()),IntentGetnis,IntentkodeMapel);

        responseSiswaDetailCall.enqueue(new Callback<ResponseSiswaDetail>() {
            @Override
            public void onResponse(Call<ResponseSiswaDetail> call, Response<ResponseSiswaDetail> response) {

                if (response.isSuccessful()){
                    tv_tugas.setText(response.body().getDetailSiswa().getNilaiTugas());
                    tv_praktikum.setText(response.body().getDetailSiswa().getNilaiPraktikum());
                    tv_teori.setText(response.body().getDetailSiswa().getNilaiTeori());
                    tv_presensi.setText(response.body().getDetailSiswa().getNilaiAbsensi());

                    tv_mapel.setText(IntentkodeMapel);
                    tv_pjg_mapel.setText(IntentdescMapel);


                    Toast.makeText(InputPenilaian.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InputPenilaian.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseSiswaDetail> call, Throwable t) {
                Toast.makeText(InputPenilaian.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void refreshDataDetailSiswa() {

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseSiswaDetail> responseSiswaDetailCall = apiServices.siswaDetail(Preferences.getKey_KodePenilain(getBaseContext()),IntentGetnis,IntentkodeMapel);

        responseSiswaDetailCall.enqueue(new Callback<ResponseSiswaDetail>() {
            @Override
            public void onResponse(Call<ResponseSiswaDetail> call, Response<ResponseSiswaDetail> response) {

                if (response.isSuccessful()){
                    tv_tugas.setText(response.body().getDetailSiswa().getNilaiTugas());
                    tv_praktikum.setText(response.body().getDetailSiswa().getNilaiPraktikum());
                    tv_teori.setText(response.body().getDetailSiswa().getNilaiTeori());
                    tv_mapel.setText(IntentkodeMapel);
                    tv_pjg_mapel.setText(IntentdescMapel);
                    tv_nama.setText(IntentGetname);
                    tv_nis.setText(IntentGetnis);
                    tv_presensi.setText(response.body().getDetailSiswa().getNilaiAbsensi());
                    Toast.makeText(InputPenilaian.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    swp_list_nilai.setRefreshing(false);
                }else{
                    Toast.makeText(InputPenilaian.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseSiswaDetail> call, Throwable t) {
                Toast.makeText(InputPenilaian.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void edit_nilaiTugas(View view) {
        namaMapel = "nilai_tugas";
        showdialog(namaMapel);
    }

    public void edit_nilaiTeori(View view) {
        namaMapel = "nilai_teori";
        showdialog(namaMapel);
    }

    public void edit_nilaiPraktikum(View view) {
        namaMapel = "nilai_praktikum";
        showdialog(namaMapel);
    }

    public void edit_nilaiPresensi(View view) {
        namaMapel = "nilai_absensi";
        showdialog(namaMapel);
    }
    public void showdialog(String namaMapels){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_nilai, null);
        dialog.setView(dialogView);
        et_edit_nilai = (EditText)dialogView.findViewById(R.id.masukan_nilai);

//        cancel = (TextView)findViewById(R.id.tv_cancel);
//        edit = (TextView)findViewById(R.id.tv_edit);

        dialog
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (et_edit_nilai.getText().toString().trim().isEmpty()){

                            et_edit_nilai.setError("Please fill out this field");

                        }else{
                            int intNilai = Integer.valueOf(et_edit_nilai.getText().toString());
                            editNilai(intNilai,namaMapels);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        dialog.show();
    }



    private void editNilai(int nilai, String namaMapels) {

        ApiServices apiServices = RetrofitClient.getInstance();
        //ToDO debug
        Call<ResponseUpdateNilai> responseUpdateNilaiCall = apiServices.updateNilai(Preferences.getKey_KodePenilain(getBaseContext()),IntentGetnis,IntentkodeMapel,nilai,namaMapels);

        responseUpdateNilaiCall.enqueue(new Callback<ResponseUpdateNilai>() {
            @Override
            public void onResponse(Call<ResponseUpdateNilai> call, Response<ResponseUpdateNilai> response) {
                if (response.isSuccessful()){
                    if (response.body().isStatus()){
                        Toast.makeText(InputPenilaian.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        showdDataDetailSiswa();

                    }else{
                        Toast.makeText(InputPenilaian.this,response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(InputPenilaian.this,response.body().getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateNilai> call, Throwable t) {
                Toast.makeText(InputPenilaian.this,t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });
    }



}
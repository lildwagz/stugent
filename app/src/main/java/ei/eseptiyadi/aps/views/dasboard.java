package ei.eseptiyadi.aps.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.adapter.ListPenilaian;
import ei.eseptiyadi.aps.adapter.ListTahunAjaran;
import ei.eseptiyadi.aps.model.inrombel.ResponseDetailKelas;
import ei.eseptiyadi.aps.model.master.DataPenilaianItem;
import ei.eseptiyadi.aps.model.master.DataTahunajaranItem;
import ei.eseptiyadi.aps.model.master.ResponseDataPenilain;
import ei.eseptiyadi.aps.model.master.ResponseTahunAjaran;
import ei.eseptiyadi.aps.module.Preferences;
import ei.eseptiyadi.aps.module.Variable;
import ei.eseptiyadi.aps.network.ApiServices;
import ei.eseptiyadi.aps.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dasboard extends AppCompatActivity  {

    LinearLayout to_data,to_penilaian,to_detail_data,to_add_data;
    TextView tahun_ajaran,kls,wl_kelas, KodePenilaian;
    RadioButton rb_rpl,rb_tkj,rb_all,kls_10,kls_11,kls_12;
    RecyclerView recyclerView,rvKodePenilaian;
    RadioGroup radioGroup, rg_kelas;
    String str_rpl,str_tkj,str_all,str_10,str_11,str_12;
    String yearnow,kelas,KelasInit,KodepenilaianInit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);

        to_data = (LinearLayout)findViewById(R.id.goToData);
        to_penilaian = (LinearLayout)findViewById(R.id.goToPenilaian);
        to_detail_data = (LinearLayout)findViewById(R.id.goToDetail);
        to_add_data = (LinearLayout)findViewById(R.id.goToAdd);

        KodePenilaian = (TextView)findViewById(R.id.TvKodepenilaian);
        tahun_ajaran = (TextView) findViewById(R.id.thn_ajaran);
        kls = (TextView) findViewById(R.id.kelas);
        wl_kelas = (TextView) findViewById(R.id.wali_kelas);

        initiate();
    }

    public void initiate(){

        if(Preferences.getKey_TahunAjaran(getBaseContext()).isEmpty()){
            int getYear = Variable.GET_YEAR;
            int nextyear = getYear + 1;

            String yearnows = getYear+"/"+nextyear;
            Preferences.setKey_TahunAjaran(getBaseContext(),yearnows);
        }
        if (Preferences.getKey_Kelas(getBaseContext()).isEmpty()){
            Preferences.setKey_Kelas(getBaseContext(),"XI");

        }
        if (Preferences.getKey_KodePenilain(getBaseContext()).isEmpty()){
            Preferences.setKey_KodePenilain(getBaseContext(),"PAS-2020");

        }
        yearnow = Preferences.getKey_TahunAjaran(getBaseContext());
        KelasInit = Preferences.getKey_Kelas(getBaseContext());
        KodepenilaianInit = Preferences.getKey_KodePenilain(getBaseContext());
//        Toast.makeText(dasboard.this, "kelas after : "+Preferences.getKey_Kelas(getBaseContext()), Toast.LENGTH_SHORT).show();

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseDetailKelas> responseDetailSiswaCall = apiServices.detailsiswa(Preferences.getKey_TahunAjaran(getBaseContext()), KelasInit);

        responseDetailSiswaCall.enqueue(new Callback<ResponseDetailKelas>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseDetailKelas> call, Response<ResponseDetailKelas> response) {
                if (response.isSuccessful()) {
                    wl_kelas.setText(response.body().getDetailKelas().getWaliKelas());
                    kls.setText(" "+KelasInit);
                    tahun_ajaran.setText(Preferences.getKey_TahunAjaran(getBaseContext()));
                    KodePenilaian.setText(KodepenilaianInit);

                }else {
//                    Log.d("LOG","response"+response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<ResponseDetailKelas> call, Throwable t) {

            }
        });



    }


    public void penilaian(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.jurusan,null);
        alertDialog.setView(dialogview);
        radioGroup = (RadioGroup) dialogview.findViewById(R.id.radioGroub);
        rb_rpl = (RadioButton)dialogview.findViewById(R.id.rpl);
        rb_tkj = (RadioButton)dialogview.findViewById(R.id.tkj);
        rb_all = (RadioButton)dialogview.findViewById(R.id.all);

        final AlertDialog  dialog=alertDialog.create();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rpl:
                        Toast.makeText(dasboard.this, rb_rpl.getText(), Toast.LENGTH_SHORT).show();
                        str_rpl = rb_rpl.getText().toString().trim();
                        sendToActivityClass(str_rpl,KelasInit,yearnow);
                        dialog.dismiss();
                        break;
                    case R.id.tkj:
                        Toast.makeText(dasboard.this, rb_tkj.getText(), Toast.LENGTH_SHORT).show();
                        str_tkj = rb_tkj.getText().toString().trim();
                        sendToActivityClass(str_tkj,KelasInit,yearnow);
                        dialog.dismiss();
                        break;
                    case R.id.all:
                        Toast.makeText(dasboard.this, rb_all.getText(), Toast.LENGTH_SHORT).show();
                        sendToActivityClass("ALL",KelasInit,yearnow);
                        dialog.dismiss();
                        break;
                }
            }
        });

        dialog.show();
    }


    private void sendToActivityClass(String jurusan , String Kelas,String tahunajaran) {
        Intent intent = new Intent(dasboard.this,ActivityClass.class);
        intent.putExtra("KODEJURUSAN",jurusan);
        intent.putExtra("KODEKELAS",Kelas);
        intent.putExtra("KODETA",tahunajaran);
        startActivity(intent);
    }


    public void detail_data(View view) {
        Intent pindah = new Intent(dasboard.this,detail_data.class);
        startActivity(pindah);

    }

    public void add_data_siswa(View view) {
        Intent pindah = new Intent(dasboard.this, addDataSiswa.class);
        startActivity(pindah);

    }


    public void addmapel(View view) {
        Intent pindah = new Intent(dasboard.this, data_pelajaran.class);
        startActivity(pindah);
    }

    public void tahun_ajaran(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.tahun_ajaran,null);
        alertDialog.setView(dialogView);
        final AlertDialog  dialog=alertDialog.create();

        recyclerView = (RecyclerView) dialogView.findViewById(R.id.tahun_ajaran);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseTahunAjaran> dataTahunAjaran = apiServices.getTahunAjaran();

        dataTahunAjaran.enqueue(new Callback<ResponseTahunAjaran>() {
            @Override
            public void onResponse(Call<ResponseTahunAjaran> call, Response<ResponseTahunAjaran> response) {
                if (response.isSuccessful()) {
                    List<DataTahunajaranItem> tahunajaranItems = response.body().getDataTahunajaran();
                    if (response.body().isStatus()){
                        ListTahunAjaran adapterTahunAjaran = new ListTahunAjaran(dasboard.this, tahunajaranItems,status ->{
                            if (!status){
                                dialog.dismiss();
                                initiate();
                            }
                        });
                        recyclerView.setAdapter(adapterTahunAjaran);
                        Toast.makeText(dasboard.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(dasboard.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseTahunAjaran> call, Throwable t) {
                Toast.makeText(dasboard.this,"error"+ t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        dialog.show();

    }

    public void pilih_kelas(View view) {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.pilih_kelas, null);
        alertdialog.setView(dialogView);

        rg_kelas = (RadioGroup)dialogView.findViewById(R.id.radio_kelas);
        kls_10 = (RadioButton)dialogView.findViewById(R.id.kelas_10);
        kls_11 = (RadioButton)dialogView.findViewById(R.id.kelas_11);
        kls_12 = (RadioButton)dialogView.findViewById(R.id.kelas_12);

        final AlertDialog  dialog=alertdialog.create();

        rg_kelas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId){
                    case R.id.kelas_10 :
                        Toast.makeText(dasboard.this, kls_10.getText(), Toast.LENGTH_SHORT).show();
                        str_10 = kls_10.getText().toString().trim();
                        setPreferenceClass(str_10);
                        dialog.dismiss();
                        break;

                    case R.id.kelas_11 :
                        Toast.makeText(dasboard.this, kls_11.getText(), Toast.LENGTH_SHORT).show();
                        str_11 = kls_11.getText().toString().trim();
                        setPreferenceClass(str_11);
                        dialog.dismiss();
                        break;

                    case R.id.kelas_12 :
                        Toast.makeText(dasboard.this, kls_12.getText(), Toast.LENGTH_SHORT).show();
                        str_12 = kls_12.getText().toString().trim();
                        setPreferenceClass(str_12);
                        dialog.dismiss();
                        break;
                }
            }
        });


        dialog.show();

    }
    public void pilihPenilain(View view) {

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.dialog_kode_penilaian,null);
        alertdialog.setView(dialogview);
        final AlertDialog  dialog=alertdialog.create();


        rvKodePenilaian = (RecyclerView)dialogview.findViewById(R.id.RvKodePenilaian);
        rvKodePenilaian.setHasFixedSize(true);
        rvKodePenilaian.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseDataPenilain> dataPenilaianItemCall = apiServices.getDataPenilain();
        dataPenilaianItemCall.enqueue(new Callback<ResponseDataPenilain>() {
            @Override
            public void onResponse(Call<ResponseDataPenilain> call, Response<ResponseDataPenilain> response) {
                if(response.isSuccessful()){
                    List<DataPenilaianItem> dataPenilaianItems = response.body().getDataPenilaian();
                    if (response.body().isStatus()){
                        ListPenilaian adapterpenilain = new ListPenilaian(dasboard.this,dataPenilaianItems, status -> {
                            if (!status){
                                dialog.dismiss();
                                initiate();

                            }
                        });
                        rvKodePenilaian.setAdapter(adapterpenilain);

                        Toast.makeText(dasboard.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(dasboard.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseDataPenilain> call, Throwable t) {

            }
        });

        dialog.show();
    }
    public void setPreferenceClass(String kelas){
        if (Preferences.getKey_Kelas(getBaseContext()).isEmpty()){
            Preferences.setKey_Kelas(getBaseContext(),kelas);

        }else {
            Preferences.clearKeyKelas(getBaseContext());
            Preferences.setKey_Kelas(getBaseContext(),kelas);

        }

        initiate();

    }
    public void setPreferenceKodePenilain(String kodePenilain){
        if (Preferences.getKey_KodePenilain(getBaseContext()).isEmpty()){
            Preferences.setKey_KodePenilain(getBaseContext(),kodePenilain);

        }else {
            Preferences.clearKeyKodePenilian(getBaseContext());
            Preferences.setKey_KodePenilain(getBaseContext(),kodePenilain);

        }

        initiate();

    }


}
package ei.eseptiyadi.aps.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.adapter.FilterClass;
import ei.eseptiyadi.aps.adapter.InRombel;
import ei.eseptiyadi.aps.model.filter.DatasiswaItem;
import ei.eseptiyadi.aps.model.filter.ResponseRombel;
import ei.eseptiyadi.aps.model.inrombel.ResponseInRombel;
import ei.eseptiyadi.aps.model.inrombel.SiswainrombelItem;
import ei.eseptiyadi.aps.module.Preferences;
import ei.eseptiyadi.aps.network.ApiServices;
import ei.eseptiyadi.aps.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HEAD;

public class ActivityClass extends AppCompatActivity {
    RecyclerView rvListSiswaFilter;

    SwipeRefreshLayout srfListData;
    String getTahunAjaran, getJenjang;
    String kodeTahunAjaran,  kodeKelas, kodeJurusan;
    String totalsiswa;
    TextView kelas,jurusan,wali,jumlah,ketuakelas,nisn,nama;
    LinearLayout too_datapenilaian  ;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        kelas = (TextView)findViewById(R.id.Tvkelas1);
        jurusan = (TextView)findViewById(R.id.Tvjurusan1);
        wali = (TextView)findViewById(R.id.Tvwali1);
        jumlah = (TextView)findViewById(R.id.Tvjumlah1);
        ketuakelas = (TextView)findViewById(R.id.Tvketuakelas1);
        too_datapenilaian = (LinearLayout)findViewById(R.id.button1);
        srfListData = (SwipeRefreshLayout) findViewById(R.id.swrListdata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarClass);
        setSupportActionBar(toolbar);
        String title = Preferences.getKey_KodePenilain(getBaseContext());
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getIntent().getExtras() != null) {
            Bundle getPackage = getIntent().getExtras();
            getTahunAjaran = getPackage.getString("KODETA");
            kodeJurusan = getPackage.getString("KODEJURUSAN");
            kodeKelas = getPackage.getString("KODEKELAS");
            if (kodeJurusan.equals("ALL")){
                showAllSiswa(getTahunAjaran,kodeKelas);
//                Toast.makeText(ActivityClass.this,"intent\n"+"kelas "+kodeKelas+"\nTAHUN "+getTahunAjaran, Toast.LENGTH_SHORT).show();

            }else{
                showdataSiswa(getTahunAjaran,kodeKelas,kodeJurusan);
//                Toast.makeText(ActivityClass.this,"intent\n"+"kelas "+kodeKelas+"\nTAHUN "+getTahunAjaran, Toast.LENGTH_SHORT).show();


            }
        }else {
            getTahunAjaran = Preferences.getKey_TahunAjaran(getBaseContext());
            kodeJurusan = "ALL";
            kodeKelas = Preferences.getKey_Kelas(getBaseContext());
//            if (kodeJurusan.equals("ALL")){
            showAllSiswa(getTahunAjaran,kodeKelas);
//            }else{
//                showdataSiswa(getTahunAjaran,kodeKelas,kodeJurusan);
//            Toast.makeText(ActivityClass.this,"No intent\n"+"kelas "+kodeKelas+"\nTAHUN "+getTahunAjaran, Toast.LENGTH_SHORT).show();


//            }
        }



        srfListData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (kodeJurusan.equals("ALL")){
                    getUpdateListALlData(getTahunAjaran,kodeKelas);
                }else{
                    getUpdateListData(getTahunAjaran,kodeKelas,kodeJurusan);

                }

            }
        });

        rvListSiswaFilter = (RecyclerView)findViewById(R.id.rvlistdata);
        rvListSiswaFilter.setHasFixedSize(true);
        rvListSiswaFilter.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    private void getUpdateListALlData(String getTahunAjaran, String kodeKela) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseInRombel> inRombelCall = apiServices.inRombel(getTahunAjaran, kodeKela);

        inRombelCall.enqueue(new Callback<ResponseInRombel>() {
            @Override
            public void onResponse(Call<ResponseInRombel> call, Response<ResponseInRombel> response) {
                if (response.isSuccessful()) {
                    totalsiswa = response.body().getTotalsiswa();

                    List<SiswainrombelItem> siswainrombelItems = response.body().getSiswainrombel();
                    boolean status = response.body().isStatus();

                    if (status) {

                        InRombel adapterinRombel = new InRombel(ActivityClass.this, siswainrombelItems, kodeTahunAjaran);
                        rvListSiswaFilter.setAdapter(adapterinRombel);
                        kelas.setText(response.body().getKelas());
                        jurusan.setText(response.body().getJurusan());
                        wali.setText(response.body().getWaliKelas());
                        jumlah.setText(totalsiswa);
                        ketuakelas.setText(response.body().getKetuaKelas());
                        Toast.makeText(ActivityClass.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        srfListData.setRefreshing(false);
                    }
                    else {

                        Toast.makeText(ActivityClass.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseInRombel> call, Throwable t) {

            }
        });
    }

    private void getUpdateListData(String kodeTahunAjaran,String KodeKelas, String Jurusan) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseRombel> datasiswaFilterCall = apiServices.datasiswaFilter(kodeTahunAjaran, KodeKelas, Jurusan);

        datasiswaFilterCall.enqueue(new Callback<ResponseRombel>() {
            @Override
            public void onResponse(Call<ResponseRombel> call, Response<ResponseRombel> response) {
                if (response.isSuccessful()) {
                    totalsiswa = response.body().getTotalsiswa() ;

                    List<DatasiswaItem> datasiswaItems = response.body().getDatasiswa();
                    boolean status = response.body().isStatus();

                    if (status) {
                        FilterClass adapterFilterClass = new FilterClass(ActivityClass.this, datasiswaItems, Preferences.getKey_TahunAjaran(getBaseContext()));
                        rvListSiswaFilter.setAdapter(adapterFilterClass);
                        kelas.setText(response.body().getKelas());
                        jurusan.setText(response.body().getJurusan());
                        wali.setText(response.body().getWaliKelas());
                        jumlah.setText(totalsiswa);
                        ketuakelas.setText(response.body().getKetuaKelas());

                        Toast.makeText(ActivityClass.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        srfListData.setRefreshing(false);

                    }
                    else {
                        Toast.makeText(ActivityClass.this,
                                response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(ActivityClass.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseRombel> call, Throwable t) {
                Toast.makeText(ActivityClass.this,t.getMessage(), Toast.LENGTH_SHORT).show();



            }
        });
    }


    private void showdataSiswa(String kodeTahunAjaran,String KodeKelas, String Jurusan) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseRombel> datasiswaFilterCall = apiServices.datasiswaFilter(kodeTahunAjaran, KodeKelas, Jurusan);

        datasiswaFilterCall.enqueue(new Callback<ResponseRombel>() {
            @Override
            public void onResponse(Call<ResponseRombel> call, Response<ResponseRombel> response) {
                if (response.isSuccessful()) {
                    totalsiswa = response.body().getTotalsiswa() ;

                    List<DatasiswaItem> datasiswaItems = response.body().getDatasiswa();
                    boolean status = response.body().isStatus();

                    if (status) {
                        FilterClass adapterFilterClass = new FilterClass(ActivityClass.this, datasiswaItems, Preferences.getKey_TahunAjaran(getBaseContext()));
                        rvListSiswaFilter.setAdapter(adapterFilterClass);
                        kelas.setText(response.body().getKelas());
                        jurusan.setText(response.body().getJurusan());
                        wali.setText(response.body().getWaliKelas());
                        jumlah.setText(totalsiswa);
                        ketuakelas.setText(response.body().getKetuaKelas());

                    }
                    else {
                        Toast.makeText(ActivityClass.this,
                                response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(ActivityClass.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseRombel> call, Throwable t) {
                Toast.makeText(ActivityClass.this,t.getMessage(), Toast.LENGTH_SHORT).show();



            }
        });
    }

    private void showAllSiswa(String kodeTahunAjaran, String kodeKelas) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseInRombel> inRombelCall = apiServices.inRombel(kodeTahunAjaran, kodeKelas);

        inRombelCall.enqueue(new Callback<ResponseInRombel>() {
            @Override
            public void onResponse(Call<ResponseInRombel> call, Response<ResponseInRombel> response) {
                if (response.isSuccessful()) {
                    totalsiswa = response.body().getTotalsiswa();

                    List<SiswainrombelItem> siswainrombelItems = response.body().getSiswainrombel();
                    boolean status = response.body().isStatus();

                    if (status) {

                        InRombel adapterinRombel = new InRombel(ActivityClass.this, siswainrombelItems, kodeTahunAjaran);
                        rvListSiswaFilter.setAdapter(adapterinRombel);
                        kelas.setText(response.body().getKelas());
                        jurusan.setText(response.body().getJurusan());
                        wali.setText(response.body().getWaliKelas());
                        jumlah.setText(totalsiswa);
                        ketuakelas.setText(response.body().getKetuaKelas());

                    }
                    else {

                        Toast.makeText(ActivityClass.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseInRombel> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




}
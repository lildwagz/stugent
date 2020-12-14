package ei.eseptiyadi.aps.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.model.inrombel.ResponseDetailKelas;
import ei.eseptiyadi.aps.module.Preferences;
import ei.eseptiyadi.aps.network.ApiServices;
import ei.eseptiyadi.aps.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detail_data extends AppCompatActivity {

    TextView tv_total_siswa, tv_total_mapel, tv_ketua_kelas, tv_rpl, tv_tkj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        tv_total_siswa = (TextView) findViewById(R.id.total_siswa);
        tv_total_mapel = (TextView) findViewById(R.id.total_mapel);
        tv_ketua_kelas = (TextView) findViewById(R.id.ketua_kelas);
        tv_rpl = (TextView) findViewById(R.id.jurusan_rpl);
        tv_tkj = (TextView) findViewById(R.id.jurusan_tkj);


        String kodeTahunAjaran = Preferences.getKey_TahunAjaran(getBaseContext());
        String kodeKelas = Preferences.getKey_Kelas(getBaseContext());

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseDetailKelas> responseDetailSiswaCall = apiServices.detailsiswa(kodeTahunAjaran, kodeKelas);

        responseDetailSiswaCall.enqueue(new Callback<ResponseDetailKelas>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseDetailKelas> call, Response<ResponseDetailKelas> response) {
                if (response.isSuccessful()) {
                    String totalsiswa = response.body().getDetailKelas().getTotalSiswa() + " Siswa";

                    tv_total_siswa.setText(totalsiswa);
                    tv_total_mapel.setText(response.body().getDetailKelas().getTotalmapel() + " Mapel");
                    tv_ketua_kelas.setText(response.body().getDetailKelas().getKetuaKelas());
                    tv_rpl.setText(response.body().getDetailKelas().getTotalSiswarpl() + " Siswa");
                    tv_tkj.setText(response.body().getDetailKelas().getTotalSiswatkj() + " Siswa");
                    Toast.makeText(detail_data.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } else {
//                    Log.d("LOG","response"+response.errorBody());
                    Toast.makeText(detail_data.this, (CharSequence) response.errorBody(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseDetailKelas> call, Throwable t) {
                Toast.makeText(detail_data.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void back(View view) {
        if (getParentActivityIntent() == null) {
            onBackPressed();
        }


    }
}
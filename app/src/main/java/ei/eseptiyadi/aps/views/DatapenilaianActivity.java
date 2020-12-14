package ei.eseptiyadi.aps.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.adapter.DataPenilaianAdapter;
import ei.eseptiyadi.aps.adapter.InRombel;
import ei.eseptiyadi.aps.model.inrombel.DataMapelItem;
import ei.eseptiyadi.aps.model.inrombel.ResponseDataMapel;
import ei.eseptiyadi.aps.network.ApiServices;
import ei.eseptiyadi.aps.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatapenilaianActivity extends AppCompatActivity {

    TextView kelas,nisn,nama;
    RecyclerView rvdatapenilaian;
    String namaSiswa,NisSiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datapenilaian);

        nama = (TextView)findViewById(R.id.TVnama);
        kelas = (TextView)findViewById(R.id.Tvkelas1);
        nisn = (TextView)findViewById(R.id.TvNisn);
        rvdatapenilaian = (RecyclerView)findViewById(R.id.listdatapenilaian);

        rvdatapenilaian.setHasFixedSize(true);
        rvdatapenilaian.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Bundle getPackage = getIntent().getExtras();
        namaSiswa = getPackage.getString("NAMASISWA");
        NisSiswa = getPackage.getString("NISSWA");

        showDetailPenilaian();

    }

    private void showDetailPenilaian() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseDataMapel> responseDataMapelCall = apiServices.datamapel();

        responseDataMapelCall.enqueue(new Callback<ResponseDataMapel>() {
            @Override
            public void onResponse(Call<ResponseDataMapel> call, Response<ResponseDataMapel> response) {
                if(response.isSuccessful()){
                    List<DataMapelItem> dataMapelItems = response.body().getDataMapel();
                    if (response.body().isStatus()){
                        DataPenilaianAdapter dataPenilaianAdapter = new DataPenilaianAdapter(DatapenilaianActivity.this, dataMapelItems,namaSiswa,NisSiswa);
                        rvdatapenilaian.setAdapter(dataPenilaianAdapter);
                        nama.setText(namaSiswa);
                        nisn.setText(NisSiswa);
                        kelas.setText("XI");
                        Toast.makeText(DatapenilaianActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(DatapenilaianActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }

                }{
                    Toast.makeText(DatapenilaianActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseDataMapel> call, Throwable t) {
                Toast.makeText(DatapenilaianActivity.this,"error "+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}
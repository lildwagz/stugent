package ei.eseptiyadi.aps.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.model.addData.ResponseAddMapel;
import ei.eseptiyadi.aps.network.ApiServices;
import ei.eseptiyadi.aps.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class data_pelajaran extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[]kode_guru = {"prod-1", "prod-2","prod-3"};

    EditText to_kode_mapel,to_nama_mapel;
    Spinner sp_kodeGuru;
    String kodeGuru;
    ImageButton to_back;
    Button to_add;

    ArrayAdapter<CharSequence>adapterkodeGuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pelajaran);

        sp_kodeGuru = (Spinner) findViewById(R.id.spinnerKodeGuru);
        sp_kodeGuru.setOnItemSelectedListener(this);

        adapterkodeGuru = new ArrayAdapter(this,android.R.layout.simple_spinner_item,kode_guru);
        adapterkodeGuru.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_kodeGuru.setAdapter(adapterkodeGuru);

        to_kode_mapel = (EditText)findViewById(R.id.edt_kode_mapel);
        to_nama_mapel = (EditText)findViewById(R.id.edt_nama_mapel);

        to_back = (ImageButton)findViewById(R.id.goBack);
        to_add = (Button) findViewById(R.id.goAdd);

        to_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (to_kode_mapel.getText().toString().trim().isEmpty()) {
                    to_kode_mapel.setError("Please fill out this field");
                } else if (to_nama_mapel.getText().toString().trim().isEmpty()) {
                    to_nama_mapel.setError("Please fill out this field");
                } else {
                    String StringKodeMapel = to_kode_mapel.getText().toString();
                    String StringNamaMapel = to_nama_mapel.getText().toString();
                    modulMapel(StringKodeMapel, StringNamaMapel);

                }
            }
        });
    }

    public void modulMapel (String kodeMapel, String namaMapel) {
        final ProgressDialog dialog = ProgressDialog.show(data_pelajaran.this,"Progres is ongoing","please wait..");

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseAddMapel> responseAddMapelCall = apiServices.addMapel(kodeMapel, namaMapel);
        responseAddMapelCall.enqueue(new Callback<ResponseAddMapel>() {
            @Override
            public void onResponse(Call<ResponseAddMapel> call, Response<ResponseAddMapel> response) {
                if(response.isSuccessful()){
                    dialog.dismiss();
                    if (response.body().isStatus()){
                        Toast.makeText(data_pelajaran.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(data_pelajaran.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(data_pelajaran.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddMapel> call, Throwable t) {

            }
        });
    }

    public void kembali(View view) {
        if (getParentActivityIntent() == null) {
            onBackPressed();
        }
    }

    public void tambah_mapel(View view) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        switch (parent.getId()){
            case R.id.spinnerKodeGuru :
                kodeGuru = kode_guru[position];
                Toast.makeText(getApplicationContext(), kode_guru[position], Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
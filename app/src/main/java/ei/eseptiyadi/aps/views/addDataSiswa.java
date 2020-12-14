package ei.eseptiyadi.aps.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Intent;
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
import ei.eseptiyadi.aps.model.addData.ResponseAddDataSiswa;
import ei.eseptiyadi.aps.network.ApiServices;
import ei.eseptiyadi.aps.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addDataSiswa extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] jurusan = { "RPL","TKJ"};
    String[] menjabat= { "Ketua kelas","wakil kelas","anggota kelas"};


    EditText to_nis,to_nama_lenkap,to_email,to_username,to_password, to_periode, to_detail;
    Spinner spinnerJurusan, spinnerJabatan;
    ImageButton to_back;
    Button to_daftar;
    String StringJabatan,StringJurusan;
    ArrayAdapter<CharSequence> adapterJurusan;
    ArrayAdapter<CharSequence> adapterJabatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_siswa);

        spinnerJurusan = (Spinner) findViewById(R.id.spinnerJurusan);
        spinnerJabatan = (Spinner) findViewById(R.id.spinnerJabatan);

        spinnerJurusan.setOnItemSelectedListener(this);
        spinnerJabatan.setOnItemSelectedListener(this);

        adapterJurusan = new ArrayAdapter(this,android.R.layout.simple_spinner_item,jurusan);
        adapterJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJurusan.setAdapter(adapterJurusan);

        adapterJabatan = new ArrayAdapter(this,android.R.layout.simple_spinner_item,menjabat);
        adapterJabatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJabatan.setAdapter(adapterJabatan);

        to_nis = (EditText)findViewById(R.id.edt_nis);
        to_nama_lenkap = (EditText)findViewById(R.id.edt_nama_lengkap);
        to_email = (EditText)findViewById(R.id.edt_email);
        to_username = (EditText)findViewById(R.id.edt_username);
        to_password = (EditText)findViewById(R.id.edt_password);
        to_periode = (EditText)findViewById(R.id.edt_periode);
        to_detail = (EditText)findViewById(R.id.edt_Detail);

        to_back = (ImageButton) findViewById(R.id.btn_back);
        to_daftar = (Button) findViewById(R.id.btn_daftar);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.spinnerJabatan :
                StringJabatan = menjabat[position];
                Toast.makeText(getApplicationContext(),menjabat[position] , Toast.LENGTH_LONG).show();
                break;
            case R.id.spinnerJurusan :
                StringJurusan = jurusan[position];
                Toast.makeText(getApplicationContext(),jurusan[position] , Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void daftarsiswa(View view) {

        if (to_nis.getText().toString().trim().isEmpty()) {
            to_nis.setError("Please fill out this field");
        } else if (to_nama_lenkap.getText().toString().trim().isEmpty()) {
            to_nama_lenkap.setError("Please fill out this field");
        } else if (to_email.getText().toString().trim().isEmpty()) {
            to_email.setError("Please fill out this field");
        } else if (to_username.getText().toString().trim().isEmpty()) {
            to_username.setError("Please fill out this field");
        } else if (to_password.getText().toString().trim().isEmpty()) {
            to_password.setError("Please fill out this field");
        }else if (to_periode.getText().toString().trim().isEmpty()) {
            to_periode.setError("Please fill out this field");
        }else if (to_detail.getText().toString().trim().isEmpty()) {
            to_detail.setError("Please fill out this field");
        } else {
            String StringNis = to_nis.getText().toString();
            String StringNamaLengkap = to_nama_lenkap.getText().toString();
            String StringEmail = to_email.getText().toString();
            String StringUsername = to_username.getText().toString();
            String StringPassword = to_password.getText().toString();
            String StringPeriode = to_periode.getText().toString();
            String StringDetail = to_detail.getText().toString();
            modulregis(StringNis,StringNamaLengkap,StringEmail,StringUsername,StringPassword, StringJurusan, StringJabatan, StringPeriode, StringDetail);
//            Toast.makeText(getApplicationContext(),StringJurusan , Toast.LENGTH_LONG).show();

        }

    }
    public void modulregis(String nis,String namalengkap,String email ,String username,String password, String jurusan, String anggota, String periode, String details){
        final ProgressDialog dialog = ProgressDialog.show(addDataSiswa.this,"Progres is ongoing","please wait..");

        ApiServices apiServices = RetrofitClient.getInstance();
//        Call<ResponseAddDataSiswa> responseAddDataSiswaCall = apiServices.addDataSiswa("29928739228","bowos","bowo@bambung.com","bowo super","password", "RPL", "anggota kelas", "-", "details","PAS-1-2020-XI","PKK","PROD-01");
        Call<ResponseAddDataSiswa> responseAddDataSiswaCall = apiServices.addDataSiswa(nis,namalengkap,email,username,password, jurusan, anggota, periode, details,"PAS-1-2020-XI","PBO","PROD-01");

        responseAddDataSiswaCall.enqueue(new Callback<ResponseAddDataSiswa>() {
            @Override
            public void onResponse(Call<ResponseAddDataSiswa> call, Response<ResponseAddDataSiswa> response) {
                if(response.isSuccessful()){
                    if (response.body().isStatus()){
                        dialog.dismiss();
                        Toast.makeText(addDataSiswa.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        Intent pindah = new Intent(addDataSiswa.this, dasboard.class);
                        startActivity(pindah);
                    }else {
                        Toast.makeText(addDataSiswa.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        Intent pindah = new Intent(addDataSiswa.this, dasboard.class);
                        startActivity(pindah);
                    }
                }else {
                    Toast.makeText(addDataSiswa.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    Intent pindah = new Intent(addDataSiswa.this, dasboard.class);
                    startActivity(pindah);
                }
            }

            @Override
            public void onFailure(Call<ResponseAddDataSiswa> call, Throwable t) {
                Toast.makeText(addDataSiswa.this,"error :"+t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

    }

    public void back(View view) {
        Intent pindah = new Intent(addDataSiswa.this, dasboard.class);
        startActivity(pindah);
        finish();
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
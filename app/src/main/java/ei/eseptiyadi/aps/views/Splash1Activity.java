package ei.eseptiyadi.aps.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import ei.eseptiyadi.aps.R;

public class Splash1Activity extends AppCompatActivity {
    private int waktu_loading=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent dasboard=new Intent(Splash1Activity.this,dasboard.class);
                startActivity(dasboard);
                finish();

            }
        },waktu_loading);
    }
}



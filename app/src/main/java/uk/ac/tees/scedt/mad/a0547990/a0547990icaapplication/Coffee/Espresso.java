package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.Coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.MainActivity;
import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.R;

public class Espresso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espresso);
        Button ok = (Button) this.findViewById(R.id.back_button);
        ok.setOnClickListener((View.OnClickListener) new ButtonListener());
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Espresso.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
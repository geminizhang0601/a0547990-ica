package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Americano extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.americano);
            Button ok = (Button) this.findViewById(R.id.back_button);
            ok.setOnClickListener((View.OnClickListener) new ButtonListener());
        }
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent=new Intent(Americano.this,MainActivity.class);//设置切换对应activity
            startActivity(intent);//开始切换
        }
    }
}

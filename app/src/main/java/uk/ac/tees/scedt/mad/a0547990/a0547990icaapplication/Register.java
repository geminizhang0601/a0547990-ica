package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.javabean.User;

public class Register extends AppCompatActivity {
    private EditText EditText_Account1, EditText_Password1;
    private MYsqliteopenhelper mYsqliteopenhelper1;
    private Button Button_Register1;


    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.register);
        mYsqliteopenhelper1=new MYsqliteopenhelper(this);
        find();

    }
    private void find(){
        Button_Register1=findViewById(R.id.Button_Register1);
        EditText_Account1=findViewById(R.id.EditText_Account1);
        EditText_Password1=findViewById(R.id.EditText_Password1);
    }
    public void Re(View view){
        String account=EditText_Account1.getText().toString();
        String password=EditText_Password1.getText().toString();
        User u=new User(account,password);
        long l =mYsqliteopenhelper1.register(u);
        if (l !=-1){
            Toast.makeText(this,"Register Successfully!",Toast.LENGTH_SHORT).show();
            Intent intent3=new Intent(this,LoginActivity.class);
            startActivity(intent3);
        }
        else{
            Toast.makeText(this,"No Register",Toast.LENGTH_SHORT).show();
        }

    }
}

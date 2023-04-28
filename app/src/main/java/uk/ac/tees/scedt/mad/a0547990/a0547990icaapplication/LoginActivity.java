package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText EditText_Account, EditText_Password;
    private CheckBox checkBox;
    private Button Button_Login;
    private EditText loginactivityPhonecodes;
    private ImageView loginactivityShowcode;
    private String realCode;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_interface);
            EditText_Account = findViewById(R.id.EditText_Account);
            EditText_Password = findViewById(R.id.EditText_Password);
            checkBox = findViewById(R.id.checkbox);
            Button_Login = findViewById(R.id.Button_Login);
            loginactivityPhonecodes = findViewById(R.id.loginactivity_phoneCodes);
            loginactivityShowcode = findViewById(R.id.loginactivity_showCode);
            loginactivityShowcode.setImageBitmap(code.getInstance().createBitmap());
            realCode = code.getInstance().getCode().toLowerCase(); //将验证码用图片的形式显示出来


        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            String username = sharedPreferences.getString("Account", "");
            String password = sharedPreferences.getString("Password", "");

            if (!username.equals("") && !password.equals("")) {
                EditText_Account.setText(username);
                EditText_Password.setText(password);
                checkBox.setChecked(true);
            }

            Button_Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String account = EditText_Account.getText().toString();
                    String password = EditText_Password.getText().toString();

                    String phoneCode = loginactivityPhonecodes.getText().toString().toLowerCase();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if (account.equals("a0547990") && password.equals("a0547990")) {
                        if(phoneCode.length()==0){
                            Toast.makeText(LoginActivity.this,"can not be empty",Toast.LENGTH_SHORT).show();}
                        else if (!phoneCode.equals(realCode)){
                            Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();}
                        else if (checkBox.isChecked()) {
                            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Account", account);
                            editor.putString("Password", password);
                            editor.apply();
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Successful login！", Toast.LENGTH_LONG).show();}
                    } else {
                        Toast.makeText(LoginActivity.this, "User name or password is incorrect！", Toast.LENGTH_LONG).show();
                    }

                }
            });
        loginactivityShowcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginactivityShowcode.setImageBitmap(code.getInstance().createBitmap());
                realCode = code.getInstance().getCode().toLowerCase(); //将验证码用图片的形式显示出来
            }
        });

    }

}


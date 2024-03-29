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

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.javabean.User;

public class LoginActivity extends AppCompatActivity {
    private EditText EditText_Account, EditText_Password;
    private MYsqliteopenhelper mYsqliteopenhelper;
    private CheckBox checkBox;
    private Button Button_Login,Button_Register,Button_Log;
    private EditText loginactivityPhonecodes;
    private ImageView loginactivityShowcode;
    private String realCode;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_interface);
            mYsqliteopenhelper=new MYsqliteopenhelper(this);
            EditText_Account = findViewById(R.id.EditText_Account);
            EditText_Password = findViewById(R.id.EditText_Password);
            checkBox = findViewById(R.id.checkbox);
            Button_Login = findViewById(R.id.Button_Login);
            Button_Register= findViewById(R.id.Button_Register);
            Button_Log=findViewById(R.id.Button_Log);
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

            Button_Register.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    Intent intent1 = new Intent(LoginActivity.this, Register.class);
                    startActivity(intent1);
                }
            });
            Button_Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String account = EditText_Account.getText().toString();
                    String password = EditText_Password.getText().toString();

                    String phoneCode = loginactivityPhonecodes.getText().toString().toLowerCase();
                    boolean login = mYsqliteopenhelper.login(account, password);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if (login) {
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

        Button_Log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                SignLaunch.launch(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                //setLogo(R.drawable)图标
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.EmailBuilder().build(),
                                        new AuthUI.IdpConfig.PhoneBuilder().setDefaultCountryIso("gb").build(),
                                        new AuthUI.IdpConfig.GoogleBuilder().build()

                                ))
                                .build()

                );
            }
        });

    }
    private final ActivityResultLauncher<Intent> SignLaunch=registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result){
        IdpResponse response=result.getIdpResponse();
        if (result.getResultCode()==RESULT_OK){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }else{}
    }



}



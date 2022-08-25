package com.erbek.remotepatientm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterScreen extends AppCompatActivity {
    private String gelenuser;
    private TextView user;
    private EditText userid;
    private EditText password;
    private Button register;
    private String usermailstring, passstring, namestring, agestring;
    private FirebaseAuth mauth;
    private Integer check=0;
    private CheckBox check1;
    private EditText name;
    private DatabaseReference mReference;
    private HashMap<String, Object> userinfo;
    private EditText namesurname,age;
    private FirebaseUser mUser;
    private RadioButton doctor,patient;
    private String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        Intent gelenintent = getIntent();

        userid = (EditText) findViewById(R.id.edit1);
        password = (EditText) findViewById(R.id.edit2);
        register = (Button) findViewById(R.id.button2);

        check1 = (CheckBox) findViewById(R.id.checkBox2);
        mauth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        namesurname = (EditText) findViewById(R.id.edit3);
        age = (EditText)findViewById(R.id.edit4);
        doctor = (RadioButton) findViewById(R.id.radioButton5);
        patient = (RadioButton) findViewById(R.id.radioButton6);




        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    check = 1;
                else
                    check=0;
            }
        });

        doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    role="doctor";
            }
        });
        patient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    role="patient";
            }
        });


            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    usermailstring = userid.getText().toString();
                    passstring = password.getText().toString();
                    namestring = namesurname.getText().toString();
                    agestring = age.getText().toString();

                    if(check==1){
                        if (!TextUtils.isEmpty(usermailstring) && !TextUtils.isEmpty(passstring) && !TextUtils.isEmpty(namestring)) {
                            mauth.createUserWithEmailAndPassword(usermailstring, passstring)
                                    .addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                mUser = mauth.getCurrentUser();
                                                userinfo = new HashMap<>();
                                                userinfo.put("mail",usermailstring);
                                                userinfo.put("Password",passstring);
                                                userinfo.put("Name Surname", namestring);
                                                userinfo.put("Age", agestring);
                                                userinfo.put("User ID", mUser.getUid());
                                                userinfo.put("Role",role);

                                                mReference.child("Users").child(mUser.getUid())
                                                        .setValue(userinfo)
                                                        .addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                            }
                                                        });

                                                Toast.makeText(RegisterScreen.this, "Success!", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(RegisterScreen.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                        }
                        else {
                            Toast.makeText(RegisterScreen.this, "E-mail and password can not empty!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(RegisterScreen.this,"Confirm the message!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

    }
}
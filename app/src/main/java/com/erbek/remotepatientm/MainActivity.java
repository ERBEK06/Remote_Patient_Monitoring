package com.erbek.remotepatientm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.lang.Thread;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private EditText userid, pass;
    private Button giris, register, enab;
    private CheckBox logged;
    private RadioButton doctor,patient;
    private EditText userid1;
    private EditText password;
    private FirebaseAuth mauth;
    private FirebaseUser mUser;
    private String usermailstring, passstring;
    private DatabaseReference mReference;
    private String rolee;
    private Button button22;
    String usernamesurname;
    int role;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userid = (EditText)findViewById(R.id.edit22);
        pass = (EditText)findViewById(R.id.edit2);
        giris = (Button)findViewById(R.id.button1);
        register = (Button)findViewById(R.id.button2);
        enab = (Button)findViewById(R.id.button3);
        logged = (CheckBox)findViewById(R.id.checkBox);
        doctor = (RadioButton)findViewById(R.id.doctorbutton);
        patient = (RadioButton)findViewById(R.id.patientbutton);
        userid1 = (EditText)findViewById(R.id.edit22);
        password = (EditText)findViewById(R.id.edit2);

        mauth= FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();




        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usermailstring = userid.getText().toString();

                passstring = password.getText().toString();
                if(!TextUtils.isEmpty(usermailstring) && !TextUtils.isEmpty(passstring))
                {
                        mauth.signInWithEmailAndPassword(usermailstring,passstring)
                                .addOnSuccessListener(MainActivity.this, new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {


                                    mUser=mauth.getCurrentUser();
                                    mReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid());
                                    mReference.addValueEventListener(new ValueEventListener() {



                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            int i=0;
                                            String abc[] = new String[6];
                                            for(DataSnapshot snp : snapshot.getChildren()){
                                                abc[i]= snp.getValue().toString();
                                                usernamesurname =abc[1];
                                                rolee = abc[3]; //patient or doctor
                                                i++;
                                            }

                                        }






                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                if (role == 1) {

                                        Intent intent = new Intent(MainActivity.this, DoctorHomepage.class);
                                        intent.putExtra("doctorname", usernamesurname);
                                        startActivity(intent);


                                } else if (role == 2) {

                                    Intent intent = new Intent(MainActivity.this, PatientHomepage.class);
                                    intent.putExtra("patientname", usernamesurname);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(MainActivity.this, "Choose patient or doctor!", Toast.LENGTH_SHORT).show();
                                }

                                    }
                                }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                }

                else{
                    Toast.makeText(MainActivity.this,"E-mail and password can not empty!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    role=1;
            }
        });
        patient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    role=2;
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegisterScreen.class);
                intent.putExtra("userid", usernamesurname);
                startActivity(intent);
            }
        });


    }


}
package com.erbek.remotepatientm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class changepass extends AppCompatActivity {
    private Button change;
    private FirebaseUser mUser;
    private FirebaseAuth mauth;
    private HashMap<String, Object> userinfo;
    private DatabaseReference mReference;
    private EditText edit1,edit2,edit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        change = (Button) findViewById(R.id.button12);
        mauth = FirebaseAuth.getInstance();

        edit2 =  (EditText)findViewById(R.id.editText4);


        mUser = mauth.getCurrentUser();
        userinfo = new HashMap<>();
        change.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                String pass = edit2.getText().toString();


                mReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid());
                mReference.updateChildren(userinfo)
                        .addOnCompleteListener(changepass.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                userinfo.put("Password", pass);
                            if(task.isSuccessful()){
                                Toast.makeText(changepass.this, "Password Changed!", Toast.LENGTH_SHORT).show();
                            }

                            }
                        });


            }
        });


}
}

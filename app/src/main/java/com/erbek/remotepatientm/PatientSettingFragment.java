package com.erbek.remotepatientm;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class PatientSettingFragment extends Fragment {

  private TextView patientname;
  String userid;
  private Button logout,map,mail,changepass;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_setting, container, false);
        logout= (Button) v.findViewById(R.id.button10);
        map = (Button) v.findViewById(R.id.button8);
        mail = (Button) v.findViewById(R.id.button9);
        changepass = (Button) v.findViewById(R.id.button7);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Googlemap.class);

                startActivity(intent);
            }
        });

        patientname = (TextView)  v.findViewById(R.id.textView7);

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), changepass.class);
                startActivity(intent);

            }
        });




        Bundle bundle = getArguments();


        userid = bundle.getString("key");
        patientname.setText(userid);



        return v;
    }
    private void sendMail() {
        String recipientList = "osmanbahadirerbek@gmail.com";
        String[] recipients = recipientList.split(",");
        String subject = "HELLO";

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);


        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
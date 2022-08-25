package com.erbek.remotepatientm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;


public class PatientTimeFragment extends Fragment {

    TextView txt_saat_sec;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    TimePickerDialog timePickerDialog;
    int saat, dakika;
    private TextView  userid;
    private String userid1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_time, container, false);

        userid = (TextView)  v.findViewById(R.id.textView6);



        txt_saat_sec = (TextView) v.findViewById(R.id.txt_saat_sec);

        txt_saat_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                saat = calendar.get(calendar.HOUR_OF_DAY);
                dakika = calendar.get(calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getContext(), timeSetListener, saat, dakika,true);
                timePickerDialog.show();
            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                txt_saat_sec.setText(i+" : "+i1);
            }
        };


        Bundle bundle = getArguments();


        userid1 = bundle.getString("key");
        userid.setText(userid1);





        return v;
    }


}
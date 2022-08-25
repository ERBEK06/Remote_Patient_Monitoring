package com.erbek.remotepatientm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import org.w3c.dom.Text;


public class DoctorTimeFragment extends Fragment {

    private Spinner denemespinner;
    private ArrayAdapter<CharSequence> denemeadapter;
    private TextView patientname;
    TextView txt_saat_sec;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    TimePickerDialog timePickerDialog;
    int saat, dakika;
    private String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctor_time, container, false);

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


        patientname = (TextView) v.findViewById(R.id.textView6);
        denemespinner=(Spinner) v.findViewById(R.id.spinner);
        denemeadapter = ArrayAdapter.createFromResource(getContext(),R.array.patientlist, android.R.layout.simple_spinner_item);
        denemeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        denemespinner.setAdapter(denemeadapter);

        denemespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                patientname.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                patientname.setText(adapterView.getItemAtPosition(0).toString());
            }
        });




        return v;
    }
}
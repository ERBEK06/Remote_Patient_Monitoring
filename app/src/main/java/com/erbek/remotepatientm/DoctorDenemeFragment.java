package com.erbek.remotepatientm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class DoctorDenemeFragment extends Fragment {
    private Spinner denemespinner;
    private ArrayAdapter<CharSequence> denemeadapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_doctor_deneme, container, false);

        denemespinner=(Spinner) v.findViewById(R.id.spinner2);
        denemeadapter = ArrayAdapter.createFromResource(getContext(),R.array.patientlist, android.R.layout.simple_spinner_item);
        denemeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        denemespinner.setAdapter(denemeadapter);



        return v;

    }
}
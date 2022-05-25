package com.example.foodwaste.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodwaste.DBHelper;
import com.example.foodwaste.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    DBHelper DB;
    private TextView prouser;
    private TextView proemail;
    private TextView proph;
    private TextView proacctype;
    private TextView proadd;
    public Profile() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DBHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        prouser = view.findViewById(R.id.profile_user);
        proemail = view.findViewById(R.id.profile_email);
        proph = view.findViewById(R.id.profile_phno);
        proacctype = view.findViewById(R.id.profile_type);
        proadd = view.findViewById(R.id.profile_address);
        prouser.setText(DBHelper.checkuser);
        proemail.setText(DBHelper.checkemail);
        proph.setText(DBHelper.checkphno);
        proacctype.setText(DBHelper.checktype);
        proadd.setText(DBHelper.checkaddress);
        return view;
    }
}
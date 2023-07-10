package com.example.eco_bucket;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class OrderdetailsFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String name, course ,email, purl;
    public OrderdetailsFragment() {
    }

    public OrderdetailsFragment(String name,String course , String email,String purl) {
        this.name = name;
        this.course = course;
        this.email = email;
        this.purl = purl;

    }


    public static detailsfragment newInstance(String param1, String param2) {
        detailsfragment fragment = new detailsfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderdetails, container, false);

        ImageView image = view.findViewById(R.id.OrderedImage);
        TextView ProductName = view.findViewById(R.id.ProductName);
        TextView amount = view.findViewById(R.id.Amount);
        TextView emailholder = view.findViewById(R.id.PaymentMethod);


        ProductName.setText(name);
        amount.setText(course);
        emailholder.setText(email);
        Glide.with(getContext()).load(purl).into(image);

        return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.recvieww
                        ,new recfragment())
                .addToBackStack(null).commit();
    }
}
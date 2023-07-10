package com.example.eco_bucket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class detailsfragment extends Fragment {

    Button order ;
    public static final String NEW = "neww_class_app";
    public static final String MEW = "ne_class_app";
    public static final String css = "KYa_hi_kar_sakte_hai_ham";
    public static final String mss = "Kuch_nahi_kar_sakte";
    public static final String gss = "Kuch_waa_nahi_kar_sakte";



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String name, course ,email, purl;
    public detailsfragment() {
    }

    public detailsfragment(String name,String course , String email,String purl) {
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

        View view = inflater.inflate(R.layout.fragment_detailsfragment, container, false);

        // button for order
        order = view.findViewById(R.id.Order);



        ImageView image = view.findViewById(R.id.OrderedImage);
        TextView nameholder = view.findViewById(R.id.ProductName);
        TextView courseholder = view.findViewById(R.id.Amount);
        //TextView emailholder = view.findViewById(R.id.PaymentMethod);

        ImageView plus = view.findViewById(R.id.add);
        ImageView minus = view.findViewById(R.id.minus);

        EditText num = view.findViewById(R.id.editTextNumber);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = num.getText().toString();
                int b = Integer.parseInt(a);
                num.setText(""+(b+1));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = num.getText().toString();
                int b = Integer.parseInt(a);

                if (a == null){
                    num.setText("0");
                }
                else if (b>0){
                    num.setText(""+(b-1));
                }
                else{
                    num.setText(""+(0));
                }
            }
        });

        nameholder.setText(name);
        courseholder.setText("Price :  "+ course + " rs");
        //emailholder.setText(email);
        Glide.with(getContext()).load(purl).into(image);


      /// order button

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aa = num.getText().toString();
                int bb = Integer.parseInt(aa);
                int mm = Integer.parseInt(course);

                Intent intent = new Intent(getActivity(),Order_details_payment.class);
                int ammount = mm*bb;
                String amm = Integer.toString(ammount);
                intent.putExtra( NEW ,amm);
                intent.putExtra(MEW,aa);
                intent.putExtra(css,name);
                intent.putExtra(mss,email);
                intent.putExtra(gss,purl);
                startActivity(intent);

                Toast.makeText(getActivity(), " Toward the Payment ", Toast.LENGTH_SHORT).show();

            }
        });

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
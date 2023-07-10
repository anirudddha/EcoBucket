package com.example.eco_bucket;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MyOrderDetails_Preview extends Fragment {

    FirebaseAuth mAuth;
    FirebaseUser currentuser;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    String  Pname,Paddress,Ppincode,PphoneNumber,date,PaymentType,AmountPaid,Type,SubType,imageUrl,time;
    public MyOrderDetails_Preview() {

    }

    public MyOrderDetails_Preview(String Pname,String Paddress, String Ppincode,
                                  String PphoneNumber,String date,String PaymentType,
                                  String AmountPaid,String Type,String SubType,String imageUrl,String time) {

        this.Pname =Pname;
        this.Paddress =Paddress;
        this.Ppincode =Ppincode;
        this.PphoneNumber =PphoneNumber;
        this.date =date;
        this.PaymentType =PaymentType;
        this.AmountPaid =AmountPaid;
        this.Type =Type;
        this.SubType =SubType;
        this.imageUrl = imageUrl;
        this.time = time;


    }


    public static MyOrderDetails_Preview newInstance(String param1, String param2) {
        MyOrderDetails_Preview fragment = new MyOrderDetails_Preview();
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


        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();

        String emmail = currentuser.getEmail();
        String usernamme = emmail.replace(".com","");

        View view = inflater.inflate(R.layout.fragment_my_order_details__preview, container, false);

        ImageView image = view.findViewById(R.id.OrderrImage);
        TextView ProductName = view.findViewById(R.id.Type);
        TextView Subttype = view.findViewById(R.id.Subttype);
        TextView amountt = view.findViewById(R.id.amountt);
        TextView OrderTime = view.findViewById(R.id.OrderTime);
        TextView NName = view.findViewById(R.id.NName);
        TextView adddress = view.findViewById(R.id.adddress);
        TextView ppno = view.findViewById(R.id.ppno);

        Button edit = view.findViewById(R.id.editInfo);
        Button cancle = view.findViewById(R.id.cancleeOrder);



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(ppno.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogue_reourse_file))
                        .setExpanded(true,1200)
                        .create();


                View myview = dialogPlus.getHolderView();
                EditText editname = myview.findViewById(R.id.editname);
                EditText editaddress = myview.findViewById(R.id.editAddress);
                EditText editPNo = myview.findViewById(R.id.editpNo);
                Button proceed = myview.findViewById(R.id.Proceed);

                editname.setText(Pname);
                editaddress.setText(Paddress);
                editPNo.setText(PphoneNumber);

                dialogPlus.show();

                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("pname",editname.getText().toString());
                        map.put("paddress",editaddress.getText().toString());
                        map.put("pphoneNumber",editPNo.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("UserOrder")
                                .child(usernamme).child(date+"and"+time)
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "fial to update", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });


            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Order Cancel");
                builder.setMessage("You want to cancel order ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("diliveryStatus","Order Cancled");

                        FirebaseDatabase.getInstance().getReference().child("UserOrder")
                                .child(usernamme).child(date+"and"+time)
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void unused) {
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "fial to update", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });


        ProductName.setText(Type);
        Subttype.setText(SubType);
        amountt.setText(AmountPaid);
        OrderTime.setText(date);
        NName.setText(Pname);
        adddress.setText(Paddress);
        ppno.setText(PphoneNumber);
        Glide.with(getContext()).load(imageUrl).into(image);





        return view;
    }

}
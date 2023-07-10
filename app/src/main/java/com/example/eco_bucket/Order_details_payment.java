package com.example.eco_bucket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Order_details_payment extends AppCompatActivity implements PaymentResultListener{

    private static int DELAY_TIME = 4000;
    LottieAnimationView lottiee;
    ImageView iimg;
    TextView amount , ordersucces;
    EditText personName,personAdress,personPincode,personPhoneNumber;
    Button pay , Pod;

    FirebaseAuth mAuth;
    FirebaseUser currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_payment);

        lottiee = findViewById(R.id.lotttie);
        lottiee.setVisibility(View.INVISIBLE);
        iimg = findViewById(R.id.iimg);
        iimg.setVisibility(View.INVISIBLE);
        ordersucces = findViewById(R.id.ordersuccesful);
        ordersucces.setVisibility(View.INVISIBLE);

        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();

        //Testing


        amount = findViewById(R.id.displayAmout);
        Intent intent = getIntent();
        String paise = intent.getStringExtra(detailsfragment.NEW);
        int ammo = Integer.parseInt(paise);
        amount.setText(" Total amount : "+ ammo );


        amount = findViewById(R.id.displayAmout);
        pay = findViewById(R.id.Payment);
        Pod = findViewById(R.id.POD);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakePayment();
            }
        });



    }
    private void MakePayment(){

        Intent intent = getIntent();
        String paise = intent.getStringExtra(detailsfragment.NEW);
        int ammo = Integer.parseInt(paise);
        int ammount_for_razorpay = ammo*100;

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_0Bq2RpZuCj2qk5");

        //checkout.setImage(R.drawable.logo);

        String emaill = currentuser.getEmail();
        String pno = currentuser.getPhoneNumber();
        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", " ECOBUCKET ");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", ammount_for_razorpay);
            options.put("prefill.email", emaill);
            options.put("prefill.contact",pno);
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        }
        catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }


    }

    @Override
    public void onPaymentSuccess(String s) {
            Uploading();
            Toast.makeText(Order_details_payment.this, "Payment succesful", Toast.LENGTH_SHORT).show();
            lottiee.setAnimation(R.raw.succesful);
            lottiee.setVisibility(View.VISIBLE);
            iimg.setImageResource(R.drawable.whitecolor);
            iimg.setVisibility(View.VISIBLE);
            ordersucces.setText("Your Order Has Been Placed Succesfully");
            ordersucces.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Drawer.class));
                finish();
            }
        },DELAY_TIME);

    }




    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(Order_details_payment.this, "Unsuccesful :"+ s, Toast.LENGTH_SHORT).show(); }

    public void Pod(View view){
            PODorder();
            Toast.makeText(getApplicationContext(), "Your Order Placed Succesfully", Toast.LENGTH_SHORT).show();

        lottiee.setAnimation(R.raw.succesful);
        lottiee.setVisibility(View.VISIBLE);
        iimg.setImageResource(R.drawable.whitecolor);
        iimg.setVisibility(View.VISIBLE);
        ordersucces.setText("Your Order Has Been Placed Succesfully");
        ordersucces.setVisibility(View.VISIBLE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Drawer.class));
                finish();
            }
        },DELAY_TIME);
    }


    private void Uploading() {

        personName = findViewById(R.id.PersonName);
        personAdress = findViewById(R.id.PersonAddress);
        personPincode = findViewById(R.id.PersonPincode);
        personPhoneNumber = findViewById(R.id.PersonPhoneNumber);

        String Pname = personName.getText().toString();
        String Paddress = personAdress.getText().toString();
        String Ppincode = personPincode.getText().toString();
        String PphoneNumber = personPhoneNumber.getText().toString();

        Date dateandtime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh_mm_ss", Locale.getDefault());

        String time = timeFormat.format(dateandtime);
        String date = dateFormat.format(dateandtime);

        String PaymentType = "Online_Payment";

        Intent intent = getIntent();
        String NumberOfItems = intent.getStringExtra(detailsfragment.MEW);
        String AmountPaid = intent.getStringExtra(detailsfragment.NEW);
        String Type = intent.getStringExtra(detailsfragment.mss);
        String SubType = intent.getStringExtra(detailsfragment.css);
        String imageUrl = intent.getStringExtra(detailsfragment.gss);

        String DiliveryStatus = "Dilivery soon";

        String email = currentuser.getEmail();
        String username = email.replace(".com","");
        String phoneNo = currentuser.getPhoneNumber();

        UploadingDataHolder DH = new UploadingDataHolder(Pname,Paddress,Ppincode,PphoneNumber,
                time,date,PaymentType,NumberOfItems,AmountPaid,Type,SubType,imageUrl,DiliveryStatus);


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference("UserOrder");
        node.child(username).child(date+"and"+time).setValue(DH);

        UploadingDataHolder Dm = new UploadingDataHolder(Pname,username,phoneNo);

        FirebaseDatabase dc = FirebaseDatabase.getInstance();
        DatabaseReference nodee = dc.getReference("Orders");
        nodee.child(date+"and"+time).setValue(Dm);





    }
    private void PODorder() {

        personName = findViewById(R.id.PersonName);
        personAdress = findViewById(R.id.PersonAddress);
        personPincode = findViewById(R.id.PersonPincode);
        personPhoneNumber = findViewById(R.id.PersonPhoneNumber);

        String Pname = personName.getText().toString();
        String Paddress = personAdress.getText().toString();
        String Ppincode = personPincode.getText().toString();
        String PphoneNumber = personPhoneNumber.getText().toString();

        Date dateandtime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh_mm_ss", Locale.getDefault());

        String time = timeFormat.format(dateandtime);
        String date = dateFormat.format(dateandtime);

        String PaymentType = "Pay on Dilivery";

        Intent intent = getIntent();
        String NumberOfItems = intent.getStringExtra(detailsfragment.MEW);
        String AmountPaid = "Not Paid";
        String Type = intent.getStringExtra(detailsfragment.mss);
        String SubType = intent.getStringExtra(detailsfragment.css);
        String imageUrl = intent.getStringExtra(detailsfragment.gss);

        String DiliveryStatus = "Dilivery soon";

        String email = currentuser.getEmail();
        String username = email.replace(".com","");
        String phoneNo = currentuser.getPhoneNumber();


        UploadingDataHolder DH = new UploadingDataHolder(Pname,Paddress,Ppincode,PphoneNumber,
                time,date,PaymentType,NumberOfItems,AmountPaid,Type,SubType,imageUrl,DiliveryStatus);

        UploadingDataHolder Dm = new UploadingDataHolder(Pname,username,phoneNo);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference("UserOrder");
        node.child(username).child(date+"and"+time).setValue(DH);

        FirebaseDatabase dc = FirebaseDatabase.getInstance();
        DatabaseReference nodee = dc.getReference("Orders");
        nodee.child(date+"and"+time).setValue(Dm);

    }
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, Drawer.class));
        finish();
    }


}
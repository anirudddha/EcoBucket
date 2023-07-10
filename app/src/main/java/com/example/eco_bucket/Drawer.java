package com.example.eco_bucket;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eco_bucket.databinding.ActivityDrawerBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Drawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDrawerBinding binding;

    FirebaseAuth mAuth;
    FirebaseUser currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//        itemView = findViewById(R.id.action_settings);

        binding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();

        setSupportActionBar(binding.appBarDrawer.toolbar);
        binding.appBarDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogPlus dialogPlus = DialogPlus.newDialog(Drawer.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.dialogue_for_message))
                        .setExpanded(true,1200)
                        .create();


                View myview = dialogPlus.getHolderView();
                Button feedback = myview.findViewById(R.id.responceSubmitBtn);
                EditText feedbackk = myview.findViewById(R.id.feedbackk);
                dialogPlus.show();
                feedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String feedback = feedbackk.getText().toString();
                        String emmail = currentuser.getEmail();
                        String Nname = currentuser.getDisplayName();
                        String usernamme = emmail.replace(".com","");

                        Date dateandtime = Calendar.getInstance().getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
                        SimpleDateFormat timeFormat = new SimpleDateFormat("hh_mm_ss", Locale.getDefault());

                        String time = timeFormat.format(dateandtime);
                        String date = dateFormat.format(dateandtime);

                        feedback_dataHolder DH = new feedback_dataHolder(Nname,feedback);

                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference node = db.getReference("feedback");
                        node.child(usernamme).child(date+"and"+time).setValue(DH);

                        Toast.makeText(Drawer.this, "Feedback succesfully send", Toast.LENGTH_SHORT).show();

                        dialogPlus.dismiss();
                    }
                });
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        updatNavbar();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void updatNavbar(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView name = headerView.findViewById(R.id.profileName);
        TextView email = headerView.findViewById(R.id.profileemail);
        ImageView image = headerView.findViewById(R.id.profileImage);
        ImageView logout = headerView.findViewById(R.id.logoutimage);

        name.setText(currentuser.getDisplayName());
        email.setText(currentuser.getEmail());
        Glide.with(this).load(currentuser.getPhotoUrl()).into(image);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Drawer.this);
                builder.setTitle("LogOut");
                builder.setMessage("You want to logout");

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),SignIn.class));
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

    }


    public void privacy(View v){
        //startActivity(new Intent(getApplicationContext(),PrivacyPolicy.class));
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://razorpay.com/sample-application/"));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request."
                    + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public void TermsCondition(View v){
        //startActivity(new Intent(getApplicationContext(),TermsAndConditions.class));
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://razorpay.com/terms/"));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request."
                    + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }



}
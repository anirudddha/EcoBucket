package com.example.eco_bucket.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eco_bucket.MyOrdersDetailsAdapter;
import com.example.eco_bucket.R;
import com.example.eco_bucket.UploadingDataHolder;
import com.example.eco_bucket.databinding.FragmentGalleryBinding;
import com.example.eco_bucket.model;
import com.example.eco_bucket.myadapter;
import com.example.eco_bucket.myadapter_order;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AboutFragment extends Fragment {
    RecyclerView mainn;
    MyOrdersDetailsAdapter adapter;

    FirebaseAuth mAuth;
    FirebaseUser currentuser;

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // google authentation
        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainn=root.findViewById(R.id.recvieww);
        mainn.setLayoutManager(new LinearLayoutManager(getContext()));

        // google authentation
        String email = currentuser.getEmail();
        String username = email.replace(".com","");

        FirebaseRecyclerOptions<UploadingDataHolder> options =
                new FirebaseRecyclerOptions.Builder<UploadingDataHolder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("UserOrder").child(username), UploadingDataHolder.class)
                        .build();

        adapter=new MyOrdersDetailsAdapter(options);
        mainn.setAdapter(adapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
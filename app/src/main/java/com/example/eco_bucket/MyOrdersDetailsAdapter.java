package com.example.eco_bucket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyOrdersDetailsAdapter extends FirebaseRecyclerAdapter<UploadingDataHolder,MyOrdersDetailsAdapter.myviewholder> {
    public MyOrdersDetailsAdapter(@NonNull FirebaseRecyclerOptions<UploadingDataHolder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UploadingDataHolder UploadingDataHolder) {

        holder.MainType.setText(UploadingDataHolder.getType());
        holder.SubType.setText(UploadingDataHolder.getSubType());
        holder.DiliveryStatus.setText(UploadingDataHolder.getDiliveryStatus());
        Glide.with(holder.img.getContext()).load(UploadingDataHolder.getImageUrl()).into(holder.img);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainn,new MyOrderDetails_Preview(
                                UploadingDataHolder.getPname(), UploadingDataHolder.getPaddress(),
                                UploadingDataHolder.getPpincode(), UploadingDataHolder.getPphoneNumber(),
                                UploadingDataHolder.getDate(), UploadingDataHolder.getPaymentType(),
                                UploadingDataHolder.getAmountPaid(), UploadingDataHolder.getType(),
                                UploadingDataHolder.getSubType(),UploadingDataHolder.getImageUrl()
                                ,UploadingDataHolder.getTime()))
                        .addToBackStack(null).commit();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orde_rsrecylerview,parent,false);
        return new MyOrdersDetailsAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img ;
        TextView MainType,SubType,DiliveryStatus;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img23);
            MainType = itemView.findViewById(R.id.MainType);
            SubType = itemView.findViewById(R.id.SubType);
            DiliveryStatus = itemView.findViewById(R.id.DiliveryStatus);


        }
    }
}

package com.example.android.recylertest1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    ArrayList<Contact> contactArrayList = new ArrayList<>();
    private OnContactClickListener onItemClickListener;

    public ContactAdapter(OnContactClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
        holder.onBind(contactArrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(contactArrayList.get(position), position);
            }
        });
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.delete(contactArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    //interface
    interface OnContactClickListener {
        void onClick(Contact contact, int position);

        void delete(Contact contact);
    }


    // ContactView Holder Class to display data

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;
        ImageView img, deleteImage;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            img = itemView.findViewById(R.id.img);
            deleteImage = itemView.findViewById(R.id.deleteImage);
        }

        void onBind(Contact contact) {
            tvName.setText(contact.getName());
            tvNumber.setText(contact.getNumber());
            img.setImageResource(contact.getImgResource());
        }
    }
}

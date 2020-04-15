package com.example.android.recylertest1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends ListAdapter<Contact,ContactAdapter.ContactViewHolder> {

    private OnContactClickListener onContactClickListener;
    private static DiffUtil.ItemCallback<Contact> diffCallback = new DiffUtil.ItemCallback<Contact>() {
        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getNumber().equals(newItem.getNumber());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getName().equals(newItem.getName())
                    &&
                    oldItem.getNumber().equals(newItem.getNumber());
        }
    };

    protected ContactAdapter(OnContactClickListener onContactClickListener) {
        super(diffCallback);
        this.onContactClickListener = onContactClickListener;
    }

//    public ContactAdapter(OnContactClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
        holder.onBind(getItem(position));
        final Contact contact = getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onContactClickListener.onClick(contact);
            }
        });
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onContactClickListener.delete(contact);
            }
        });
    }


    //interface
    interface OnContactClickListener {
        void onClick(Contact contact);

        void delete(Contact contact );
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

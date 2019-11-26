package com.example.emailapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Email> list;

    public RecyclerAdapter(List<Email> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.email_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Email email = list.get(position);

        holder.textViewDate.setText(email.date.toString());
        holder.textViewSubject.setText(email.subject);
        holder.textViewAuthor.setText(email.author);
        holder.textViewBody.setText(email.body);
        holder.image.setImageResource(email.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDate, textViewSubject, textViewAuthor, textViewBody;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewSubject = itemView.findViewById(R.id.textViewSubject);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewBody = itemView.findViewById(R.id.textViewBody);
            image = itemView.findViewById(R.id.person_photo);

            // set on click listener to view the email
            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Email email = Email.list.get(getAdapterPosition());
                    Intent intent = new Intent(v.getContext(), ViewEmail.class);
                    intent.putExtra("Subject", email.subject);
                    intent.putExtra("Author","From: " + email.author);
                    intent.putExtra("Image", email.image);
                    intent.putExtra("Date", email.date.toString());
                    intent.putExtra("Body", email.body);
                    v.getContext().startActivity(intent);
                }
            });

            // set long click listener to delete email
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(v.getContext()).setTitle("Delete Email").setMessage("Are you sure you want to delete this item?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int pos) {
                                    Email.list.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                }
                            })
                            .setNegativeButton("No", null).show();
                    return true;
                }
            }
            );
        }

    }
}

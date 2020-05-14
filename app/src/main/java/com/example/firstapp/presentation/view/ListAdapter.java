package com.example.firstapp.presentation.view;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.CharacterDetail;
import com.example.firstapp.R;
import com.example.firstapp.presentation.model.Character;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;



public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Character> values;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(Character item);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        ImageView imageHeader;
        View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            imageHeader = (ImageView) v.findViewById(R.id.icon);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);

        }
    }


    public void add(int position, Character item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Character> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Character currentCharacter = values.get(position);
        // holder.imageHeader.setImageIcon(currentCharacter.getImage());
        holder.txtHeader.setText(currentCharacter.getName());
        holder.txtFooter.setText(currentCharacter.getSpecies());
        Picasso.get()
                .load(currentCharacter.getImage())
                .into(holder.imageHeader);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentCharacter);
               /* System.out.println("position : " + position + " | name : " + currentCharacter.getName());
                Intent intent = new Intent(holder.itemView.getContext(), CharacterDetail.class);
                intent.putExtra("EXTRA_CHARACTER", (Serializable) currentCharacter);
                v.getContext().startActivity(intent);  */
            }
        });

        /*holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });
        */
    }




    @Override
    public int getItemCount() {
        System.out.println(values.size());
        return values.size();
    }

}
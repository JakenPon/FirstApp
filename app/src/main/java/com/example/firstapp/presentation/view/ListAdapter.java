package com.example.firstapp.presentation.view;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.R;
import com.example.firstapp.presentation.model.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {
    private List<Character> list;
    private List<Character> listAll;
    private OnItemClickListener listener;

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Character> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(listAll);
            }else {
                for (Character character : listAll) {
                    if (character.getName().toLowerCase().contains(constraint.toString().toLowerCase().trim())) {
                        filteredList.add(character);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            System.out.println(list);
            System.out.println(results);
            list.addAll((Collection<? extends Character>) results.values);
            System.out.println(list);

            notifyDataSetChanged();
        }


    };

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
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Character> myDataset, OnItemClickListener listener) {
        this.list = myDataset;
        this.listener = listener;
        this.listAll = new ArrayList<>();
        System.out.print("listAll:"+ listAll);
        System.out.print("list:"+ list);
        listAll.addAll(list);
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
        final Character currentCharacter = list.get(position);
        holder.txtHeader.setText(currentCharacter.getName());
        holder.txtFooter.setText(currentCharacter.getSpecies());
        Picasso.get()
                .load(currentCharacter.getImage())
                .into(holder.imageHeader);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentCharacter);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
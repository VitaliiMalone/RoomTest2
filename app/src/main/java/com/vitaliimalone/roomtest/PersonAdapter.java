package com.vitaliimalone.roomtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitaliimalone.roomtest.model.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> people;
    final private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Person person, int position);
    }

    public PersonAdapter(List<Person> people, OnItemClickListener listener) {
        this.people = people;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(v);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.bind(people.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        TextView nameTv;
        @BindView(R.id.item_last_name)
        TextView lastNameTv;

        public PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final Person person, final OnItemClickListener listener) {
            nameTv.setText(person.getFirstName());
            lastNameTv.setText(person.getLastName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(person, getAdapterPosition());
                }
            });
        }
    }

    public void updateList(List<Person> newList) {
        people.clear();
        people.addAll(newList);
        notifyDataSetChanged();
    }
}

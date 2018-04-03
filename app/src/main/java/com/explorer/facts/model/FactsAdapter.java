package com.explorer.facts.model;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.explorer.R;
import com.explorer.databinding.ListItemFactsBinding;
import com.explorer.facts.FactsContract;

import java.util.List;

/**
 * Created by ankitpatel on 3/4/18.
 */

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactViewHolder> {
    private ObservableArrayList<Fact> facts = new ObservableArrayList<>();
    private FactsContract.Presenter mPresenter;

    public FactsAdapter(FactsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public void setFacts(List<Fact> facts) {
        this.facts.clear();
        this.facts.addAll(facts);
        notifyDataSetChanged();
    }

    static class FactViewHolder extends RecyclerView.ViewHolder {

        private final ListItemFactsBinding binding;

        FactViewHolder(ListItemFactsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        ListItemFactsBinding getViewDataBinding() {
            return binding;
        }
    }

    @NonNull
    @Override
    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ListItemFactsBinding factsBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.list_item_facts , parent, false);
        return new FactViewHolder(factsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final FactViewHolder holder, final int position) {
        ListItemFactsBinding binding = holder.getViewDataBinding();
        binding.setFact(facts.get(position));
        binding.setPresenter(mPresenter);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }
}

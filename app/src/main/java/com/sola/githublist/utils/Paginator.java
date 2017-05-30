package com.sola.githublist.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sola.githublist.presenters.ListPresenter;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class Paginator extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;
    private ListPresenter presenter;

    public Paginator(LinearLayoutManager layoutManager, ListPresenter presenter) {
        this.layoutManager = layoutManager;
        this.presenter = presenter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = layoutManager.getChildCount();
        int totalCount = layoutManager.getItemCount();

        if (!presenter.isPageLoading()) {
            if ((visibleItemCount + firstVisiblePosition) >= totalCount
                    && firstVisiblePosition >= 0)
                presenter.loadList();
        }
    }
}

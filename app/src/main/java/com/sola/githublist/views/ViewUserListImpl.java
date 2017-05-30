package com.sola.githublist.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sola.githublist.R;
import com.sola.githublist.models.UserDetail;
import com.sola.githublist.presenters.ListPresenter;
import com.sola.githublist.utils.FragToActivity;

import java.lang.reflect.Field;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class ViewUserListImpl extends Fragment implements ViewUserList {

    public static ViewUserListImpl getInstance() {
        return new ViewUserListImpl();
    }

    private FragToActivity frToActivity;
    private ListPresenter presenter;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String login = (String) view.getTag();
            if (login != null) {
                presenter.loadUserDetail(login);
            } else {
                Toast.makeText(getContext(), "Cannot show details", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_users_list, container, false);
        RecyclerView recycler = (RecyclerView) v.findViewById(R.id.usersList);
        presenter = ListPresenter.getInstance(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(presenter.getAdapter(getContext(), listener));
        recycler.addOnScrollListener(presenter.getPaginator(layoutManager));

        if (savedInstanceState == null) {
            presenter.loadList();
        }
        return v;
    }

    @Override
    public void onSomethingWrong(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void userDetailLoaded(UserDetail user) {
        frToActivity.showDetailFragment(user);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        frToActivity = (FragToActivity) context;
    }


    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

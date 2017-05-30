package com.sola.githublist.presenters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sola.githublist.models.UserDetail;
import com.sola.githublist.models.UserListItem;
import com.sola.githublist.utils.Paginator;
import com.sola.githublist.utils.UserDetailCallback;
import com.sola.githublist.utils.UsersAdapter;
import com.sola.githublist.views.ViewUserDetail;
import com.sola.githublist.views.ViewUserDetailImpl;
import com.sola.githublist.views.ViewUserList;
import com.sola.githublist.utils.RetrofitGitHubImpl;
import com.sola.githublist.utils.UsersCallback;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class ListPresenter {

    private static int lastUserId = 1;
    private static ListPresenter presenter;
    private RetrofitGitHubImpl retrofit;
    private ViewUserList vUserList;
    private UsersAdapter adapter;
    private Paginator paginator;
    private UserDetail user;
    private boolean isPageLoading = false;

    public static ListPresenter getInstance(ViewUserList vUserList) {
        if (presenter == null)
            presenter = new ListPresenter(vUserList);
        return presenter;
    }

    private ListPresenter(ViewUserList vUserList) {

        this.vUserList = vUserList;
        retrofit = RetrofitGitHubImpl.getInstance();
    }

    public void loadList() {
        Callback callback = new UsersCallback(this);
        retrofit.loadPage(lastUserId, callback);
        isPageLoading = true;
        if (adapter.getItemCount() != 0)
            adapter.showProgress();

    }

    public void loadSuccesfully(List<UserListItem> list) {
        if (list != null) {
            lastUserId = list.get(list.size() - 1).getId();
            adapter.addItems(list);
            adapter.hideProgress();
        }
        isPageLoading = false;
    }

    public void loadFailure(String error) {
        vUserList.onSomethingWrong(error);
    }

    public boolean isPageLoading() {
        return isPageLoading;
    }

    public UsersAdapter getAdapter(Context context, View.OnClickListener listener) {
        if (adapter == null)
            adapter = new UsersAdapter(context, listener);
        return adapter;
    }

    public Paginator getPaginator(LinearLayoutManager layoutManager) {
        if (paginator == null)
            paginator = new Paginator(layoutManager, this);
        return paginator;
    }

    public void loadUserDetail(String login) {
        Callback callback = new UserDetailCallback(this);
        retrofit.getUserDetail(login, callback);
    }

    public void setUserData(UserDetail user) {
        this.user = user;
        vUserList.userDetailLoaded(user);
    }

    public UserDetail getUserData() {
        return user;
    }

}

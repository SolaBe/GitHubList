package com.sola.githublist.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sola.githublist.R;
import com.sola.githublist.models.UserDetail;
import com.sola.githublist.utils.FragToActivity;

public class MainActivity extends AppCompatActivity implements FragToActivity {

    private static final String LIST_TAG = "list";

    private static final String DETAIL_TAG = "detail";

    private AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        if (getSupportFragmentManager().findFragmentByTag(LIST_TAG) == null &&
                getSupportFragmentManager().findFragmentByTag(DETAIL_TAG) == null) {
            changeFragment(ViewUserListImpl.getInstance(), LIST_TAG);
        }
    }

    public void changeFragment(Fragment fragment, String tag) {

        if (!activity.isFinishing()) {
            FragmentTransaction transaction =
                    activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, fragment, tag);
            transaction.addToBackStack(tag);
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void showDetailFragment(UserDetail user) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        ViewUserDetailImpl fragment = ViewUserDetailImpl.getInstance();
        fragment.setArguments(bundle);
        changeFragment(fragment, DETAIL_TAG);
    }

}

package com.sola.githublist.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sola.githublist.R;
import com.sola.githublist.models.UserDetail;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class ViewUserDetailImpl extends Fragment implements ViewUserDetail {

    public static ViewUserDetailImpl getInstance() {
        return new ViewUserDetailImpl();
    }

    private TextView login;
    private TextView name;
    private TextView email;
    private TextView type;
    private TextView company;
    private TextView blog;
    private TextView location;
    private TextView biograhy;
    private TextView pubRepos;
    private TextView pubGits;
    private TextView followers;
    private TextView follwing;
    private TextView createdAt;
    private TextView updatedAt;
    private TextView isAdmin;
    private ImageView avatar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_user_details, container, false);
        login = (TextView) v.findViewById(R.id.login);
        name = (TextView) v.findViewById(R.id.name);
        email = (TextView) v.findViewById(R.id.email);
        type = (TextView) v.findViewById(R.id.type);
        company = (TextView) v.findViewById(R.id.company);
        blog = (TextView) v.findViewById(R.id.blog);
        location = (TextView) v.findViewById(R.id.location);
        biograhy = (TextView) v.findViewById(R.id.bio);
        pubRepos = (TextView) v.findViewById(R.id.pubRepos);
        pubGits = (TextView) v.findViewById(R.id.pubGists);
        followers = (TextView) v.findViewById(R.id.follwers);
        follwing = (TextView) v.findViewById(R.id.following);
        createdAt = (TextView) v.findViewById(R.id.createdAt);
        updatedAt = (TextView) v.findViewById(R.id.updatedAt);
        isAdmin = (TextView) v.findViewById(R.id.isAdmin);
        avatar = (ImageView) v.findViewById(R.id.avatar);

        UserDetail user = (UserDetail) getArguments().get("user");
        if (user != null) {
            setUserData(user);
        }
        return v;
    }

    @Override
    public void setUserData(UserDetail user) {

        Picasso.with(getContext()).load(user.getAvatarUrl()).placeholder(R.drawable.github_logo)
                .into(avatar);
        login.setText(user.getLogin());
        name.setText(user.getName());
        email.setText(user.getEmail());
        type.setText(user.getType());
        company.setText(user.getCompany());
        blog.setText(user.getBlog());
        location.setText(user.getLocation());
        biograhy.setText(user.getBio());

        pubRepos.setText(String.format(getString(R.string.pub_repos), user.getPubRepo()));
        pubGits.setText(String.format(getString(R.string.pub_gists), user.getPubGists()));
        followers.setText(String.format(getString(R.string.followers), user.getFollowers()));
        follwing.setText(String.format(getString(R.string.following), user.getFollowing()));

        if (user.isAdmin())
            isAdmin.setVisibility(View.VISIBLE);
        else
            isAdmin.setVisibility(View.GONE);

        createdAt.setText(decodeDate(user.getCreatedAt()));
        updatedAt.setText(decodeDate(user.getUpdatedAt()));
    }

    @Override
    public void failResponse(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public String decodeDate(String formated) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault());
        String decodedDate = formated;
        try {
            Date date = sdf.parse(formated);
            sdf = new SimpleDateFormat("dd.MM.yyyy 'AT' hh:mm:ss", Locale.getDefault());
            decodedDate = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return decodedDate;
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

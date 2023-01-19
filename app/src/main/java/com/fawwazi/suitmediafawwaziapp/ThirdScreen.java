package com.fawwazi.suitmediafawwaziapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdScreen extends AppCompatActivity {
    private ImageView ivBack;
    private UserInterface userInterface;
    private ArrayList<UserData> userDataArrayList = new ArrayList<>();
    private UserAdapter adapter = new UserAdapter(userDataArrayList);
    private RecyclerView rvUser;
    private int page = 1;
    private boolean isEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.srl_pulltorefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1; isEmpty = false;
                userDataArrayList.clear();
                displayUsers(Integer.toString(page));
                pullToRefresh.setRefreshing(false);
            }
        });

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvUser = findViewById(R.id.rv_user);
        rvUser.setHasFixedSize(true);
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(adapter);

        displayUsers(Integer.toString(page));

        Toast toast = Toast.makeText(ThirdScreen.this, "There's nothing more to show", Toast.LENGTH_SHORT);
        rvUser.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    if (!isEmpty) {
                        page++;
                        displayUsers(Integer.toString(page));
                    } else {
                        toast.show();
                    }
                }

                if (recyclerView.canScrollVertically(1)) {
                    toast.cancel();
                }
            }
        });

        adapter.setOnItemClickCallback(new UserAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(String name) {
                SharedPreferences mySharedPreferences = getSharedPreferences("SELECTEDNAME_PREFS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putString("SELECTED_NAME", name);
                editor.apply();
            }
        });
    }

    private void displayUsers(String page) {
        userInterface = RetrofitClient.Connect().create(UserInterface.class);
        Call<UserResponse> showData = userInterface.showUser(page);
        showData.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body().getData().isEmpty()) {
                    isEmpty = true;
                    return;
                }
                userDataArrayList.addAll((ArrayList<UserData>) response.body().getData());
                adapter.userDataArrayList = userDataArrayList;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
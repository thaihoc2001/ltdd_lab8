package com.example.roomdb;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private List<User> userList;
    private UserAdapter userAdapter;
    private ListView listView;
    private UserDB userDB;
    private Button btnAdd, btnRemove, btnCancel;
    private EditText tpName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.idListView);
        this.initUi();
        List<User> list = new ArrayList<User>();
        User user = new User("Nguyen Thai Hoc");
        User user1 = new User("Ho Cong Viet");
        userDB.getInstance(this).userDao().insertAll(user);
        userDB.getInstance(this).userDao().insertAll(user1);
        userList = userDB.getInstance(this).userDao().getAll();
        Log.i(TAG, "OKe");
        userAdapter = new UserAdapter(
                this,
                R.layout.list_item,
                userList
        );
        listView.setAdapter(userAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();

            }
        });
    }
    private void addUser() {
        String name = tpName.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            return;
        }
        User user2 = new User(name);
        userDB.getInstance(this).userDao().insertAll(user2);
        tpName.setText("");
    }
    public void initUi(){
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);
        tpName = findViewById(R.id.tpnName);
    }

}

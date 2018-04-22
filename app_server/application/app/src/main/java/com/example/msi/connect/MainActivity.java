package com.example.msi.connect;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button login;
    TextView join;
    EditText userid;
    EditText password;

//    private SessionCallback sessionCallback;
//
//    private class SessionCallback implements ISessionCallback {
//
//        @Override
//        public void onSessionOpened() {
//            request();
//        }
//
//        @Override
//        public void onSessionOpenFailed(KakaoException exception) {
//            Log.d("error", "Session Fail Error is " + exception.getMessage().toString());
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login_btn);
        join = (TextView) findViewById(R.id.join_btn);
        userid = (EditText) findViewById(R.id.user_id);
        password = (EditText) findViewById(R.id.password);

//        sessionCallback = new SessionCallback();
//        Session.getCurrentSession().addCallback(sessionCallback);
//        Session.getCurrentSession().checkAndImplicitOpen();

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = userid.getText().toString();
                final String userPassword = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                                if (success) {
                                    String userID = jsonResponse.getString("userID");
                                    String userName = jsonResponse.getString("userName");
                                    int userAge = jsonResponse.getInt("userAge");
                                    Intent intent = new Intent(MainActivity.this, SubmainActivity.class);
                                    intent.putExtra("userID", userID);
                                    intent.putExtra("userName", userName);
                                    intent.putExtra("userAge", userAge);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("로그인에 실패했습니다.")
                                            .setNegativeButton("다시 시도", null)
                                            .create()
                                            .show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
    } //onCreate fin.

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)){
//            return ;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    public void request(){
//        UserManagement.getInstance().requestMe(new MeResponseCallback() {
//            @Override
//            public void onSessionClosed(ErrorResult errorResult) {
//                Log.d("error", "Session Closed Error is " + errorResult.toString());
//            }
//
//            @Override
//            public void onNotSignedUp() {
//
//            }
//
//            @Override
//            public void onSuccess(UserProfile result) {
//                Toast.makeText(MainActivity.this, "사용자 이름은 " + result.getNickname(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
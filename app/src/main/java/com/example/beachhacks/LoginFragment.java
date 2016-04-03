package com.example.beachhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.*;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginFragment extends Fragment {
    /**
     * CallbackManager for facebook events
     */
    private CallbackManager callbackManager;

    // Views for the UI
    private LoginButton loginButton;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) view.findViewById(R.id.login_button);

        // Just to redeclare user permissions
        loginButton.setReadPermissions("public_profile");

        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Ask for the user
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                if (response.getError() == null) {
                                    // Create a bundle
                                    Bundle userInfo = new Bundle();
                                    try {
                                        userInfo.putString("id", object.getString("id"));
                                        userInfo.putString("name", object.getString("name"));
                                        userInfo.putString("first_name", object.getString("first_name"));
                                        userInfo.putString("last_name", object.getString("last_name"));
                                        userInfo.putString("age_range", object.getJSONObject("age_range").getString("min"));
                                        userInfo.putString("gender", object.getString("gender"));
                                        userInfo.putString("locale", object.getString("locale"));
                                        userInfo.putString("picture", object.getJSONObject("picture").getJSONObject("data").getString("url"));
                                    } catch (JSONException e) {
                                        Log.e("JSON", e.getMessage());
                                    }

                                    // Update our screen
                                    Fragment fragment = DashboardFragment.newInstance(userInfo);
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                                    transaction.replace(R.id.activity_main, fragment);
                                    transaction.commit();
                                } else {
                                    switch (response.getError().getErrorCode()) {
                                        // TODO: Handle errors from response here.
                                    }
                                }
                            }
                        });

                // Put that information in a bundle
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,first_name,last_name,age_range,gender,locale,picture");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.v("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.v("LoginActivity", error.getCause().toString());
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
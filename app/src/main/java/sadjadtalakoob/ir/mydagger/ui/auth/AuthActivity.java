package sadjadtalakoob.ir.mydagger.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import sadjadtalakoob.ir.mydagger.R;
import sadjadtalakoob.ir.mydagger.models.User;
import sadjadtalakoob.ir.mydagger.viewmodels.ViewModelProviderFactory;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    private EditText userId;

    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userId = findViewById(R.id.user_id_input);

        findViewById(R.id.login_button).setOnClickListener(this);

        progressBar = findViewById(R.id.progress_bar);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();
        subscribeObservers();
    }

    private void subscribeObservers() {

        viewModel.observeUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case ERROR:
                            showProgressbar(false);
                            Toast.makeText(AuthActivity.this, userAuthResource.message +
                                    "\nDid you enter number between 1 and 10?", Toast.LENGTH_SHORT).show();
                            break;
                        case LOADING:
                            showProgressbar(true);
                            break;
                        case NOT_AUTHENTICATED:
                            showProgressbar(false);
                            break;
                        case AUTHENTICATED:
                            showProgressbar(false);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS " + userAuthResource.data.getEmail());
                            break;
                    }
                }
            }
        });
    }

    private void showProgressbar(boolean isVisible) {

        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_button:
                attemptLogin();
                break;
        }

    }

    private void attemptLogin() {

        if (TextUtils.isEmpty(userId.getText().toString())) {
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }
}

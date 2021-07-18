package sadjadtalakoob.ir.mydagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.PagerAdapter;

import javax.inject.Inject;

import sadjadtalakoob.ir.mydagger.network.auth.AuthApi;


public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is working...");
        if (this.authApi == null) {
            Log.d(TAG, "AuthApi is null");

        } else Log.d(TAG, "AuthApi is not null");

    }
}

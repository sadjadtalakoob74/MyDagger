package sadjadtalakoob.ir.mydagger.ui.auth;

import android.icu.lang.UScript;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import sadjadtalakoob.ir.mydagger.SessionManager;
import sadjadtalakoob.ir.mydagger.models.User;
import sadjadtalakoob.ir.mydagger.network.auth.AuthApi;


public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: viewmodel is working...");

    }

    public void authenticateWithId(int userId) {
        Log.d(TAG, "authenticateWithId: Attempting to login.");
        sessionManager.authenticatedWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {

        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .subscribeOn(Schedulers.io())
                        .onErrorReturn(new Function<Throwable, User>() {
                                           @NotNull
                                           @Override
                                           public User apply(@NotNull Throwable throwable) throws Exception {
                                               User errorUser = new User();
                                               errorUser.setId(-1);
                                               return errorUser;
                                           }
                                       }
                        )
                        .map(new Function<User, AuthResource<User>>() {
                            @NotNull
                            @Override
                            public AuthResource<User> apply(@NotNull User user) throws Exception {
                                if (user.getId() == -1) {
                                    return AuthResource.error("Could not authenticate user", (User) null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        }));
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}

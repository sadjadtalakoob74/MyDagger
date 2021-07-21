package sadjadtalakoob.ir.mydagger;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;
import javax.inject.Singleton;

import sadjadtalakoob.ir.mydagger.models.User;
import sadjadtalakoob.ir.mydagger.ui.auth.AuthResource;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {

    }

    public void authenticatedWithId(final LiveData<AuthResource<User>> source) {
        if (cachedUser != null){
            cachedUser.setValue(AuthResource.loading((User)null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }

    }
    
    public void logOut(){
        Log.d(TAG, "logOut: Logging out ...");
        cachedUser.setValue(AuthResource.logout());
    }
    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }
}

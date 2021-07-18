package sadjadtalakoob.ir.mydagger.di.auth;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sadjadtalakoob.ir.mydagger.network.auth.AuthApi;

@Module
public class AuthModule {

    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
}

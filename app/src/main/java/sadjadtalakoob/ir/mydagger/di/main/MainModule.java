package sadjadtalakoob.ir.mydagger.di.main;
/*
 * @created 7/23/2021 - 9:14 AM
 * @project MyDagger
 * @author Sadjadt
 */

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sadjadtalakoob.ir.mydagger.network.main.MainApi;
import sadjadtalakoob.ir.mydagger.ui.main.posts.PostRecyclerAdapter;

@Module
public class MainModule {

    @Provides
    static PostRecyclerAdapter postRecyclerAdapter(){
        return new PostRecyclerAdapter();
    }

@Provides
    static MainApi provideMainApi(Retrofit retrofit){
    return retrofit.create(MainApi.class);
}

}

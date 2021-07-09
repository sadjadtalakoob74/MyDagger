package sadjadtalakoob.ir.mydagger.di;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import sadjadtalakoob.ir.mydagger.AuthActivity;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

    @Provides
    static String string(){
        return "this is test string";
    }
}

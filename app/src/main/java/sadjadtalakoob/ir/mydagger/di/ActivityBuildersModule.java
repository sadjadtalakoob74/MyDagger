package sadjadtalakoob.ir.mydagger.di;



import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import sadjadtalakoob.ir.mydagger.di.auth.AuthModule;
import sadjadtalakoob.ir.mydagger.di.auth.AuthViewModelsModule;
import sadjadtalakoob.ir.mydagger.ui.auth.AuthActivity;
import sadjadtalakoob.ir.mydagger.ui.main.MainActivity;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity conMainActivity();

}

package sadjadtalakoob.ir.mydagger;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import sadjadtalakoob.ir.mydagger.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}

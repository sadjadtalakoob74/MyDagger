package sadjadtalakoob.ir.mydagger.di;

import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import sadjadtalakoob.ir.mydagger.viewmodels.ViewModelProviderFactory;


@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

}

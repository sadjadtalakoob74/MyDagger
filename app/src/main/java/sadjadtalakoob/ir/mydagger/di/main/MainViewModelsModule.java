package sadjadtalakoob.ir.mydagger.di.main;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import sadjadtalakoob.ir.mydagger.di.ViewModelKey;
import sadjadtalakoob.ir.mydagger.ui.main.profile.ProfileViewModel;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel viewModel(ProfileViewModel profileViewModel);
}

package sadjadtalakoob.ir.mydagger.di.auth;


import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import sadjadtalakoob.ir.mydagger.di.ViewModelKey;
import sadjadtalakoob.ir.mydagger.ui.auth.AuthViewModel;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}

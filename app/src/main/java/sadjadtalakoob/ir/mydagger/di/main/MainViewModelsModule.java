package sadjadtalakoob.ir.mydagger.di.main;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import sadjadtalakoob.ir.mydagger.di.ViewModelKey;
import sadjadtalakoob.ir.mydagger.ui.main.photos.PhotosViewModel;
import sadjadtalakoob.ir.mydagger.ui.main.posts.PostsViewModel;
import sadjadtalakoob.ir.mydagger.ui.main.profile.ProfileViewModel;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostViewModel(PostsViewModel postsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PhotosViewModel.class)
    public abstract ViewModel bindPhotoViewModel(PhotosViewModel photosViewModel);
}

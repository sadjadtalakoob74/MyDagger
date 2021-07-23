package sadjadtalakoob.ir.mydagger.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import sadjadtalakoob.ir.mydagger.ui.main.photos.PhotosFragment;
import sadjadtalakoob.ir.mydagger.ui.main.posts.PostFragment;
import sadjadtalakoob.ir.mydagger.ui.main.profile.ProfileFragment;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment profileFragment();

    @ContributesAndroidInjector
    abstract PostFragment postFragment();

    @ContributesAndroidInjector
    abstract PhotosFragment photosFragment();

}

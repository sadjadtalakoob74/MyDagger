package sadjadtalakoob.ir.mydagger.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sadjadtalakoob.ir.mydagger.R;

@Module
public class AppModule {

    @Singleton
    @Provides
    static RequestOptions requestOptions() {
        return requestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
    }

    @Singleton
    @Provides
    static RequestManager requestManagerGlideInstance(Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);

    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.icon);
    }
}

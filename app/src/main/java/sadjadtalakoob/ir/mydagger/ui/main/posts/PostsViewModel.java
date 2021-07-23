package sadjadtalakoob.ir.mydagger.ui.main.posts;
/*
 * @created 7/23/2021 - 9:20 AM
 * @project MyDagger
 * @author Sadjadt
 */

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import sadjadtalakoob.ir.mydagger.SessionManager;
import sadjadtalakoob.ir.mydagger.models.Post;
import sadjadtalakoob.ir.mydagger.network.main.MainApi;
import sadjadtalakoob.ir.mydagger.ui.main.Resource;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> mediatorPosts ;
    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: ViewModel is working...");
    }

    public LiveData<Resource<List<Post>>> observePosts(){
        if (mediatorPosts==null){
            mediatorPosts = new MediatorLiveData<>();
            mediatorPosts.setValue(Resource.loading((List<Post>) null));

            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId())
                    .onErrorReturn(new Function<Throwable, List<Post>>() {
                        @NotNull
                        @Override
                        public List<Post> apply(@NotNull Throwable throwable) throws Exception {
                            Log.e(TAG, "apply: ",throwable );
                            Post post = new Post();
                            post.setId(-1);
                            ArrayList<Post> posts = new ArrayList<>();
                            posts.add(post);
                            return posts;
                        }
                    })
                    .map(new Function<List<Post>, Resource<List<Post>>>() {
                        @NotNull
                        @Override
                        public Resource<List<Post>> apply(@NotNull List<Post> posts) throws Exception {
                            if (posts.size()>0){
                                if (posts.get(0).getId()==-1){
                                    return Resource.error("Something went wrong",null);
                                }
                            }
                            return Resource.success(posts);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );
            mediatorPosts.addSource(source, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    mediatorPosts.setValue(listResource);
                    mediatorPosts.removeSource(source);
                }
            });
        }
        return mediatorPosts;
    }
    
    
}

package sadjadtalakoob.ir.mydagger.network.main;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sadjadtalakoob.ir.mydagger.models.Post;

public interface MainApi {
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(
            @Query("userId") int id
    );
}

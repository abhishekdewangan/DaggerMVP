package demo.dagger.com.daggermvprxjava.net;

import java.util.List;

import demo.dagger.com.daggermvprxjava.data.Comment;
import demo.dagger.com.daggermvprxjava.data.Shot;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface DribblerService {

    /**
     * Retrieve a list of shots
     */
    @GET("shots")
    public Single<List<Shot>> getShots(@Query("access_token") String accessToken,
                                       @Query("per_page") int perPage,
                                       @Query("page") int page);

    /**
     * Retrieve a list of comments for a given shot
     */
    @GET("shots/{shot_id}/comments")
    public Single<List<Comment>> getComments(@Path("shot_id") int shotId,
                                             @Query("access_token") String accessToken,
                                             @Query("per_page") int perPage,
                                             @Query("page") int page);
}

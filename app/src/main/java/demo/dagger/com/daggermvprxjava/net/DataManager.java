package demo.dagger.com.daggermvprxjava.net;

import java.util.List;
import javax.inject.Inject;

import demo.dagger.com.daggermvprxjava.data.Comment;
import demo.dagger.com.daggermvprxjava.data.Shot;
import demo.dagger.com.daggermvprxjava.utils.Statics;
import rx.Single;

public class DataManager {

    private final DribblerService mDribblerService;

    @Inject
    public DataManager(DribblerService dribblerService) {
        mDribblerService = dribblerService;
    }

    public Single<List<Shot>> getShots(int perPage, int page) {
        return mDribblerService.getShots(Statics.DRIBBBLE_ACCESS_TOKEN, perPage, page);
    }

    public Single<List<Comment>> getComments(int id, int perPage, int page) {
        return mDribblerService.getComments(id, Statics.DRIBBBLE_ACCESS_TOKEN, perPage, page);
    }
}

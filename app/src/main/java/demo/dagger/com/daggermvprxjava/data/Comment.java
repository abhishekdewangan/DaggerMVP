package demo.dagger.com.daggermvprxjava.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amanarora on 4/10/17.
 */

public class Comment implements Parcelable {

    private int id;
    private String body;
    private User user;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.body);
        dest.writeParcelable(this.user, flags);
    }

    public Comment() {
    }

    private Comment(Parcel in) {
        this.id = in.readInt();
        this.body = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}

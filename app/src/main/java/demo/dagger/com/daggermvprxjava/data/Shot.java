package demo.dagger.com.daggermvprxjava.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Shot implements Parcelable {

    private int id;
    private String title;
    private String description;
    private int width;
    private int height;
    private Image images;
    private String image;
    private String views_count;
    private String likes_count;
    private User user;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.image);
        dest.writeString(this.views_count);
        dest.writeString(this.likes_count);
        dest.writeParcelable(this.user, flags);
    }

    public Shot() {
    }

    private Shot(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        width = in.readInt();
        height = in.readInt();
        this.images = in.readParcelable(Image.class.getClassLoader());
        image = in.readString();
        views_count = in.readString();
        likes_count = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Shot> CREATOR = new Creator<Shot>() {
        @Override
        public Shot createFromParcel(Parcel source) {
            return new Shot(source);
        }

        @Override
        public Shot[] newArray(int size) {
            return new Shot[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getViews_count() {
        return views_count;
    }

    public void setViews_count(String views_count) {
        this.views_count = views_count;
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

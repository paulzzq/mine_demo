package com.zzq.paul_tools.bean;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fengjuan on 2017/9/26.
 */

public class Article implements Parcelable {

    private String id;
    private int index;
    private String introduce;
    private boolean is_praised;
    private int praise_times;
    private int publish_time;
    private int see_times;
    private String title;
    private String top;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public boolean isIs_praised() {
        return is_praised;
    }

    public void setIs_praised(boolean is_praised) {
        this.is_praised = is_praised;
    }

    public int getPraise_times() {
        return praise_times;
    }

    public void setPraise_times(int praise_times) {
        this.praise_times = praise_times;
    }

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }

    public int getSee_times() {
        return see_times;
    }

    public void setSee_times(int see_times) {
        this.see_times = see_times;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "Article{" +
                    "id='" + id + '\'' +
                    ", index=" + index +
                    ", introduce='" + introduce + '\'' +
                    ", is_praised=" + is_praised +
                    ", praise_times=" + praise_times +
                    ", publish_time=" + publish_time +
                    ", see_times=" + see_times +
                    ", title='" + title + '\'' +
                    ", top='" + top + '\'' +
                ", type='" + type + '\'' +
                    '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.index);
        dest.writeString(this.introduce);
        dest.writeByte(this.is_praised ? (byte) 1 : (byte) 0);
        dest.writeInt(this.praise_times);
        dest.writeInt(this.publish_time);
        dest.writeInt(this.see_times);
        dest.writeString(this.title);
        dest.writeString(this.top);
        dest.writeString(this.type);
    }

    public Article() {
    }

    public Article(String id, String title, String des) {
        this.id = id;
        this.title = title;
        this.introduce = des;
    }

    protected Article(Parcel in) {
        this.id = in.readString();
        this.index = in.readInt();
        this.introduce = in.readString();
        this.is_praised = in.readByte() != 0;
        this.praise_times = in.readInt();
        this.publish_time = in.readInt();
        this.see_times = in.readInt();
        this.title = in.readString();
        this.top = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}

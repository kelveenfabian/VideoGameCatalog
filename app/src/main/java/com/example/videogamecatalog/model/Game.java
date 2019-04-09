package com.example.videogamecatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Game
        implements Parcelable {

    private int id;
    private String name;
    private String summary;
    private String url;

    protected Game(Parcel in) {
        id = in.readInt();
        name = in.readString();
        summary = in.readString();
        url = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {

        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(summary);
        dest.writeString(url);
    }
}

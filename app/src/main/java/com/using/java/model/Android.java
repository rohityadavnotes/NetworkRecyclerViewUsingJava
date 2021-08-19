package com.using.java.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Android implements Parcelable {

    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("codeName")
    @Expose
    private String codeName;
    @SerializedName("versionNumbers")
    @Expose
    private String versionNumbers;
    @SerializedName("apiLevel")
    @Expose
    private String apiLevel;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getVersionNumbers() {
        return versionNumbers;
    }

    public void setVersionNumbers(String versionNumbers) {
        this.versionNumbers = versionNumbers;
    }

    public String getApiLevel() {
        return apiLevel;
    }

    public void setApiLevel(String apiLevel) {
        this.apiLevel = apiLevel;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Android() {
    }

    protected Android(Parcel in) {
        this.logo = in.readString();
        this.codeName = in.readString();
        this.versionNumbers = in.readString();
        this.apiLevel = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Parcelable.Creator<Android> CREATOR = new Parcelable.Creator<Android>() {
        @Override
        public Android createFromParcel(Parcel source) {
            return new Android(source);
        }

        @Override
        public Android[] newArray(int size) {
            return new Android[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.logo);
        dest.writeString(this.codeName);
        dest.writeString(this.versionNumbers);
        dest.writeString(this.apiLevel);
        dest.writeString(this.releaseDate);
    }
}

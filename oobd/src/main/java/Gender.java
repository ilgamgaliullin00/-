import com.google.gson.annotations.SerializedName;

public enum Gender {
    @SerializedName("MALE")
    MALE,
    @SerializedName("FEMALE")
    FEMALE,
    @SerializedName("TRANSGENDER")
    TRANSGENDER;
}
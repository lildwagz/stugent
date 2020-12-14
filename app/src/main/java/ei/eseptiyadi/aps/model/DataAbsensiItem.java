
package ei.eseptiyadi.aps.model;

import com.google.gson.annotations.SerializedName;

public class DataAbsensiItem{

    @SerializedName("jampelajaran_ke")
    private String jampelajaranKe;

    @SerializedName("kelas")
    private String kelas;

    @SerializedName("img_picture")
    private String imgPicture;

    @SerializedName("namasiswa")
    private String namasiswa;

    @SerializedName("status")
    private String status;

    public String getJampelajaranKe(){
        return jampelajaranKe;
    }

    public String getKelas(){
        return kelas;
    }

    public String getImgPicture(){
        return imgPicture;
    }

    public String getNamasiswa(){
        return namasiswa;
    }

    public String getStatus(){
        return status;
    }
}
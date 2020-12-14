package ei.eseptiyadi.aps.model.filter;

import com.google.gson.annotations.SerializedName;

public class DatasiswaItem{

    @SerializedName("nama_siswa")
    private String namaSiswa;

    @SerializedName("NIS")
    private String nIS;

    public String getNamaSiswa(){
        return namaSiswa;
    }

    public String getNIS(){
        return nIS;
    }
}
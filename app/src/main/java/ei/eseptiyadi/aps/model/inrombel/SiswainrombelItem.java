
package ei.eseptiyadi.aps.model.inrombel;

import com.google.gson.annotations.SerializedName;

public class SiswainrombelItem{

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
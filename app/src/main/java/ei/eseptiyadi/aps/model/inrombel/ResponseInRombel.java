package ei.eseptiyadi.aps.model.inrombel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseInRombel{

    @SerializedName("wali_kelas")
    private String waliKelas;

    @SerializedName("siswainrombel")
    private List<SiswainrombelItem> siswainrombel;

    @SerializedName("ketua_kelas")
    private String ketuaKelas;

    @SerializedName("code")
    private int code;

    @SerializedName("pendidikan")
    private String pendidikan;

    @SerializedName("jurusan")
    private String jurusan;

    @SerializedName("totalsiswa")
    private String totalsiswa;

    @SerializedName("message")
    private String message;

    @SerializedName("kejuruan")
    private String kejuruan;

    @SerializedName("periode")
    private String periode;

    @SerializedName("kelas")
    private String kelas;

    @SerializedName("rombel_terdaftar")
    private String rombelTerdaftar;

    @SerializedName("instansi")
    private String instansi;

    @SerializedName("status")
    private boolean status;

    public String getWaliKelas(){
        return waliKelas;
    }

    public List<SiswainrombelItem> getSiswainrombel(){
        return siswainrombel;
    }

    public String getKetuaKelas(){
        return ketuaKelas;
    }

    public int getCode(){
        return code;
    }

    public String getPendidikan(){
        return pendidikan;
    }

    public String getJurusan(){
        return jurusan;
    }

    public String getTotalsiswa(){
        return totalsiswa;
    }

    public String getMessage(){
        return message;
    }

    public String getKejuruan(){
        return kejuruan;
    }

    public String getPeriode(){
        return periode;
    }

    public String getKelas(){
        return kelas;
    }

    public String getRombelTerdaftar(){
        return rombelTerdaftar;
    }

    public String getInstansi(){
        return instansi;
    }

    public boolean isStatus(){
        return status;
    }
}
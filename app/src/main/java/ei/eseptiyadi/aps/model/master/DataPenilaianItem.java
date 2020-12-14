package ei.eseptiyadi.aps.model.master;

import com.google.gson.annotations.SerializedName;

public class DataPenilaianItem{

	@SerializedName("kode_penilaian")
	private String kodePenilaian;

	@SerializedName("tanggal_penilaian")
	private String tanggalPenilaian;

	@SerializedName("jenis_penilaian")
	private String jenisPenilaian;

	public String getKodePenilaian(){
		return kodePenilaian;
	}

	public String getTanggalPenilaian(){
		return tanggalPenilaian;
	}

	public String getJenisPenilaian(){
		return jenisPenilaian;
	}
}
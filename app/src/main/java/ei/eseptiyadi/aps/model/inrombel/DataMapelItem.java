package ei.eseptiyadi.aps.model.inrombel;

import com.google.gson.annotations.SerializedName;

public class DataMapelItem{

	@SerializedName("kode_mapel")
	private String kodeMapel;

	@SerializedName("nama_matapelajaran")
	private String namaMatapelajaran;

	public String getKodeMapel(){
		return kodeMapel;
	}

	public String getNamaMatapelajaran(){
		return namaMatapelajaran;
	}

	@Override
 	public String toString(){
		return 
			"DataMapelItem{" + 
			"kode_mapel = '" + kodeMapel + '\'' + 
			",nama_matapelajaran = '" + namaMatapelajaran + '\'' + 
			"}";
		}
}
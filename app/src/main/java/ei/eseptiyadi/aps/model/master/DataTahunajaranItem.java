package ei.eseptiyadi.aps.model.master;

import com.google.gson.annotations.SerializedName;

public class DataTahunajaranItem{

	@SerializedName("tahunajaran")
	private String tahunajaran;

	@SerializedName("identifikasi")
	private String identifikasi;

	@SerializedName("kode_tahun_ajaran")
	private String kodeTahunAjaran;

	public String getTahunajaran(){
		return tahunajaran;
	}

	public String getIdentifikasi(){
		return identifikasi;
	}

	public String getKodeTahunAjaran(){
		return kodeTahunAjaran;
	}

	@Override
 	public String toString(){
		return 
			"DataTahunajaranItem{" + 
			"tahunajaran = '" + tahunajaran + '\'' + 
			",identifikasi = '" + identifikasi + '\'' + 
			",kode_tahun_ajaran = '" + kodeTahunAjaran + '\'' + 
			"}";
		}
}
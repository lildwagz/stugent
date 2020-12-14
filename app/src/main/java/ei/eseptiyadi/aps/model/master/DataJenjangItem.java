package ei.eseptiyadi.aps.model.master;

import com.google.gson.annotations.SerializedName;

public class DataJenjangItem{

	@SerializedName("jenjang_pendidikan")
	private String jenjangPendidikan;

	@SerializedName("terpadu")
	private String terpadu;

	@SerializedName("detail")
	private String detail;

	@SerializedName("instansi")
	private String instansi;

	public String getJenjangPendidikan(){
		return jenjangPendidikan;
	}

	public String getTerpadu(){
		return terpadu;
	}

	public String getDetail(){
		return detail;
	}

	public String getInstansi(){
		return instansi;
	}
}
package ei.eseptiyadi.aps.model.inrombel;

import com.google.gson.annotations.SerializedName;

public class DetailKelas{

	@SerializedName("wali_kelas")
	private String waliKelas;

	@SerializedName("ketua_kelas")
	private String ketuaKelas;

	@SerializedName("total_siswatkj")
	private String totalSiswatkj;

	@SerializedName("total_siswarpl")
	private String totalSiswarpl;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("total_siswa")
	private String totalSiswa;

	@SerializedName("totalmapel")
	private String totalmapel;

	public String getWaliKelas(){
		return waliKelas;
	}

	public String getKetuaKelas(){
		return ketuaKelas;
	}

	public String getTotalSiswatkj(){
		return totalSiswatkj;
	}

	public String getTotalSiswarpl(){
		return totalSiswarpl;
	}

	public String getKelas(){
		return kelas;
	}

	public String getTotalSiswa(){
		return totalSiswa;
	}

	public String getTotalmapel(){
		return totalmapel;
	}

	@Override
 	public String toString(){
		return 
			"DetailKelas{" + 
			"wali_kelas = '" + waliKelas + '\'' + 
			",ketua_kelas = '" + ketuaKelas + '\'' + 
			",total_siswatkj = '" + totalSiswatkj + '\'' + 
			",total_siswarpl = '" + totalSiswarpl + '\'' + 
			",kelas = '" + kelas + '\'' + 
			",total_siswa = '" + totalSiswa + '\'' + 
			",totalmapel = '" + totalmapel + '\'' + 
			"}";
		}
}
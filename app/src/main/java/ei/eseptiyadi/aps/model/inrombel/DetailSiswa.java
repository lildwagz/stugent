package ei.eseptiyadi.aps.model.inrombel;

import com.google.gson.annotations.SerializedName;

public class DetailSiswa{

	@SerializedName("kode_penilaian")
	private String kodePenilaian;

	@SerializedName("nama_siswa")
	private String namaSiswa;

	@SerializedName("NIS")
	private String nIS;

	@SerializedName("kode_mapel")
	private String kodeMapel;

	@SerializedName("nilai_tugas")
	private String nilaiTugas;

	@SerializedName("nama_matapelajaran")
	private String namaMatapelajaran;

	@SerializedName("nilai_praktikum")
	private String nilaiPraktikum;

	@SerializedName("nilai_teori")
	private String nilaiTeori;

	@SerializedName("nilai_absensi")
	private String nilaiAbsensi;

	public String getKodePenilaian(){
		return kodePenilaian;
	}

	public String getNamaSiswa(){
		return namaSiswa;
	}

	public String getNIS(){
		return nIS;
	}

	public String getKodeMapel(){
		return kodeMapel;
	}

	public String getNilaiTugas(){
		return nilaiTugas;
	}

	public String getNamaMatapelajaran(){
		return namaMatapelajaran;
	}

	public String getNilaiPraktikum(){
		return nilaiPraktikum;
	}

	public String getNilaiTeori(){
		return nilaiTeori;
	}

	public String getNilaiAbsensi(){
		return nilaiAbsensi;
	}

	@Override
 	public String toString(){
		return 
			"DetailSiswaAdapter{" +
			"kode_penilaian = '" + kodePenilaian + '\'' + 
			",nama_siswa = '" + namaSiswa + '\'' + 
			",nIS = '" + nIS + '\'' + 
			",kode_mapel = '" + kodeMapel + '\'' + 
			",nilai_tugas = '" + nilaiTugas + '\'' + 
			",nama_matapelajaran = '" + namaMatapelajaran + '\'' + 
			",nilai_praktikum = '" + nilaiPraktikum + '\'' + 
			",nilai_teori = '" + nilaiTeori + '\'' + 
			",nilai_absensi = '" + nilaiAbsensi + '\'' + 
			"}";
		}
}
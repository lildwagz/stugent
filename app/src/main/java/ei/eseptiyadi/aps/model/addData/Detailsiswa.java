package ei.eseptiyadi.aps.model.addData;

public class Detailsiswa{
	private String namaSiswa;
	private String emailSiswa;
	private String nIS;
	private String ditambahkan;
	private String username;

	public String getNamaSiswa(){
		return namaSiswa;
	}

	public String getEmailSiswa(){
		return emailSiswa;
	}

	public String getNIS(){
		return nIS;
	}

	public String getDitambahkan(){
		return ditambahkan;
	}

	public String getUsername(){
		return username;
	}

	@Override
	public String toString(){
		return
				"Detailsiswa{" +
						"nama_siswa = '" + namaSiswa + '\'' +
						",email_siswa = '" + emailSiswa + '\'' +
						",nIS = '" + nIS + '\'' +
						",ditambahkan = '" + ditambahkan + '\'' +
						",username = '" + username + '\'' +
						"}";
	}
}

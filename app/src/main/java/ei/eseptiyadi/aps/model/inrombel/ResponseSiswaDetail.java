package ei.eseptiyadi.aps.model.inrombel;

import com.google.gson.annotations.SerializedName;

public class ResponseSiswaDetail{

	@SerializedName("code")
	private int code;

	@SerializedName("detail_siswa")
	private DetailSiswa detailSiswa;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public DetailSiswa getDetailSiswa(){
		return detailSiswa;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseSiswaDetail{" + 
			"code = '" + code + '\'' + 
			",detail_siswa = '" + detailSiswa + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
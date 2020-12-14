package ei.eseptiyadi.aps.model.inrombel;

import com.google.gson.annotations.SerializedName;

public class ResponseDetailKelas{

	@SerializedName("code")
	private int code;

	@SerializedName("detail_kelas")
	private DetailKelas detailKelas;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public DetailKelas getDetailKelas(){
		return detailKelas;
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
			"ResponseDetailKelas{" + 
			"code = '" + code + '\'' + 
			",detail_kelas = '" + detailKelas + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
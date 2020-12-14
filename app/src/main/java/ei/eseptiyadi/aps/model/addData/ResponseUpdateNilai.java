package ei.eseptiyadi.aps.model.addData;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateNilai{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}
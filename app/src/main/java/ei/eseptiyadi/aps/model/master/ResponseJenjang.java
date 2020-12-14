package ei.eseptiyadi.aps.model.master;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseJenjang{

	@SerializedName("data_jenjang")
	private List<DataJenjangItem> dataJenjang;

	@SerializedName("total_datajenjang")
	private String totalDatajenjang;

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<DataJenjangItem> getDataJenjang(){
		return dataJenjang;
	}

	public String getTotalDatajenjang(){
		return totalDatajenjang;
	}

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
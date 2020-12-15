package ei.eseptiyadi.aps.model.inrombel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataMapel{

	@SerializedName("total_mapel")
	private String totalMapel;

	@SerializedName("code")
	private int code;

	@SerializedName("data_mapel")
	private List<DataMapelItem> dataMapel;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getTotalMapel(){
		return totalMapel;
	}

	public int getCode(){
		return code;
	}

	public List<DataMapelItem> getDataMapel(){
		return dataMapel;
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
			"ResponseDataMapel{" + 
			"total_mapel = '" + totalMapel + '\'' + 
			",code = '" + code + '\'' + 
			",data_mapel = '" + dataMapel + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
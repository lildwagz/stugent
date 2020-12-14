package ei.eseptiyadi.aps.model.master;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTahunAjaran{

	@SerializedName("code")
	private int code;

	@SerializedName("data_tahunajaran")
	private List<DataTahunajaranItem> dataTahunajaran;

	@SerializedName("total_data")
	private String totalData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public List<DataTahunajaranItem> getDataTahunajaran(){
		return dataTahunajaran;
	}

	public String getTotalData(){
		return totalData;
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
			"ResponseTahunAjaran{" + 
			"code = '" + code + '\'' + 
			",data_tahunajaran = '" + dataTahunajaran + '\'' + 
			",total_data = '" + totalData + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
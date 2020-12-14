package ei.eseptiyadi.aps.model.master;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataPenilain{

	@SerializedName("code")
	private int code;

	@SerializedName("total_penilaian")
	private String totalPenilaian;

	@SerializedName("message")
	private String message;

	@SerializedName("data_penilaian")
	private List<DataPenilaianItem> dataPenilaian;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public String getTotalPenilaian(){
		return totalPenilaian;
	}

	public String getMessage(){
		return message;
	}

	public List<DataPenilaianItem> getDataPenilaian(){
		return dataPenilaian;
	}

	public boolean isStatus(){
		return status;
	}
}
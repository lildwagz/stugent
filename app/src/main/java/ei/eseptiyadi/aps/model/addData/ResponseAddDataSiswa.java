package ei.eseptiyadi.aps.model.addData;

import com.google.gson.annotations.SerializedName;

public class ResponseAddDataSiswa{
  private int code;
  private Detailsiswa detailsiswa;
  private String message;
  private boolean status;

  public int getCode(){
    return code;
  }

  public Detailsiswa getDetailsiswa(){
    return detailsiswa;
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
			"ResponseAddDataSiswa{" + 
			"code = '" + code + '\'' + 
			",detailsiswa = '" + detailsiswa + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
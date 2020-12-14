
package ei.eseptiyadi.aps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFilterAbsensi{

  @SerializedName("data_absensi")
  private List<DataAbsensiItem> dataAbsensi;

  @SerializedName("code")
  private int code;

  @SerializedName("message")
  private String message;

  @SerializedName("status")
  private boolean status;

  public List<DataAbsensiItem> getDataAbsensi(){
    return dataAbsensi;
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
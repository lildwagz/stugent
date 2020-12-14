package ei.eseptiyadi.aps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.model.master.DataTahunajaranItem;
import ei.eseptiyadi.aps.module.Preferences;

public class ListTahunAjaran extends RecyclerView.Adapter<ListTahunAjaran.MyViewHolder> {

  Context context;

  List<DataTahunajaranItem> listDataTahunAjaran;
  private onAlertdialogstatus mlistener;
  public interface onAlertdialogstatus{
    void onAlertDialogChange (boolean status);
  }

  public ListTahunAjaran(Context context, List<DataTahunajaranItem> listDataTahunAjaran,onAlertdialogstatus mlistener) {
    this.context = context;
    this.listDataTahunAjaran = listDataTahunAjaran;
    this.mlistener = mlistener;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.itemlist_tahun_ajaran, parent, false);
    MyViewHolder myViewHolder = new MyViewHolder(view);
    return myViewHolder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, final int position) {

    holder.txTahunAjaran.setText(listDataTahunAjaran.get(position).getTahunajaran());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick( View view) {
        if (Preferences.getKey_TahunAjaran(context).isEmpty()){
          Preferences.setKey_TahunAjaran(context,listDataTahunAjaran.get(position).getTahunajaran());

        }else {
          Preferences.clearKeyTahunAjaran(context);
          Preferences.setKey_TahunAjaran(context,listDataTahunAjaran.get(position).getTahunajaran());
        }
        mlistener.onAlertDialogChange(false);
      }
    });
  }

  @Override
  public int getItemCount() {
    return listDataTahunAjaran.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView txTahunAjaran, txIdentifkasiTA;
    ImageView imvKet;

    public MyViewHolder(View itemView) {
      super(itemView);
      txTahunAjaran = (TextView)itemView.findViewById(R.id.TvTahunAjaran);
//      txIdentifkasiTA = (TextView)itemView.findViewById(R.id.txtIdentifikasiTA);
//      imvKet = (ImageView)itemView.findViewById(R.id.ivKetTA);
    }
  }
}
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
import ei.eseptiyadi.aps.model.master.DataPenilaianItem;
import ei.eseptiyadi.aps.model.master.DataTahunajaranItem;
import ei.eseptiyadi.aps.module.Preferences;

public class ListPenilaian extends RecyclerView.Adapter<ListPenilaian.MyViewHolder> {

    Context context;

    List<DataPenilaianItem> dataPenilaianItems;
    private onAlertdialogstatus mlistener;
    public interface onAlertdialogstatus{
        void onAlertDialogChange (boolean status);
    }

    public ListPenilaian(Context context, List<DataPenilaianItem> dataPenilaianItems,onAlertdialogstatus mlistener) {
        this.context = context;
        this.dataPenilaianItems = dataPenilaianItems;
        this.mlistener = mlistener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kodenilaian, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txKodePenilaian.setText(dataPenilaianItems.get(position).getKodePenilaian());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view) {
                if (Preferences.getKey_KodePenilain(context).isEmpty()){
                    Preferences.setKey_KodePenilain(context,dataPenilaianItems.get(position).getKodePenilaian());

                }else {
                    Preferences.clearKeyKodePenilian(context);
                    Preferences.setKey_KodePenilain(context,dataPenilaianItems.get(position).getKodePenilaian());
                }
                mlistener.onAlertDialogChange(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPenilaianItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txKodePenilaian;

        public MyViewHolder(View itemView) {
            super(itemView);
            txKodePenilaian = (TextView)itemView.findViewById(R.id.TvKodeNilai);

        }
    }
}
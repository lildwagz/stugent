package ei.eseptiyadi.aps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.model.inrombel.DataMapelItem;
import ei.eseptiyadi.aps.views.ActivityClass;
import ei.eseptiyadi.aps.views.DatapenilaianActivity;
import ei.eseptiyadi.aps.views.InputPenilaian;

public class DataPenilaianAdapter extends RecyclerView.Adapter<DataPenilaianAdapter.MyViewHolder> {

    Context context;
    List<DataMapelItem> dataMapelItems;
    String getNis,NamaSis;

    public DataPenilaianAdapter(Context context, List<DataMapelItem> dataMapelItems,String NamaSis,String getNis) {
        this.context = context;
        this.dataMapelItems = dataMapelItems;
        this.getNis = getNis;
        this.NamaSis = NamaSis;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_datapenilaian, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.namaMapel.setText(dataMapelItems.get(position).getKodeMapel());
//        holder.namaMapel.setText("contoh");
        holder.DetailMapel.setText(dataMapelItems.get(position).getNamaMatapelajaran());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(context, InputPenilaian.class);
                pindah.putExtra("KODEMAPEL",dataMapelItems.get(position).getKodeMapel());
                pindah.putExtra("DESCMAPEL",dataMapelItems.get(position).getNamaMatapelajaran());
                pindah.putExtra("GETNIS",getNis);
                pindah.putExtra("GETNAME",NamaSis);

//                Toast.makeText(context,dataMapelItems.get(position).getNamaMatapelajaran(), Toast.LENGTH_SHORT).show();


                context.startActivity(pindah);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataMapelItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView namaMapel, DetailMapel;
        public MyViewHolder( View itemView) {
            super(itemView);
            namaMapel = (TextView)itemView.findViewById(R.id.TvMapel);
            DetailMapel = (TextView)itemView.findViewById(R.id.TvDetailMapel);
        }
    }
}

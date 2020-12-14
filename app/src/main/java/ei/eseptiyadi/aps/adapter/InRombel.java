package ei.eseptiyadi.aps.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ei.eseptiyadi.aps.R;
import ei.eseptiyadi.aps.model.deleteData.ResponseDeleteSiswa;
import ei.eseptiyadi.aps.model.inrombel.SiswainrombelItem;
import ei.eseptiyadi.aps.network.ApiServices;
import ei.eseptiyadi.aps.network.RetrofitClient;
import ei.eseptiyadi.aps.views.DatapenilaianActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InRombel extends RecyclerView.Adapter<InRombel.MyViewHolder> {

    Context context;
    List<SiswainrombelItem> siswainrombelItems;
    String getTA;

    public InRombel(Context context, List<SiswainrombelItem> siswainrombelItems, String getTA) {
        this.context = context;
        this.siswainrombelItems = siswainrombelItems;
        this.getTA = getTA;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activitykelas, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txtSiswa.setText(siswainrombelItems.get(position).getNamaSiswa());
        holder.txtNim.setText(siswainrombelItems.get(position).getNIS());
        String kodeNisn = siswainrombelItems.get(position).getNIS();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vieew) {
                String NamaSiswa = siswainrombelItems.get(position).getNamaSiswa();
                String NisSiswa = siswainrombelItems.get(position).getNIS();

                Intent pindah = new Intent(context, DatapenilaianActivity.class);
                pindah.putExtra("NAMASISWA",NamaSiswa);
                pindah.putExtra("NISSWA",NisSiswa);

                context.startActivity(pindah);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Yakin ingin menghapus data ?");
                dialog
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ApiServices apiServices = RetrofitClient.getInstance();
                                Call<ResponseDeleteSiswa> responseDelSiswaCall = apiServices.delSiswa(kodeNisn);
                                responseDelSiswaCall.enqueue(new Callback<ResponseDeleteSiswa>() {
                                    @Override
                                    public void onResponse(Call<ResponseDeleteSiswa> call, Response<ResponseDeleteSiswa> response) {
                                        if(response.isSuccessful()){
                                            if (response.body().isStatus()){
                                                Toast.makeText(context.getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(context.getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(context.getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseDeleteSiswa> call, Throwable t) {

                                    }
                                });

                            }
                        });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswainrombelItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtSiswa;
        TextView txtNim;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtSiswa = (TextView)itemView.findViewById(R.id.TvNamasiswa);
            txtNim = (TextView)itemView.findViewById(R.id.TvNisn);
        }
    }
}

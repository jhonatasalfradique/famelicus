package br.ufrj.cos.famelicus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by guigs on 12/07/2015.
 */
public class PAsVisiveisAdapter extends BaseAdapter {

    private ArrayList listData;
    private LayoutInflater layoutInflater;

    public PAsVisiveisAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.lista_pa_visivel, null);
            holder = new ViewHolder();
            holder.id_pa = (TextView) convertView.findViewById(R.id.id_pa);
            holder.nome_pa = (TextView) convertView.findViewById(R.id.nome_pa);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PontoAlimentacao PA = (PontoAlimentacao) listData.get(position);
 //       String id = Integer.toString(PA.getId());
        holder.nome_pa.setText(PA.getNome());
        holder.id_pa.setText(PA.getId());


        return convertView;
    }

    static class ViewHolder {
        TextView id_pa;
        TextView nome_pa;

    }
}

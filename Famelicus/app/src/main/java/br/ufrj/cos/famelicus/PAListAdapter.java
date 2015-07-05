package br.ufrj.cos.famelicus;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by guigs on 30/06/2015.
 */
public class PAListAdapter extends BaseAdapter{
    private ArrayList listData;
    private LayoutInflater layoutInflater;

    public PAListAdapter(Context context, ArrayList listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_pa, null);
            holder = new ViewHolder();
            holder.nome = (TextView) convertView.findViewById(R.id.nome);
            holder.hatualizacao = (TextView) convertView.findViewById(R.id.horaAtualizacao);
            holder.img_estado= (ImageView) convertView.findViewById(R.id.estado);
            holder.img_situacao = (ImageView) convertView.findViewById(R.id.situacao);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PontoDeAlimentacao PA = (PontoDeAlimentacao) listData.get(position);
        holder.nome.setText(PA.getNome());
        holder.hatualizacao.setText(PA.getUltimaAtualizacao().toString());
        switch(PA.getSituacao().getSituacaodaFila()){
            case naoConhecido:
                holder.img_situacao.setImageResource(R.mipmap.nao_conhecido);
                break;
            case vazia:
                holder.img_situacao.setImageResource(R.mipmap.bateria_vazia);
                break;
            case filaPequena:
                holder.img_situacao.setImageResource(R.mipmap.bateria_pequena);
                break;
            case filaMedia:
                holder.img_situacao.setImageResource(R.mipmap.bateria_media);
                break;
            case filaGrande:
                holder.img_situacao.setImageResource(R.mipmap.bateria_grande);
                break;
            case lotado:
                holder.img_situacao.setImageResource(R.mipmap.bateria_lotado);
                break;
        }

        switch(PA.getSituacao().getFuncionamento()){
            case naoConhecido:
                holder.img_estado.setImageResource(R.mipmap.nao_conhecido);
                break;
            case aberto:
                holder.img_estado.setImageResource(R.mipmap.aberto);
                break;
            case fechado:
                holder.img_estado.setImageResource(R.mipmap.fechado);
                break;
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView img_estado;
        ImageView img_situacao;
        TextView nome;
        TextView hatualizacao;

    }
}




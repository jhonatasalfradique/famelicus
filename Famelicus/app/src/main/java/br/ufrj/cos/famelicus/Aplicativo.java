package br.ufrj.cos.famelicus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.content.Intent;
import android.util.Log;

import android.app.Service;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import static android.content.Context.*;

public class Aplicativo{

	private ArrayList<PontoAlimentacao> ListaPA;

    //variavel pra guardar a posicao do pa na lista, ordenado pelo id
    //private ArrayList<Integer> posicao;

	private double VersaoBD;

	private Date HorariodeAtualizacaoBD;

	private GeoPt Localizacao;

	private Proxy proxy;

	private InterfaceGPS interfaceGPS;

	private TratadorQRCode tratadorQRCode;

    private Context mContext;

    public Aplicativo(Context mContext){
        this.mContext = mContext;
        proxy = new Proxy();
        ListaPA = new ArrayList();
        //posicao = new ArrayList<Integer>();
        proxy.execute("1", "http://ec2-52-24-137-151.us-west-2.compute.amazonaws.com:8080/famelicus-servidor/api/retornarTudo");
        this.setListaPAString(proxy.json);
        this.setVersaoBDString(proxy.json);
    }


    public Aplicativo() {
        proxy = new Proxy();
        ListaPA = new ArrayList();
        //posicao = new ArrayList<Integer>();
        proxy.execute("1", "http://ec2-52-24-137-151.us-west-2.compute.amazonaws.com:8080/famelicus-servidor/api/retornarTudo");
        this.setListaPAString(proxy.json);
        this.setVersaoBDString(proxy.json);
    }

    public Aplicativo(String lista, double versao) {
        proxy = new Proxy();
        ListaPA = new ArrayList();
        //posicao = new ArrayList<Integer>();
        this.setListaPAString(lista);
        this.setVersaoBD(versao);
    }

    public boolean ValidarQRCode(String id) {

        String[] ids = {"1", "2", "3","4", "5", "6", "7", "8",
                "9", "10", "11", "12", "13", "14", "15", "16"};
        if( Arrays.asList(ids).contains(id)){

            return true;
        }

		return false;
	}

	public void LigarGPS() {

        final LocationManager manager = (LocationManager)  mContext.getSystemService (Context.LOCATION_SERVICE);

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
           buildAlertMessageNoGps();
        }
	}

    //acho q nao precisa mais dessa funcao, ja que api ja resolve esse problema
	public Boolean CompararLocalizacao(GeoPt location) {
		return null;
	}

    public void InformarSituacao(double versao, int id, SituacaoDoPA situacao){
        this.proxy.execute("3", Double.toString(versao), Integer.toString(id), situacao.getFuncionamento().toString(), situacao.getSituacaoDaFila().toString());

    }

	private void SolicitarColaboracao(PontoAlimentacao pontoAlimentacao) {
        Intent intent = new Intent(mContext, ColaborarActivity.class);
	}

	private List<Integer> ListarPAVisiveis() {
		return null;
	}

	public boolean VerificarVersaoBD(double versaoBDServidor) {
		return false;
	}

	private boolean AtualizarDadosPersistentes(List<Integer> listaDados) {
		return false;
	}

	private void AtualizarDadosDinamicos(List<SituacaoDoPA> Dados) {
        for(PontoAlimentacao p:ListaPA){
            int i = 0;
            p.setSituacao(Dados.get(i));
            i++;
        }
	}

	public PontoAlimentacao BuscarPontoAlimentacao(int id) {
        PontoAlimentacao ret = new PontoAlimentacao();
		for(PontoAlimentacao p: ListaPA){
            if(p.getId()==id){
                ret = p;
            }
        }
        return ret;
	}

	public boolean VerificarHorarioValido(Date hora) {
		return false;
	}

	public void ListarPontos() {

	}

	public boolean VerificarDisponibilidadeGPS() {
		return false;
	}

	public GeoPt getLocalizacao() {
		return null;
	}

	public Boolean ChecarVisibilidade() {
		Log.d("checar visibilidade", "checada");

        return null;
	}
        
    public float CalcularProximidade(int paIndex){
        PontoAlimentacao pa = this.ListaPA.get(paIndex);
        return pa.getLocalizacao().distanceTo(this.getLocalizacao());
    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Para o aplicativo funcionar corretamente, precisamos que o GPS esteja ligado, gostaria de liga-lo?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        mContext.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    public Date getHorariodeAtualizacaoBD() {
        return HorariodeAtualizacaoBD;
    }

    public void setHorariodeAtualizacaoBD(Date horariodeAtualizacaoBD) {
        HorariodeAtualizacaoBD = horariodeAtualizacaoBD;
    }

    public void setLocalizacao(GeoPt localizacao) {
        Localizacao = localizacao;
    }

    public double getVersaoBD() {
        return VersaoBD;
    }

    public void setVersaoBD(double versaoBD) {
        VersaoBD = versaoBD;
    }

    public boolean setVersaoBDString(String versaojson){
        JsonParser parser = new JsonParser();
        JsonElement jObj = parser.parse(versaojson).getAsJsonObject().get("versao");
        double versao = jObj.getAsDouble();
        setVersaoBD(versao);
        return true;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public InterfaceGPS getInterfaceGPS() {
        return interfaceGPS;
    }

    public void setInterfaceGPS(InterfaceGPS interfaceGPS) {
        this.interfaceGPS = interfaceGPS;
    }

    public TratadorQRCode getTratadorQRCode() {
        return tratadorQRCode;
    }

    public void setTratadorQRCode(TratadorQRCode tratadorQRCode) {
        this.tratadorQRCode = tratadorQRCode;
    }

    public ArrayList<PontoAlimentacao> getListaPA() {
        return ListaPA;
    }

    public void setListaPAString(String listaJson) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        Log.d("lista json", listaJson);
        JsonElement estadoroot = parser.parse(listaJson).getAsJsonObject().get("estado");
        String estado = estadoroot.getAsString();
        if(estado.equals("aberto")){
            JsonElement jObj = parser.parse(listaJson).getAsJsonObject().get("PAs");
            JsonArray jArray = jObj.getAsJsonArray();
            //Log.d("jArray", jArray.toString());

            if(!ListaPA.isEmpty()){
                ListaPA.clear();
            }

            for(JsonElement obj: jArray){
                int count =0;
                PontoAlimentacao pa = gson.fromJson(obj, PontoAlimentacao.class);
                ListaPA.add(pa);
                //posicao.add(pa.getId(),count);
                Log.d("PA", pa.toString());
                count ++;
            }
        }

    }

    public void setListaPA(ArrayList<PontoAlimentacao> listaPA){

        this.ListaPA = listaPA;
        for(PontoAlimentacao pa: ListaPA){
            int count =0;
            //posicao.add(pa.getId(),count);
            Log.d("PA", pa.toString());
            count ++;
        }
    }

    public PontoAlimentacao getPaByID(int paId){
        PontoAlimentacao ret = new PontoAlimentacao();
        for(PontoAlimentacao obj:ListaPA){
            if(obj.getId()==paId){
                ret = obj;
                break;
            }

        }
        return ret;
    }

    public String generateString(){
        ArrayList<PontoAlimentacao> jsonarray = new ArrayList();
        JsonElement listaPA;
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        for(PontoAlimentacao pa:ListaPA){
            jsonarray.add(pa);
        }
        listaPA = gson.toJsonTree(jsonarray);
        object.add("PAs", listaPA);
        String jsonstring = gson.toJson(object);
        return jsonstring;

    }

}

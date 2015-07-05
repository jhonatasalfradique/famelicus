package br.ufrj.cos.famelicus;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Aplicativo {

	private ArrayList<PontoDeAlimentacao> ListaPA;

	private int VersaoBD;

	private Date HorariodeAtualizacaoBD;

	private GeoPt Localizacao;

	private Proxy proxy;

	private InterfaceGPS interfaceGPS;

	private TratadorQRCode tratadorQRCode;

	//private InterfaceUsuario interface;


    public Aplicativo() {
        proxy = new Proxy();
        ListaPA = new ArrayList();
        this.setListaPA(proxy.pedirBDPersistente());
    }

    public boolean ValidarQRCode() {
		return false;
	}

	public void UsarGPS() {

	}

	public Boolean CompararLocalizacao(GeoPt location) {
		return null;
	}

	private void SolicitarColaboracao(PontoDeAlimentacao pontoAlimentacao) {
        //Intent intent = new Intent(MainActivity.this, ColaborarActivity.class);
	}

	private List<Integer> ListarPAVisiveis() {
		return null;
	}

	public void InformarSituacao() {

	}

	public void PedirSituacao() {

	}

	public boolean VerificarVersaoBD(int versaoBDServidor) {
		return false;
	}

	private boolean AtualizarDadosPersistentes(List<Integer> listaDados) {
		return false;
	}

	private void AtualizarDadosDinamicos(List<SituacaodoPontodeAlimentacao> Dados) {
        for(PontoDeAlimentacao p:ListaPA){
            int i = 0;
            p.setSituacao(Dados.get(i));
            i++;
        }
	}

	public PontoDeAlimentacao BuscarPontoAlimentacao(int id) {
        PontoDeAlimentacao ret = new PontoDeAlimentacao();
		for(PontoDeAlimentacao p: ListaPA){
            if(p.getID()==id){
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
        
    public float CalcularProximidade(int paId){
            return (float)0.1;
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

    public int getVersaoBD() {
        return VersaoBD;
    }

    public void setVersaoBD(int versaoBD) {
        VersaoBD = versaoBD;
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

    public ArrayList<PontoDeAlimentacao> getListaPA() {
        return ListaPA;
    }

    public void setListaPA(String listaJson) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement jObj = parser.parse(listaJson).getAsJsonObject().get("values");
        JsonArray jArray = jObj.getAsJsonArray();
        //Log.d("jArray", jArray.toString());

        //ArrayList<PontoDeAlimentacao> listData = new ArrayList();
        if(!ListaPA.isEmpty()){
            ListaPA.clear();
        }
        for(JsonElement obj: jArray){
            PontoDeAlimentacao pa = gson.fromJson(obj, PontoDeAlimentacao.class);
            ListaPA.add(pa);
            Log.d("PA", pa.toString());
        }

    }
}

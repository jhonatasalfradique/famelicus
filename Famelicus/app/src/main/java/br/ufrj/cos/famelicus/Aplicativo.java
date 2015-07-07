package br.ufrj.cos.famelicus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static android.content.Context.*;

public class Aplicativo {

    //bora usar ArrayList eh bem melhor, List nao tem nenhum metodo.
	private ArrayList<PontoAlimentacao> ListaPA;

	private double VersaoBD;

	private Date HorariodeAtualizacaoBD;

	private GeoPt Localizacao;

	private Proxy proxy;

	private InterfaceGPS interfaceGPS;

	private TratadorQRCode tratadorQRCode;

    Context mContext;
    public Aplicativo(Context mContext){
        this.mContext = mContext;
        proxy = new Proxy();
        ListaPA = new ArrayList();
        this.setListaPA(proxy.pedirSituacao());
    }


    public Aplicativo() {
        proxy = new Proxy();
        ListaPA = new ArrayList();
        this.setListaPA(proxy.pedirSituacao());
    }

    public boolean ValidarQRCode(String id) {

        if( id.equals("2")){

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

	public Boolean CompararLocalizacao(GeoPt location) {
		return null;
	}

	private void SolicitarColaboracao(PontoAlimentacao pontoAlimentacao) {
        //Intent intent = new Intent(MainActivity.this, ColaborarActivity.class);
	}

	private List<Integer> ListarPAVisiveis() {
		return null;
	}

    //acho q nao precisa, podemos chamar diretamente do proxy quando usuario clicar no botao, pois proxy faz parte do aplicativo
	public void InformarSituacao() {

	}

    //acho q nao precisa, podemos chamar diretamente do proxy quando usuario clicar no botao, pois proxy faz parte do aplicativo
	public void PedirSituacao() {

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
        
    public float CalcularProximidade(int paId){
            return (float)0.1;
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

    public ArrayList<PontoAlimentacao> getListaPA() {
        return ListaPA;
    }

    public void setListaPAString(String listaJson) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement jObj = parser.parse(listaJson).getAsJsonObject().get("PAs");
        JsonArray jArray = jObj.getAsJsonArray();
        //Log.d("jArray", jArray.toString());

        if(!ListaPA.isEmpty()){
            ListaPA.clear();
        }

        for(JsonElement obj: jArray){
            PontoAlimentacao pa = gson.fromJson(obj, PontoAlimentacao.class);
            ListaPA.add(pa);
            Log.d("PA", pa.toString());
        }
    }

    public void setListaPA(ArrayList<PontoAlimentacao> listaPA){
        this.ListaPA = listaPA;
    }
}

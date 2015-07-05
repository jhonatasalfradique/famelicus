package br.ufrj.cos.famelicus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;

import java.util.List;
import java.util.Date;

import static android.content.Context.*;

public class Aplicativo {

	private List<PontoDeAlimentacao> ListaPA;

	private int VersaoBD;

	private Date HorariodeAtualizacaoBD;

	private GeoPt Localizacao;

	private Proxy proxy;

	private InterfaceGPS interfaceGPS;

	private TratadorQRCode tratadorQRCode;

    Context mContext;
    public Aplicativo(Context mContext){
        this.mContext = mContext;
    }


	public boolean ValidarQRCode() {

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

	private void SolicitarColaboracao(PontoDeAlimentacao pontoAlimentacao) {

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

	private void AtualizarDadosDinamicos(List<Vector<Integer,Boolean,Integer>> Dados) {

	}

	public PontoDeAlimentacao BuscarPontoAlimentacao(int id) {
		return null;
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
}

package br.ufrj.cos.famelicus;

import java.util.List;
import java.util.Date;

public class Aplicativo {

	private List<PontoDeAlimentacao> ListaPA;

	private int VersaoBD;

	private Date HorariodeAtualizacaoBD;

	private GeoPt Localizacao;

	private Proxy proxy;

	private InterfaceGPS interfaceGPS;

	private TratadorQRCode tratadorQRCode;

	//private InterfaceUsuario interface;

	public boolean ValidarQRCode() {
		return false;
	}

	public void UsarGPS() {

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

}

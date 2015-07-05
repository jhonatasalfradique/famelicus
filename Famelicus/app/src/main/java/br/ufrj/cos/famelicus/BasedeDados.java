package br.ufrj.cos.famelicus;

import java.util.List;

public class BasedeDados {

	private List<PontoDeAlimentacao> ListaPontosdeAlimentacao;

	private List<Boolean> ListaDePermissao;

	private int Versao;

	private int HorariodeAtualizacao;

	public void Atualizar() {

	}

	public void AtualizarDadosEstaticos() {

	}

	public boolean ValidarQRCode() {
		return false;
	}

}

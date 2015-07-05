package br.ufrj.cos.famelicus;

import java.util.Date;
import java.util.List;

public class PontoDeAlimentacao {

	private int ID;

	private String Nome;

	private GeoPt Localizacao;

	private boolean Situacao;

	private int Fila;

	private int RegiaoVisibilidade;

	private Date UltimaAtualizacao;

	private Date UltimaColaboracao;

	public void AtualizarAtributosPersistentes(String nome, GeoPt localizacao, int regiaoVisivel) {

	}

	public void SetSituacao(boolean aberto) {

	}

	public void SetFila(int estado) {

	}

	public List<String> Exibir() {
		return null;
	}

	public GeoPt getLocalizacao() {
		return null;
	}

}

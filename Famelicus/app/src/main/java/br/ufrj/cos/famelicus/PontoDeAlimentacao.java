package br.ufrj.cos.famelicus;

import java.util.Date;
import java.util.List;

public class PontoDeAlimentacao {

	private int ID;

	private String Nome;

	private GeoPt Localizacao;

	private SituacaodoPontodeAlimentacao Situacao;

	private String UltimaAtualizacao;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getNome() {
            return Nome;
        }

        public void setNome(String Nome) {
            this.Nome = Nome;
        }

        public GeoPt getLocalizacao() {
            return Localizacao;
        }

        public void setLocalizacao(GeoPt Localizacao) {
            this.Localizacao = Localizacao;
        }

        public SituacaodoPontodeAlimentacao getSituacao() {
            return Situacao;
        }

        public void setSituacao(SituacaodoPontodeAlimentacao Situacao) {
            this.Situacao = Situacao;
        }

    public String getUltimaAtualizacao() {
        return UltimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        UltimaAtualizacao = ultimaAtualizacao;
    }

    public void AtualizarAtributosPersistentes(String nome, GeoPt localizacao) {

	}

	public List<String> Exibir() {
		return null;
	}
        
        @Override
        public String toString(){
            return "[ID= " +ID + ", Nome= " + Nome +", localizacao={" + Localizacao.toString()+"}" + ", situacao={" + Situacao.toString()+"}" + ", UltimaAtualizacao="+ UltimaAtualizacao + "]";
        }

}

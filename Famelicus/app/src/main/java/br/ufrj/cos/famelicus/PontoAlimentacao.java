package br.ufrj.cos.famelicus;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;
import br.ufrj.cos.famelicus.GeoPt;
import br.ufrj.cos.famelicus.SituacaoDoPA;
//import br.ufrj.cos.famelicus.Funcionamento;
//import br.ufrj.cos.famelicus.SituacaoDaFila;
import java.io.Serializable;
import java.util.ArrayList;


public class PontoAlimentacao{
    //private static final long serialVersionUID = -7788619177798333712L;


    private int id;
    private String nome;
    private GeoPt localizacao;
    private SituacaoDoPA situacao;
    private String ultimaAtualizacao;

    public PontoAlimentacao() {
        //get the default SituacaoDoPA for init;
        this.situacao = SituacaoDoPA.getDefault();

        //criando localizacao default
        Float defaultXY = (float) 0;
        this.localizacao = new GeoPt(defaultXY, defaultXY);
    }


    //only for test purposes
//    public PontoAlimentacao(int id, String nome, GeoPt localizacao) {
//        setId(id);
//        setNome(nome);
//        setLocalizacao(localizacao);
//        this.situacao = SituacaoDoPA.getDefault();
//        this.ultimaActualizacao = MyTimer.getCurrentTime();
//        this.votos = new ArrayList<Voto>();
//    }
//    //only for test purpose
//    public PontoAlimentacao(int id, String nome, GeoPt localizacao, int situacaoFila, int funcionamento ) {
//        setId(id);
//        setNome(nome);
//        setLocalizacao(localizacao);
//        this.ultimaActualizacao = MyTimer.getCurrentTime();
//        this.situacao = new SituacaoDoPA(situacaoFila, funcionamento);
//        this.votos = new ArrayList<Voto>();
//    }


    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {

        this.nome = nome;
    }
    public void setLocalizacao(GeoPt localizacao) {this.localizacao = localizacao;}
    public void setLocalizacao(Float lat, Float lng) {this.localizacao.set(lat, lng);}
    public void setSituacao(SituacaoDoPA s){this.situacao = s;}
    public void setSituacao(int situacaoFila, int funcionamento){
        this.situacao.set(situacaoFila, funcionamento);
    }
    public void setultimaAtualizacao(String u){ this.ultimaAtualizacao = u;}
    //public ArrayList<Voto> obterVotos(){return this.votos;}

    //getters
    public int getId() {return id;}
    public String getNome() {return nome;}
    public GeoPt getLocalizacao() {return localizacao;}
    public SituacaoDoPA getSituacao(){return situacao;}
    public SituacaoDoPA.Funcionamento getFuncionamento(){return situacao.funcionamento;}
    public SituacaoDoPA.SituacaoDaFila getSituacaoDaFila(){return situacao.situacaoDaFila;}
    public int getSituacaoDaFilaInt(){return situacao.situacaoDaFila.getValue();}
    public int getFuncionamentoInt(){return situacao.funcionamento.getValue();}
    public String getultimaAtualizacao(){return ultimaAtualizacao;}



    public void AtualizarAtributosPersistentes(String nome, GeoPt localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public void updateSituacaoDaFila(SituacaoDoPA.SituacaoDaFila s){
        this.situacao.situacaoDaFila = s;
    }
    public void updateFuncionamento(SituacaoDoPA.Funcionamento f) {
        this.situacao.funcionamento =f;
    }

//    public int CompareTo(PontoAlimentacao other){
//        int i = this.getFuncionamento().compareTo(other.getFuncionamento());
//        if(i!=0) return i;
//
//        i = this.getSituacaoDaFila().compareTo(other.getSituacaoDaFila());
//        if(i!=0) return i;
//
//        return i;
//    }


//    public void writeToParcel(Parcel out, int flags){
//        out.writeString(Integer.toString(id));
//        out.writeString(nome);
//        out.writeString(localizacao.toString());
//        out.writeString(situacao.toString());
//        out.writeString(ultimaAtualizacao);
//    }
//    public int describeContents() {
//        return 0;
//    }

    @Override
    public String toString(){
        return "[id= " +id + ", nome= " + nome +", localizacao={" + localizacao.toString()+"}" + ", situacao={" + situacao.toString()+"}" + ", ultimaAtualizacao="+ ultimaAtualizacao + "]";
    }

}

package br.ufrj.cos.famelicus;

import java.io.Serializable;

public class Voto implements Serializable{
    private static final long serialVersionUID = 1L;
    public String hora;
    public int idPA;
    public SituacaoDoPA situacao;


    public void setSituacao(SituacaoDoPA s){this.situacao = s;}
    public String getHora(){return this.hora;}
    public void setHora(String h){this.hora = h;}
    public int getidPA(){return this.idPA;}
    public void setidPA(int id){this.idPA = id;}
    public SituacaoDoPA getSituacao(){return this.situacao;}

}

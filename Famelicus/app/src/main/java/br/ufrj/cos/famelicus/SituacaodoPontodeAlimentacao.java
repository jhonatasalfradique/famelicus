package br.ufrj.cos.famelicus;

/**
 * Created by guigs on 04/07/2015.
 */
public class SituacaodoPontodeAlimentacao {
    public enum SituacaodaFila{lotado, filaGrande, filaMedia, filaPequena, vazia, naoConhecido};
    public enum Funcionamento{aberto, fechado, naoConhecido};
    private SituacaodaFila situacaodaFila;
    private Funcionamento funcionamento;

    public SituacaodaFila getSituacaodaFila() {
        return situacaodaFila;
    }

    public void setSituacaodaFila(SituacaodaFila situacaodaFila) {
        this.situacaodaFila = situacaodaFila;
    }

    public Funcionamento getFuncionamento() {
        return funcionamento;
    }

    public void setFuncionamento(Funcionamento funcionamento) {
        this.funcionamento = funcionamento;
    }

    @Override
    public String toString(){
      return "situacaodaFila:" + situacaodaFila.toString() +",funcionamento:"+funcionamento;
    };
}

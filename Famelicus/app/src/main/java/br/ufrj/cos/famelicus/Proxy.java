package br.ufrj.cos.famelicus;

import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.util.ArrayList;

public class Proxy {

//    public List<SituacaodoPontodeAlimentacao> pedirSituacao(){
////        for(int i=0; i<15; i++){
////            PontoDeAlimentacao
////        }
//    };

    public void informarSituacao(Voto voto){
        //Enviar Voto para servidor
    };
    public int pedirVersaoBD(){
        return 1;
    };

    public String pedirBDPersistente(){
        //ArrayList<PontoDeAlimentacao> lista = new ArrayList();
        JSONArray jsonarray = new JSONArray();
        Gson gsonobj = new Gson();

        for(int i=0; i<25; i++){
            GeoPt geopt = new GeoPt();
            geopt.setLatitude(i);
            geopt.setLongitude(i);

            int situacaoInt = i%6;
            int estadoInt = i%3;

            SituacaodoPontodeAlimentacao situacao = new SituacaodoPontodeAlimentacao();

            switch(situacaoInt){
                case 0:
                    situacao.setSituacaodaFila(SituacaodoPontodeAlimentacao.SituacaodaFila.lotado);
                    break;
                case 1:
                    situacao.setSituacaodaFila(SituacaodoPontodeAlimentacao.SituacaodaFila.filaGrande);
                    break;
                case 2:
                    situacao.setSituacaodaFila(SituacaodoPontodeAlimentacao.SituacaodaFila.filaMedia);
                    break;
                case 3:
                    situacao.setSituacaodaFila(SituacaodoPontodeAlimentacao.SituacaodaFila.filaPequena);
                    break;
                case 4:
                    situacao.setSituacaodaFila(SituacaodoPontodeAlimentacao.SituacaodaFila.vazia);
                    break;
                case 5:
                    situacao.setSituacaodaFila(SituacaodoPontodeAlimentacao.SituacaodaFila.naoConhecido);
                    break;
            }
            switch(estadoInt){
                case 0:
                    situacao.setFuncionamento(SituacaodoPontodeAlimentacao.Funcionamento.aberto);
                    break;
                case 1:
                    situacao.setFuncionamento(SituacaodoPontodeAlimentacao.Funcionamento.fechado);
                    break;
                case 2:
                    situacao.setFuncionamento(SituacaodoPontodeAlimentacao.Funcionamento.naoConhecido);
                    break;
            }

            PontoDeAlimentacao p = new PontoDeAlimentacao();
            p.setNome("nome" + i);
            p.setID(i);

            p.setLocalizacao(geopt);
            p.setSituacao(situacao);
            p.setUltimaAtualizacao("Hora:"+i);
            jsonarray.put(p);
        }
        String jsonstring = gsonobj.toJson(jsonarray);
        return jsonstring;
    };
}

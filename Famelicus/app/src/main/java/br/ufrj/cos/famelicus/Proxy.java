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

    public void informarSituacao(){};
    public void pedirVersaoBD(){};

    public String pedirBDPersistente(){
        //ArrayList<PontoDeAlimentacao> lista = new ArrayList();
        JSONArray jsonarray = new JSONArray();
        Gson gsonobj = new Gson();

        for(int i=0; i<25; i++){
            GeoPt geopt = new GeoPt();
            geopt.setLatitude(i);
            geopt.setLongitude(i);

            SituacaodoPontodeAlimentacao situacao = new SituacaodoPontodeAlimentacao();
            situacao.setFuncionamento(SituacaodoPontodeAlimentacao.Funcionamento.aberto);
            situacao.setSituacaodaFila(SituacaodoPontodeAlimentacao.SituacaodaFila.filaGrande);

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

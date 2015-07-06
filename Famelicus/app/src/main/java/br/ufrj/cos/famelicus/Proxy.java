package br.ufrj.cos.famelicus;

import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import java.util.ArrayList;
import br.ufrj.cos.famelicus.Voto;
public class Proxy {

//    public List<SituacaodoPontodeAlimentacao> pedirSituacao(){
////        for(int i=0; i<15; i++){
////            PontoDeAlimentacao
////        }
//    };

    public void informarSituacao(int paid, int fila, int situacao ){
        //Manually creating simple json to be send to the server.
        String toServer = "{paid : ";
        toServer += Integer.toString(paid) + ",";
        toServer +=  "fila : " + Integer.toString(fila) + ",";
        toServer += "situação : " + situacao +"}";
        //Implement methor to send to server.
        System.out.println(toServer);
    };

    public int pedirVersaoBD(){
        int version = 0;
        return version;
    };

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

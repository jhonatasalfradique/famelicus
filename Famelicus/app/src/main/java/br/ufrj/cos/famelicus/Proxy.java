package br.ufrj.cos.famelicus;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import java.util.ArrayList;
public class Proxy {

    //metodo que chama retornarSituacao da classe ufrj.cos.famelicus.servidor.controller.GerenciadorDeSolicitacoes
    //ja retorna a Lista de PA para carregar na classe br.ufrj.famelicus.Aplicativo
    public ArrayList<PontoAlimentacao> pedirSituacao(){
        String listaJson = this.createretornasituacao();

        ArrayList<PontoAlimentacao> ListaPA = new ArrayList();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement jObj = parser.parse(listaJson).getAsJsonObject().get("PAs");
        JsonArray jArray = jObj.getAsJsonArray();
        //Log.d("jArray", jArray.toString());

        for(JsonElement obj: jArray){
            PontoAlimentacao pa = gson.fromJson(obj, PontoAlimentacao.class);
            ListaPA.add(pa);
            //Log.d("PA", pa.toString());
        }
        return ListaPA;
    };

    //metodo para enviar um voto para servidor. Nao vejo para nosso caso a necessidade da classe voto. Arrumar
    public void informarSituacao(double versao, int paID, SituacaoDoPA situacao){
        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        //SituacaoDoPA situacao = voto.getSituacao();
        object.addProperty("v", Double.toString(versao));
        object.addProperty("id", paID);
        object.addProperty("funcionamento", situacao.getFuncionamento().toString());
        object.addProperty("situacaoDaFila", situacao.getSituacaoDaFila().toString());

        String jsonstring = gson.toJson(object);
        //Implement methor to send to server.
        Log.d("informarsituacao", jsonstring);
    };

    //classe que abstrai o servidor, e chama ufrj.cos.famelicus.servidor.controller.GerenciadorDeSolicitacoes.retornarsituacao()
    // mas devolve apenas a versao do servidor para a classe br.ufrj.famelicus.Aplicativo
    public double pedirVersaoBD(){
        String json = this.createretornasituacao();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement obj = parser.parse(json).getAsJsonObject().get("versao");
        double ret = obj.getAsDouble();
        return ret;
    };

    //metodo que chama retornarTudo da classe ufrj.cos.famelicus.servidor.controller.GerenciadorDeSolicitacoes
    //ja retorna a Lista de PA para carregar na classe br.ufrj.famelicus.Aplicativo
    //public String pedirBDPersistente(){
//    };

    //cria a string json teoricamente igual a que servidor ira mandar se servidor estiver aberto e executar
    //ufrj.cos.famelicus.servidor.controller.GerenciadorDeSolicitacoes.retornarTudo()
    public String createretornatudo(){
        JSONArray jsonarray = new JSONArray();
        JsonElement listaPA;
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        //versao do bd
        object.addProperty("versao", "1.00");
        // se o servidor esta em funcionamento ou nao
        object.addProperty("estado", "aberto");

        //gerar dinamicamente uma lista de pa que pega todas as situacoes possiveis
        for(int i=0; i<10; i++){
            float j = (float)i;
            GeoPt geopt = new GeoPt(j,j);

            int situacaoInt = i%6;
            int estadoInt = i%3;

            SituacaoDoPA situacao = new SituacaoDoPA(situacaoInt,estadoInt);

            PontoAlimentacao p = new PontoAlimentacao();
            p.setNome("nome" + i);
            p.setId(i);
            p.setLocalizacao(geopt);
            p.setSituacao(situacao);
            p.setultimaAtualizacao("Hora:" + i);
            jsonarray.put(p);

        }
        listaPA = gson.toJsonTree(jsonarray);

        object.add("PAs", listaPA);
        String jsonstring = gson.toJson(object);
        Log.d("retornatudo", jsonstring.toString());
        return jsonstring;
    }

    //cria a string json teoricamente igual a que servidor ira mandar quando estiver fechado
    public String ServidorOFF(){
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("versao", "1.00");
        object.addProperty("estado", "fechado");
        return gson.toJson(object);
    };

    //cria a string json teoricamente igual a que servidor ira mandar se servidor estiver aberto e executar funcao
    //ufrj.cos.famelicus.servidor.controller.GerenciadorDeSolicitacoes.retornarSituacao()
    public String createretornasituacao(){
        //JSONArray jsonarray = new JSONArray();

        ArrayList<PontoAlimentacao> jsonarray = new ArrayList();
        JsonElement listaPA;
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        //versao do bd
        object.addProperty("versao", "1.00");
        // se o servidor esta em funcionamento ou nao
        object.addProperty("estado", "aberto");
        //horario da ultima atualizacao
        object.addProperty("ultimaAtualizacao", "13:40");

        //gerar dinamicamente uma lista de pa que pega todas as situacoes possiveis
        for(int i=0; i<10; i++){
            float j = (float)i;
            GeoPt geopt = new GeoPt(j,j);

            int situacaoInt = i%6;
            int estadoInt = i%3;

            SituacaoDoPA situacao = new SituacaoDoPA(situacaoInt,estadoInt);

            PontoAlimentacao p = new PontoAlimentacao();
            p.setNome("nome" + i);
            p.setId(i);
            p.setLocalizacao(geopt);
            p.setSituacao(situacao);
            p.setultimaAtualizacao("HO:RA");
            //Log.d("hora de p", p.getultimaAtualizacao());
            //jsonarray.put(p);
            jsonarray.add(p);

        }
        //casa do guilhereme -22.8400541f, -43.2706262f
        //casa de jhonatas -22.4979104f, 43.060696f
        GeoPt geopt = new GeoPt(-22.8400541f, -43.2706262f);

        int situacaoInt = 4;
        int estadoInt = 1;

        SituacaoDoPA situacao = new SituacaoDoPA(situacaoInt,estadoInt);

        PontoAlimentacao p = new PontoAlimentacao();
        p.setNome("sala");
        p.setId(10);
        p.setLocalizacao(geopt);
        p.setSituacao(situacao);
        p.setultimaAtualizacao("HO:RA");
        jsonarray.add(p);

        listaPA = gson.toJsonTree(jsonarray);

        object.add("PAs", listaPA);
        String jsonstring = gson.toJson(object);
        Log.d("retornasituacao", jsonstring.toString());
        return jsonstring;
    }

    public void InformarSituacao2(double versao, int paID, SituacaoDoPA situacao) throws Exception{
        String str = "http://ec2-52-24-137-151.us-west-2.compute.amazonaws.com:8080/famelicus-servidor/api/adicionarVoto?";
        str += "v=" + Double.toString(versao) +"&id=" + Integer.toString(paID) + "&funcionamento=" + situacao.getFuncionamento().toString();
        str += "&situacaoDaFila=" + situacao.getSituacaoDaFila().toString();
        URL url = new URL(str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        String status = request.getResponseMessage();
        Log.d("response message", status);
//        JsonParser jp = new JsonParser(); //from gson
//        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
//        JsonObject rootobj = root.getAsJsonObject();
    }

    public String retornarTudo() throws Exception{
        String str = "http://ec2-52-24-137-151.us-west-2.compute.amazonaws.com:8080/famelicus-servidor/api/retornarTudo";
        URL url = new URL(str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        Gson gson = new Gson();
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        String jsonstring = gson.toJson(rootobj);
        Log.d("Servidor", jsonstring);
        return jsonstring;

    }

    public String retornarSituacao() throws IOException{
        String str = "http://ec2-52-24-137-151.us-west-2.compute.amazonaws.com:8080/famelicus-servidor/api/retornarSituacao";
        URL url = new URL(str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        Gson gson = new Gson();
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        String jsonstring = gson.toJson(rootobj);
        Log.d("Servidor situacao", jsonstring);
        return jsonstring;

    }
}

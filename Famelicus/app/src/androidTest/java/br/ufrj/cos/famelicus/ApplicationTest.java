package br.ufrj.cos.famelicus;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    PontoAlimentacao pa = new PontoAlimentacao();

    public ApplicationTest() {
        super(Application.class);

    }

/*

    //situacao e geolocalizacao ja definidos por default
    public void preCondicoes(){

        pa.setId(1);
        pa.setNome("PA");
        pa.setultimaAtualizacao("00:00");
    }


    public void getTests(){

        int actualId = 1;
        String actualNome = "PA";
        String actualUltimaAtualizacao = "00:00";

        int Idesperado = pa.getId();
        String nomeEsperado = pa.getNome();
        String AtualizacaoEsperado = pa.getultimaAtualizacao();


        // Assert Id
        assertEquals("getTests - error getId()", actualId, Idesperado);
        assertEquals("getTests - error getNome()", actualNome, nomeEsperado);
        assertEquals("getTests - error getultimaAtualizacao()", actualUltimaAtualizacao, AtualizacaoEsperado);

        System.out.print("geTest() OK");

    }


*/
}
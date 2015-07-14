package br.ufrj.cos.famelicus;

import junit.framework.TestCase;

/**
 * Created by Jhonatas on 13/07/2015.
 */
public class PontoDeAlimentacaoTestextendsTest extends TestCase {


    public void testGetTests() throws Exception {

        PontoAlimentacao pa = new PontoAlimentacao();
        pa.setId(1);
        pa.setNome("PA");
        pa.setultimaAtualizacao("00:00");
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

    }


    public void testAtualizarAtributosPersistentes() throws Exception {

        PontoAlimentacao pa = new PontoAlimentacao();
        pa.setNome("Nome");
        //Localizacao default
        GeoPt geo = new GeoPt(11.43f, 12.34f);
        pa.AtualizarAtributosPersistentes("NomeAlterado", geo);

        assertEquals("AtualizarAtributosPersistentesTest() - Nome", "NomeAlterado", pa.getNome());
        assertEquals("AtualizarAtributosPersistentesTest() - Geo", geo, pa.getLocalizacao());

    }

    public void testupdateSituacaoDaFilaFuncionamento() throws Exception {

        PontoAlimentacao pa = new PontoAlimentacao();
        SituacaoDoPA sit = new SituacaoDoPA(SituacaoDoPA.SituacaoDaFila.FilaGrande, SituacaoDoPA.Funcionamento.Aberto);

        pa.setSituacao(sit);
        pa.updateSituacaoDaFila(SituacaoDoPA.SituacaoDaFila.FilaMedia);
        pa.updateFuncionamento(SituacaoDoPA.Funcionamento.Fechado);

        assertEquals("testupdateSituacaoDaFila()) - SituacaoDaFila", SituacaoDoPA.SituacaoDaFila.FilaMedia, pa.getSituacaoDaFila());
        assertEquals("testupdateSituacaoDaFila()) - Funcionamento", SituacaoDoPA.Funcionamento.Fechado, pa.getFuncionamento());

    }







}
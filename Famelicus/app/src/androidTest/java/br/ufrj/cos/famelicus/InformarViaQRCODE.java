package br.ufrj.cos.famelicus;

import junit.framework.TestCase;

/**
 * Created by Jhonatas on 14/07/2015.
 */
public class InformarViaQRCODE extends TestCase {


    Aplicativo aplicativo = new Aplicativo();

    public void testValidarQRCODE() throws Exception{

        String id = "3";
        assertTrue(aplicativo.ValidarQRCode(id));

        String id_invalido = "100";
        assertTrue(!aplicativo.ValidarQRCode(id_invalido));

    }

    public void testInformarSituacao() throws Exception{


    }


}

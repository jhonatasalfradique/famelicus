package br.ufrj.cos.famelicus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ColaborarQRCODE extends ActionBarActivity {

    IntentIntegrator it;

    String id;
    Aplicativo aplicativo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaborar_qrcode);

        //aplicativo = new Aplicativo(this);
        String famelicusString = getIntent().getStringExtra("listaPA");
        double versao = Double.parseDouble(getIntent().getStringExtra("versao"));
        aplicativo = new Aplicativo(famelicusString, versao);
        IntentIntegrator.initiateScan(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_colaborar_qrcode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Log.d("ColaborarQRCODE", "Cancelado");
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
                Uri contentUri = data.getData();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("id", id);
                //intent.putExtra("nome", pa.getNome());
                startActivity(new Intent(this, MainActivity.class)
                        .setData(contentUri));
            } else {
                Log.d("ColaborarQRCODE", "Scanned");
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                id = result.getContents();

                if(aplicativo.ValidarQRCode(id)){
                    Uri contentUri = data.getData();

                    Intent intent = new Intent(this, ColaborarActivity.class);
                    intent.putExtra("id", id);
                    PontoAlimentacao pa = aplicativo.getPaByID(Integer.parseInt(id));
                    String famelicusString = aplicativo.generateString();
                    String versao = Double.toString(aplicativo.getVersaoBD());
                    intent.putExtra("listaPA", famelicusString);
                    intent.putExtra("versao", versao);
                    //intent.putExtra("nome", pa.getNome());
                    startActivity(new Intent(this, ColaborarActivity.class)
                            .setData(contentUri));
                } else{

                    Toast.makeText(this, "QRCODE Inválido! Tente Novamente.", Toast.LENGTH_LONG).show();
                    Uri contentUri = data.getData();
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("id", id);
                    //intent.putExtra("nome", pa.getNome());
                    startActivity(new Intent(this, MainActivity.class)
                            .setData(contentUri));
                }

            }
        } else {
            Log.d("MainActivity", "Weird");
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}

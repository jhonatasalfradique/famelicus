package br.ufrj.cos.famelicus;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ColaborarActivity extends Activity {

    Aplicativo aplicativo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaborar);

        final double versao = Double.parseDouble(getIntent().getStringExtra("versao"));
        String famelicusString = getIntent().getStringExtra("listaPA");
        final int id =  Integer.parseInt(this.getIntent().getStringExtra("id"));

        aplicativo = new Aplicativo(famelicusString, versao);
        //Log.d("estou aqui", "hello");
        final PontoAlimentacao pa = aplicativo.getPaByID(id);

        Spinner estado_spinner = (Spinner) findViewById(R.id.estado_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.estado_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_spinner.setAdapter(adapter);

        Spinner situacao_spinner = (Spinner) findViewById(R.id.situacao_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
            R.array.situacao_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        situacao_spinner.setAdapter(adapter2);

        String estado = estado_spinner.getSelectedItem().toString();
        String situacao = situacao_spinner.getSelectedItem().toString();

        TextView textView = (TextView)findViewById(R.id.nomePA);
       // PontoAlimentacao pa = aplicativo.getPaByID(Integer.parseInt(id));
        textView.setText(pa.getNome());

        Button colaborar = (Button) findViewById(R.id.btcolaborar);

        colaborar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ColaborarActivity.this, "Colaboracao Enviada", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ColaborarActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                aplicativo.InformarSituacao(versao, id, pa.getSituacao());
                startActivity(intent);

            }
        });



    }

    @Override
    protected void onPause(){
        super.onPause();
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_colaborar, menu);
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
}

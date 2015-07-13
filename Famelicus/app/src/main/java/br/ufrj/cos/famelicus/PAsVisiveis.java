package br.ufrj.cos.famelicus;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;


public class PAsVisiveis extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_visiveis);
        //ArrayList<PontoAlimentacao> list = getIntent().getParcelableArrayListExtra("listaPA");

        String json = getIntent().getStringExtra("listaPA");
        //Log.d("on new intent", json);
        ArrayList<PontoAlimentacao> listaPA = new ArrayList();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement jObj = parser.parse(json).getAsJsonObject().get("PAs");
        JsonArray jArray = jObj.getAsJsonArray();

        for(JsonElement obj: jArray){
            PontoAlimentacao pa = gson.fromJson(obj, PontoAlimentacao.class);
            listaPA.add(pa);
        }

        final ListView listView = (ListView)findViewById(R.id.list_PAsVisiveis);
        final PAsVisiveisAdapter adapter = new PAsVisiveisAdapter(this, listaPA);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                final String item = (String) parent.getItemAtPosition(position);
//                Log.d("clicado no item ", Long.toString((id)));
//
//            }
//        });
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pas_visiveis, menu);
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

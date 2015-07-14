package br.ufrj.cos.famelicus;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import android.app.IntentService;
import android.app.NotificationManager;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationResult;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Servico extends IntentService {
    Location location;
    Aplicativo famelicus;

    public Servico(){
        super("Servico");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
//        Location location = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);
//        String not = Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude());
//        Log.d("notification", not);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Builder noti = new NotificationCompat.Builder(this);
//        noti.setContentTitle("Fused Location");
//        noti.setContentText(location.getLatitude() + "," + location.getLongitude());
//        noti.setSmallIcon(R.mipmap.ic_launcher);
//        notificationManager.notify(1234, noti.build());
//        if (intent.hasExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED)) {
//
//            location = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);
//
//            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            Builder noti = new NotificationCompat.Builder(this);
//            noti.setContentTitle("Fused Location");
//            noti.setContentText(location.getLatitude() + "," + location.getLongitude());
//            noti.setSmallIcon(R.mipmap.ic_launcher);
//            notificationManager.notify(1234, noti.build());
//            Log.d("locationtesting", "accuracy: " + location.getAccuracy() + " lat: " + location.getLatitude() + " lon: " + location.getLongitude());
//        }

        String famelicusString = intent.getStringExtra("listaPA");
        double versao = Double.parseDouble(intent.getStringExtra("versao"));
        famelicus = new Aplicativo(famelicusString, versao);

        Intent notificationIntent = new Intent(this, PAsVisiveis.class );
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //this.startActivity(notificationIntent);

        ArrayList<PontoAlimentacao> lista = new ArrayList<PontoAlimentacao>();
        JsonElement listaPA;
        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        ArrayList<Geofence> geofences = new ArrayList<Geofence>(geofencingEvent.getTriggeringGeofences());

        for(Geofence obj: geofences){
            PontoAlimentacao pt = famelicus.getPaByID(Integer.parseInt(obj.getRequestId()));
            //Log.d(" geofence nome", pt.getNome());
            //HashMap<String, String> PA = new HashMap<String, String>(pt.getNome(), pt.toString());
            lista.add(pt);
        }
        listaPA = gson.toJsonTree(lista);
        object.add("PAs", listaPA);
        String jsonstring = gson.toJson(object);
        //notificationIntent.putParcelableArrayListExtra("listaPA", lista);
        Log.d("extras pa", jsonstring);
        notificationIntent.putExtra("listaPA", jsonstring);
        notificationIntent.putExtra("versao", Double.toString(famelicus.getVersaoBD()));


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        location = geofencingEvent.getTriggeringLocation();

        Builder noti = new NotificationCompat.Builder(this);
        noti.setContentTitle("Pas visiveis");
        noti.setContentIntent(pendingIntent);
        noti.setContentText(location.getLatitude() + "," + location.getLongitude());
        noti.setSmallIcon(R.mipmap.ic_launcher);
        notificationManager.notify(1234, noti.build());
        Log.d("triggerGeogence", "event: " + geofencingEvent.getGeofenceTransition() + " lat: " + location.getLatitude() + " lon: " + location.getLongitude());
    }



}

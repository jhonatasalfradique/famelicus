package br.ufrj.cos.famelicus;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

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
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationResult;

public class Servico extends IntentService {

    public Servico(){
        super(Servico.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
//        final Location location = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);
//        String not = Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude());
//        //Log.d("notification", not);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Builder noti = new NotificationCompat.Builder(this);
//        noti.setContentTitle("Fused Location");
//        noti.setContentText(location.getLatitude() + "," + location.getLongitude());
//        noti.setSmallIcon(R.mipmap.ic_launcher);
//        notificationManager.notify(1234, noti.build());
    }



}

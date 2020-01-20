package service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    public Context context = this;
    public Handler handler = null;
    public Runnable runnable = null;

    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(context, "Service Started!", Toast.LENGTH_SHORT).show();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                double randNo = getRandomDoubleBetweenRange(1,100);
                Toast.makeText(context, "Random No: "+ randNo, Toast.LENGTH_SHORT).show();
                handler.postDelayed(runnable, 2000);
            }
        };
        handler.postDelayed(runnable,2000);

    }

    public static double getRandomDoubleBetweenRange(double min, double max){
        return (Math.random()*((max - min)+1)) + min;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(runnable);
        Toast.makeText(context, "Service Stopped!", Toast.LENGTH_SHORT).show();
    }
}

package boardcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.deepak.notification.CreateChannel;
import com.deepak.notification.R;

public class BoardCastReceiverExample extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;


    public BoardCastReceiverExample(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean noConnectivity;

        notificationManagerCompat = NotificationManagerCompat.from(context);



        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){

            noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,
                    false
            );

            if (noConnectivity){
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                DisConnectedNotification();
            }
            else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                ConnectedNotification();
            }
        }
    }

    private void DisConnectedNotification(){

        Notification notification = new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.wifi)
                .setContentTitle("No connection")
                .setContentText("No Connection, Please connect")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

    private void ConnectedNotification(){

        Notification notification = new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.wifi)
                .setContentTitle("Connected")
                .setContentText("You have been connected successfully.")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();
        notificationManagerCompat.notify(2, notification);
    }
}

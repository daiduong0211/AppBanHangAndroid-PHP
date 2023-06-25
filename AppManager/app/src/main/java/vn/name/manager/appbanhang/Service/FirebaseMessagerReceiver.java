package vn.name.manager.appbanhang.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import vn.name.manager.appbanhang.R;
import vn.name.manager.appbanhang.activity.MainActivity;

public class FirebaseMessagerReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        //kiem tra va show thong bao
        if (message.getNotification()!=null){
            showNotification(message.getNotification().getTitle(),message.getNotification().getBody());
        }
        super.onMessageReceived(message);
    }

    private void showNotification(String title, String body) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String channelId = "noti";
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_MUTABLE); // chỉ định hành động khi nhấn vào thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),channelId)
                .setSmallIcon(R.drawable.unnamed) //set icon
                .setAutoCancel(true) // auto clear noti
                .setVibrate(new long[]{1000,1000,1000,1000}) // đặt kiểu rung cho thông báo
                .setOnlyAlertOnce(true) // âm thanh và kiểu rung chỉ phát 1 lần
                .setContentIntent(pendingIntent);  // đặt pendinginten khi nhắn vào thông báo
                builder = builder.setContent(customView(title,body)); // đặt nội dung cho thông báo
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // xử lí khi phiên bản SDK > phiên bản Oreo
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "web_app",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(0,builder.build()); // sử dụng hàm để hiện thị thông báo
        }
    }

    private RemoteViews customView(String title, String body){
        //tạo 1 remoteview có dạng notification layout
        RemoteViews remoteViews= new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.title_noti,title);
        remoteViews.setTextViewText(R.id.body_noti,body);
        remoteViews.setImageViewResource(R.id.imgnoti,R.drawable.unnamed);
        return remoteViews;
    }
}

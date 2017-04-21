package com.example.samsung.p0991_servicenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    private NotificationManager notificationManager;
    private Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotif();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotif() {
        //Создание активити для отображения подробностей
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.FILE_NAME, "somefile");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //Подготовка уведомления в статус-бар
        Notification.Builder builder = new Notification.Builder(MyService.this);
        //Установка флага, удаляющего уведомление из списка после нажатия
        builder.setAutoCancel(true);
        //Установка сообщения для статус-бара
        builder.setContentText("Notification's text in status-bar");
        //Установка стикера
        builder.setTicker("Notification's ticker");
        //Установка заголовка
        builder.setContentTitle("Notification's title");
        //Установка малой иконки
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //Подключение активити к записи
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        //Только для API не младше №16
//        builder.setSubText("The notification's subtext");
        //Создание строки в разворачивающемся списке уведомлений
        notification = builder.getNotification();
        //Отправка уведомления в статус-бар
        notificationManager.notify(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

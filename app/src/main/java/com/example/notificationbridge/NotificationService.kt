package com.example.notificationbridge

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        val packageName = sbn?.packageName ?: return

        // Проверяем, разрешено ли это приложение в настройках
        val prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val isAllowed = prefs.getBoolean(packageName, false)

        if (isAllowed) {
            val extras = sbn.notification.extras
            val title = extras.getString("android.title") ?: ""
            val text = extras.getString("android.text") ?: ""

            // Здесь в будущем можно будет отправлять данные на твой FastAPI или в Telegram
            Log.d("NotificationBridge", "Перехвачено от $packageName: $title - $text")
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }
}

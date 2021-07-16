package com.codecollapse.motionflick.models.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MotionFlickMessageService : FirebaseMessagingService() {

    private var TAG = "MotionFlickMessageService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (!remoteMessage.data.isNullOrEmpty()) {
            Log.d(TAG, "onMessageReceived: ${remoteMessage.data}")
        }
    }

    override fun onNewToken(firebaseToken: String) {
        super.onNewToken(firebaseToken)
        Log.d(TAG, "onNewToken: $firebaseToken")
    }
}
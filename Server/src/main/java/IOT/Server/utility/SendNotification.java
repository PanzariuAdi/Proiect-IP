package IOT.Server.utility;

import IOT.Server.dao.Conturi;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.util.concurrent.ExecutionException;

public class SendNotification {
    public static void send(String msg,String username) throws ExecutionException, InterruptedException {
        String response = null;
        Message message = Message.builder()
                .putData("message",msg)
                .setNotification(
                        Notification.builder()
                        .setTitle("STATUS")
                        .setBody(msg)
                        .build())
                .setToken(new Conturi().getToken(username))
                .build();
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            System.out.println("Couldn't send message!"+ e.getCause());
        }
        if(response!=null)
            System.out.println("notification sent! " + response);
        System.out.println(new Conturi().getToken(username));
    }
}

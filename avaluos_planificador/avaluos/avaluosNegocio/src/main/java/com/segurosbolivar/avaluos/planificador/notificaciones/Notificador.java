package com.segurosbolivar.avaluos.planificador.notificaciones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import com.segurosbolivar.avaluos.planificador.util.Constantes;
 
public class Notificador {

	   // Method to send Notifications from server to client end.
    public final static String AUTH_KEY_FCM = "AAAAJHeJWyY:APA91bH_mBxipgE8h4VzCF9x_JrJLSIIri1ZhLkRb3GItOiifYw9N_vtRYLyMQFjO4ID70m6nU-TvjPOXMq6WtLmQXrTX9vNdvf4XZvHwKFwCJU5IDj_-XQkC3FGWillJUcgwa0Av2DR";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
   
    public static void notificarCrearSolicitud(String codSolicitud,
    		String municipioDepartamento,
    		String tokenDispositivo) throws Exception {

        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        
        URL url = new URL(FMCurl);
        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject data = new JSONObject();
        data.put("to", tokenDispositivo.trim());
        data.put("priority","high");
        
        JSONObject notification = new JSONObject();
        notification.put("title", "Agro Plan");
        notification.put("body", "Tienes una nueva solicitud asignada en: "+municipioDepartamento+" con el codigo: "+codSolicitud);
        notification.put("sound", "default");
        notification.put("badge", 1);
		notification.put("icon", "ic_stat_ic_notificacion");
        notification.put("click_action", "NUEVA_SOLICITUD");
        notification.put("android_channel_id", "agroPlanChanelId");
		    
        data.put("notification", notification);
        
        JSONObject info = new JSONObject();
        info.put(Constantes.NTF_KEY_ACCION, Constantes.NTF_NUEVA_SOLICITUD); // Notification body
        info.put("codSolicitud", codSolicitud); 
        info.put("municipioDepartamento", municipioDepartamento);  
         
        data.put("data", info);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data.toString());
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

    }
    
   

}

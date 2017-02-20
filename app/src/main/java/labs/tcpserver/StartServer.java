package labs.tcpserver;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by abhinav.srivastava on 20/02/17.
 */

public class StartServer extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        startServer();
        return null;
    }

    void startServer(){
        try{
            ServerSocket ss = null;
            // Initialize Server Socket to listen to its opened port
            ss = new ServerSocket(6588);
            try {
                while (true) {
                    Socket s = null;
                    BufferedReader in = null;
                    try {
                        // Accept any client connection
                        s = ss.accept();
                        // Initialize Buffered reader to read the message from the client
                        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        // Get incoming message
                        String incomingMessage = in.readLine() + System.getProperty("line.separator");
                        // Use incomingMessage as required
                        showMessage(incomingMessage);
                    } finally {
                        // Close input stream
                        in.close();
                        // Close Socket
                        s.close();
                    }
                }
            }finally {
                // Close server socket
                ss.close();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    void showMessage(final String message) {
        MainActivity.getinstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.getinstance(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}

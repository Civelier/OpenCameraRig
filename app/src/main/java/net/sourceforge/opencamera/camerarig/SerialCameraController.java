package net.sourceforge.opencamera.camerarig;

import static net.sourceforge.opencamera.camerarig.UsbService.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import androidx.core.util.DebugUtils;

import net.sourceforge.opencamera.MainActivity;

import org.xml.sax.helpers.ParserFactory;

public class SerialCameraController
{
    private StringBuilder buffer = new StringBuilder();
    private final MainActivity mainAct;

    public static class ErrorMessageBox extends DialogFragment
    {
        public String message;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(message);
            return builder.create();
        }
    }

    private final IControlSource controlSource;
    private final UsbService usb;

    public SerialCameraController(IControlSource source, UsbService service, MainActivity mainActivity)
    {
        controlSource = source;
        usb = service;
        mainAct = mainActivity;
    }

    public void onDataRecieved(String data)
    {
        String line;
        buffer.append(data);
        if (data.contains("\n"))
        {
            line = buffer.toString();
            buffer = new StringBuilder();
        }
        else return;

        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Recieved: ");
            sb.append(line);
            Log.d(TAG, sb.toString());
            if (line == "") return;
            if (line == "\n") return;
            line = line.trim();
            String[] params;
            if (line.contains(" "))
            {
                params = line.split(" ");
            }
            else
            {
                params = new String[] {line};
            }
            int cmd = Integer.parseInt(line);
            switch (cmd)
            {
                case 1:
                    Log.d(TAG, "Starting video");
                    controlSource.StartVideo();
                    break;
                case 2:
                    Log.d(TAG, "Stopping video");
                    controlSource.StopVideo();
                    break;
                case 3: // Ping
                    Log.d(TAG, "Pingged");
                    usb.println("1");
                    Log.d(TAG, "Write ms");
                    usb.println("3");
                    break;
                default:
                    break;
            }

        }
        catch (Exception e)
        {
            Log.d(TAG, Log.getStackTraceString(e));
            Log.d(TAG, e.getMessage());
            /*ErrorMessageBox err = new ErrorMessageBox();
            err.message = e.getMessage();
            err.show(mainAct.getFragmentManager(), "error");*/
        }
    }
}

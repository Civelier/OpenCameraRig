package net.sourceforge.opencamera.camerarig;

import org.xml.sax.helpers.ParserFactory;

public class SerialCameraController
{
    private final IControlSource controlSource;
    private final UsbService usb;

    public SerialCameraController(IControlSource source, UsbService service)
    {
        controlSource = source;
        usb = service;
    }

    public void onDataRecieved(String data)
    {
        String[] params = data.split(" ");
        int cmd = Integer.parseInt(params[0]);
        switch (cmd)
        {
            case 1:
                controlSource.StartVideo();
                break;
            case 2:
                controlSource.StopVideo();
                break;
            default:
                break;
        }
    }
}

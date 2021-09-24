package net.sourceforge.opencamera.camerarig;

import net.sourceforge.opencamera.MainActivity;
import net.sourceforge.opencamera.MyApplicationInterface;
import net.sourceforge.opencamera.cameracontroller.CameraController;
import net.sourceforge.opencamera.preview.Preview;

public class BasicControlSource implements IControlSource
{
    private final MainActivity mainAct;
    private final MyApplicationInterface app;
    private final Preview prev;
    private final CameraController cam;

    public BasicControlSource(MyApplicationInterface appInterface, MainActivity mainActivity, Preview preview)
    {
        app = appInterface;
        mainAct = mainActivity;
        prev = preview;
        cam = prev.getCameraController();
    }
    @Override
    public void StartVideo()
    {
        if (!app.isVideoPref())
        {
            app.setVideoPref(true);
        }
        if (prev.isVideo()) return;
        mainAct.takePicture(false);
    }

    @Override
    public void StopVideo()
    {
        if (prev.isVideo()) mainAct.takePicture(false);
    }

    @Override
    public void SetFocusDistance(float distance)
    {
        app.setFocusDistancePref(distance, false);
    }

    @Override
    public float GetFocusDistance()
    {
        return app.getFocusDistancePref(false);
    }

    @Override
    public void SetZoom(int zoom)
    {
        app.setZoomPref(zoom);
    }

    @Override
    public int GetZoom()
    {
        return app.getZoomPref();
    }
}

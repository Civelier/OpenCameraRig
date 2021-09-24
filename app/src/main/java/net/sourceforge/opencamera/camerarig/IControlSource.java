package net.sourceforge.opencamera.camerarig;

public interface IControlSource
{
    void StartVideo();
    void StopVideo();
    void SetFocusDistance(float distance);
    float GetFocusDistance();
    void SetZoom(int zoom);
    int GetZoom();
}

package com.libs.nativelibserial;

public class NativeLib {

    // Used to load the 'nativelibserial' library on application startup.
    static {
        System.loadLibrary("nativelibserial");
    }

    /**
     * A native method that is implemented by the 'nativelibserial' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
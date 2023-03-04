using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.XR;
using UnityEngine.UI;
using Unity.XR.PXR;
using UDP;

public class TrackingGetter : MonoBehaviour {
    /*
     * PICO uses audio to estimate your lips given some phoneme.
     * If you only want the expressions on your face disable this param.
     */
    public bool useAudio = true;

    [SerializeField] private Text logger;

    private UDPSocket _send;

    void Start() {
        this._send = new UDPSocket();
        this._send.Client("192.168.1.101", 27000);
    }
    
    void Update()
    {
        Vector3 v;
        bool result = PXR_EyeTracking.GetCombineEyeGazeVector(out v);
        if (!result) return;
        logger.text = v.ToString();
        this._send.Send(v.ToString());
    }
}

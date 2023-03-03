using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.XR;
using UnityEngine.UI;
using Unity.XR.PXR;

public class TrackingGetter : MonoBehaviour {
    /*
     * PICO uses audio to estimate your lips given some phoneme.
     * If you only want the expressions on your face disable this param.
     */
    public bool useAudio = true;

    [SerializeField] private Text logger;
    
    void Start() { }
    
    void Update()
    {
        Vector3 v;
        bool result = PXR_EyeTracking.GetCombineEyeGazeVector(out v);
        if (!result) return;
        logger.text = v.ToString();
    }
}

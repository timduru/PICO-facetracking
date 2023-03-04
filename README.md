# PICO-facetracking
A Unity project to send PICO 4 enterprise eye&amp;face tracking data to the PC using sockets

#### In-app permissions
- PICO's eye_tracking: get the eye information
- PICO's face_tracking: get the face expressions
- record_audio: PICO estimates the mouth position given a phoneme listened by the audio; this permission can be disabled from the GUI
- internet: send the eye/face information to your computer

## Dependencies
### Build dependency
- Use Unity 2020.3.45f1 with OpenJDK & Android SDK Tools. More information [here](https://developer-global.pico-interactive.com/document/unity/quickstart-set-up-dev-env).

### Run dependency
- [temporal] A UDP server to read the information
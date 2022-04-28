# native2tex

This PoC demonstrates sharing a ByteArray between:
- JavaScript
- Java/Kotlin
- C/C++/Rust

The hope is that if that ByteArray/Uint8Array backs a WebGL texture, 
changes to it can be efficiently rendered from native code into the Android WebView.

## Update

This PoC Failed: it's just not possible to pass binary data from Java to JavaScript

https://stackoverflow.com/questions/46523385/how-to-send-binary-image-from-android-to-javascript
https://stackoverflow.com/questions/27034897/is-there-a-way-to-pass-an-arraybuffer-from-javascript-to-java-on-android

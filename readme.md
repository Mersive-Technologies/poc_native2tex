# native2tex

This PoC demonstrates sharing a ByteArray between:
- JavaScript
- Java/Kotlin
- C/C++/Rust

The hope is that if that ByteArray/Uint8Array backs a WebGL texture, 
changes to it can be efficiently rendered from native code into the Android WebView.

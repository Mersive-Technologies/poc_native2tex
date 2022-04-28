package live.ditto.myapplication

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import live.ditto.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()

        val webView = findViewById<WebView>(R.id.webView)
        webView.addJavascriptInterface(this, "JSInterface");
        webView.settings.javaScriptEnabled = true;
//        webView.loadUrl("https://google.com")
        webView.loadUrl("file:///android_asset/index.html")

//        webView.evaluateJavascript("""
//            const bytes = Uint8Array.from([1,2,3]);
//            //const bytes = 'test';
//            window.JSInterface.callback(bytes)
//        """.trimIndent()) {
//            println("${it}")
//        }
    }

    @JavascriptInterface
    fun callback(bytes: ByteArray): String {
        println("Hello Kotlin")
        bytes[0] = 42;
        sendData(127.toByte(), bytes)
        return "test";
    }

    /**
     * A native method that is implemented by the 'myapplication' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    private external fun sendData(color: Byte, data: ByteArray)

    companion object {
        // Used to load the 'myapplication' library on application startup.
        init {
            System.loadLibrary("myapplication")
        }
    }
}
package live.ditto.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.TextView
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
        webView.evaluateJavascript("""
            const bytes = Uint8Array.from([1,2,3]);
            //const bytes = 'test';
            window.JSInterface.callback(bytes)
        """.trimIndent()) {
            println("${it}")
        }
    }

    @JavascriptInterface
    fun callback(text: ByteArray) {
        println(text)
    }

    /**
     * A native method that is implemented by the 'myapplication' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'myapplication' library on application startup.
        init {
            System.loadLibrary("myapplication")
        }
    }
}
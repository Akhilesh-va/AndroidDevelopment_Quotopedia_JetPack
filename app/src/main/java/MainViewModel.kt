import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context :Context) : ViewModel(){
     var quoteList :Array<Quote> = emptyArray()
    var index =0
    init {
        quoteList=loadQuoteFromAssests()
    }

    private fun loadQuoteFromAssests(): Array<Quote> {
        // Opens the "quotes.json" file located in the assets folder.
        // We use 'context.assets.open' to access the app's assets, which are non-code files bundled with the app.
        val inputStream = context.assets.open("quotes.json")

        // Gets the size of the JSON file in bytes.
        // This is used to create a buffer of the same size for reading the file content.
        val size: Int = inputStream.available()

        // Creates a byte array (buffer) with a length equal to the size of the file.
        // This buffer will temporarily hold the file data before it is converted to a String.
        val buffer = ByteArray(size)

        // Reads the file content into the buffer.
        // This method fills the buffer with data from the input stream (i.e., the JSON file).
        inputStream.read(buffer)

        // Closes the InputStream to free up system resources.
        // It is a good practice to close streams after use to avoid memory leaks.
        inputStream.close()

        // Converts the byte array (buffer) into a String using UTF-8 encoding.
        // JSON data is in text format, so we need to convert it from bytes to a readable String.
        val json = String(buffer, Charsets.UTF_8)

        // Creates an instance of the Gson class, which is used to parse JSON into Kotlin objects.
        // Gson is a library for converting Java/Kotlin objects to JSON and vice versa.
        val gson = Gson()

        // Parses the JSON string into an array of Quote objects using Gson's fromJson method.
        // - 'json': the JSON string to be parsed.
        // - 'Array<Quote>::class.java': specifies the type of the array we want to parse the JSON into.
        // The function returns this array of Quote objects.
        return gson.fromJson(json, Array<Quote>::class.java)
    }
    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun prevQuote() = quoteList[--index]

}
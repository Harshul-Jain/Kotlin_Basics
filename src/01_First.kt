/*
//1.CONSISE
/*
 Create a POJO with getters, setters, `equals()`, `hashCode()`, `toString()` and `copy()` in a single line:
*/

data class Customer(val name: String, val email: String, val company: String)

// Or filter a list using a lambda expression:

val positiveNumbers = list.filter { it > 0 }

// Want a singleton? Create an object:

object ThisIsASingleton {
    val companyName: String = "JetBrains"
}
//2.SAFE
/*
 Get rid of those pesky NullPointerExceptions, you know, The Billion Dollar Mistake
*/

var output: String
output = null   // Compilation error

// Kotlin protects you from mistakenly operating on nullable types

val name: String? = null    // Nullable type
println(name.length())      // Compilation error

// And if you check a type is right, the compiler will auto-cast it for you

fun calculateTotal(obj: Any) {
    if (obj is Invoice)
        obj.calculateTotal()
}
//3.INTEROPERABLE
/*
 Use any existing library on the JVM, as there’s 100% compatibility, including SAM support.
*/

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

Flowable
    .fromCallable {
        Thread.sleep(1000) //  imitate expensive computation
         "Done"
    }
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.single())
    .subscribe(::println, Throwable::printStackTrace)


// Target either the JVM or JavaScript. Write code in Kotlin and decide where you want to deploy to

import kotlin.browser.window

fun onLoad() {
    window.document.body!!.innerHTML += "<br/>Hello, Kotlin!"
}
*/
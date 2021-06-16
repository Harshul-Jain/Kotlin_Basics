/*
var f:String
val d:String
this declaraction are not valid in kotlin
Solution:
lateinit var f:String
//Late Initialization
val d:String ="HJ" //No other option for this

lateinit var f (this is not valid)
 */


fun main(){
    val a=10//Type inference refers to the automatic detection of the type of an expression in a formal language.
    val x:String="hello world"
    val b:Int=10
    val c:Boolean=true
    val d: Float=10.0F
    val e: Double=10.9
    var f= "Hello"
    f="World"
    /* var and val are both used to declare variables in Kotlin language. However, there are some key differences between them:

VAR(Variable)
It is a general variable. The value of a variable that is declared using var can be changed anytime throughout the program. var is also called mutable and non-final variable, as there value can be changed anytime.

VAL(Value)
The object stored using val cannot be changed, it cannot be reassigned, it is just like the final keyword in java. val is immutable. Once assigned the val becomes read-only, however, the properties of a val object could be changed, but the object itself is read-only.*/
    println(a)
}
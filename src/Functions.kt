fun main(){
    square(10)
    showMeUser("harshul",20,true)//Positional Arguments
    showMeUser2("harshul",20)//Default Arguments
    showMeUser(age=18,isActive = true,name = "harshul")//Named Arguments
    createAndPrintArgs(1,2,3,3,5,6,6,7,6,7,7,7,0)

}
fun square(num:Int):Int{
    return num*num
}
fun square2(num:Int):Int=num*num
fun square3(num:Int)=if(num>0)num*num else -1
fun square4(num:Int)=num*num

fun thisShouldStartWithLowerCase(){
    //this is lowerCamelCase
}

fun showMeUser(name:String,age:Int,isActive:Boolean){
    println("Name $name,Age $age,isActive $isActive")
}
fun showMeUser2(name:String,age:Int,isActive:Boolean=false){
    println("Name $name,Age $age,isActive $isActive")
}
/*
Type of Arguments
1.Positional Arguments
2.Default Arguments
3.Named Arguments
 */
//Varargs
fun createAndPrintArgs(vararg number:Int ){
    println(number.size)
    for(no in number) println(no)
}

/*
Unit-returning functions
If a function does not return any useful value, its return type is Unit.
 Unit is a type with only one value - Unit. This value does not have to be returned explicitly:
fun printHello(name: String?): Unit {
    if (name != null)
        println("Hello $name")
    else
        println("Hi there!")
    // `return Unit` or `return` is optional
}
The Unit return type declaration is also optional. The above code is equivalent to:
fun printHello(name: String?) { ... }

Infix notation
Functions marked with the infix keyword can also be called using the infix notation (omitting the dot and the parentheses for the call).
 Infix functions must meet the following requirements:
1.They must be member functions or extension functions.
2.They must have a single parameter.
3.The parameter must not accept variable number of arguments and must have no default value.

infix fun Int.shl(x: Int): Int { ... }

// calling the function using the infix notation
1 shl 2

// is the same as
1.shl(2)

Note that infix functions always require both the receiver and the parameter to be specified.
When you're calling a method on the current receiver using the infix notation, use this explicitly.
This is required to ensure unambiguous parsing.

class MyStringCollection {
    infix fun add(s: String) { /*...*/ }

    fun build() {
        this add "abc"   // Correct
        add("abc")       // Correct
        //add "abc"        // Incorrect: the receiver must be specified
    }
}
Function scope
Kotlin functions can be declared at the top level in a file,
 meaning you do not need to create a class to hold a function,
 which you are required to do in languages such as Java, C# or Scala.
 In addition to top level functions, Kotlin functions can also be declared locally,
 as member functions and extension functions.

Local functions
Kotlin supports local functions, which are functions inside another function:

fun dfs(graph: Graph) {
    fun dfs(current: Vertex, visited: MutableSet<Vertex>) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v, visited)
    }

    dfs(graph.vertices[0], HashSet())
}

A local function can access local variables of outer functions (the closure). In the case above, the visited can be a local variable:

fun dfs(graph: Graph) {
    val visited = HashSet<Vertex>()
    fun dfs(current: Vertex) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v)
    }

    dfs(graph.vertices[0])
}

Member functions
A member function is a function that is defined inside a class or object:

class Sample {
    fun foo() { print("Foo") }
}

Member functions are called with dot notation:

Sample().foo() // creates instance of class Sample and calls foo

For more information on classes and overriding members see Classes and Inheritance.

Generic functions
Functions can have generic parameters which are specified using angle brackets before the function name:

fun <T> singletonList(item: T): List<T> { /*...*/ }

For more information on generic functions, see Generics.

Tail recursive functions
Kotlin supports a style of functional programming known as tail recursion. For some algorithms that would normally use loops you can use a recursive function instead without a risk of stack overflow. When a function is marked with the tailrec modifier and meets the required form, the compiler optimizes out the recursion, leaving behind a fast and efficient loop based version instead:

val eps = 1E-10 // "good enough", could be 10^-15

tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))
Copied!
This code calculates the fixpoint of cosine, which is a mathematical constant. It simply calls Math.cos repeatedly starting at 1.0 until the result doesn't change anymore, yielding a result of 0.7390851332151611 for the specified eps precision. The resulting code is equivalent to this more traditional style:

val eps = 1E-10 // "good enough", could be 10^-15

private fun findFixPoint(): Double {
    var x = 1.0
    while (true) {
        val y = Math.cos(x)
        if (Math.abs(x - y) < eps) return x
        x = Math.cos(x)
    }
}
To be eligible for the tailrec modifier, a function must call itself as the last operation it performs. You cannot use tail recursion when there is more code after the recursive call, and you cannot use it within try/ catch/ finally blocks. Currently, tail recursion is supported by Kotlin for JVM and Kotlin/Native.
 */

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
Inline functions﻿
Edit page
Last modified: 04 June 2021
Using higher-order functions imposes certain runtime penalties: each function is an object, and it captures a closure. A closure means those variables that are accessed in the body of the function. Memory allocations (both for function objects and classes) and virtual calls introduce runtime overhead.

But it appears that in many cases this kind of overhead can be eliminated by inlining the lambda expressions. The functions shown below are good examples of this situation. The lock() function could be easily inlined at call-sites. Consider the following case:

lock(l) { foo() }
Copied!
Instead of creating a function object for the parameter and generating a call, the compiler could emit the following code:

l.lock()
try {
    foo()
} finally {
    l.unlock()
}
Copied!
To make the compiler do this, you need to mark the lock() function with the inline modifier:

inline fun <T> lock(lock: Lock, body: () -> T): T { ... }
Copied!
The inline modifier affects both the function itself and the lambdas passed to it: all of those will be inlined into the call site.

Inlining may cause the generated code to grow; however, if you do it in a reasonable way (avoiding inlining large functions), it will pay off in performance, especially at "megamorphic" call-sites inside loops.

noinline﻿
In case you want only some of the lambdas passed to an inline function to be inlined, you can mark some of your function parameters with the noinline modifier:

inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) { ... }
Copied!
Inlinable lambdas can only be called inside the inline functions or passed as inlinable arguments, but noinline ones can be manipulated in any way you like such as stored in fields or passed around.

If an inline function has no inlinable function parameters and no reified type parameters, the compiler will issue a warning, since inlining such functions is very unlikely to be beneficial (you can suppress the warning if you are sure the inlining is needed using the annotation @Suppress("NOTHING_TO_INLINE") ).

Non-local returns﻿
In Kotlin, you can only use a normal, unqualified return to exit a named function or an anonymous function. To exit a lambda, use a label. A bare return is forbidden inside a lambda because a lambda cannot make the enclosing function return:

fun foo() {
    ordinaryFunction {
        return // ERROR: cannot make `foo` return here
    }
}
Target platform: JVMRunning on kotlin v.1.5.0
But if the function the lambda is passed to is inlined, the return can be inlined as well. So it is allowed:

fun foo() {
    inlined {
        return // OK: the lambda is inlined
    }
}
Target platform: JVMRunning on kotlin v.1.5.0
Such returns (located in a lambda, but exiting the enclosing function) are called non-local returns. This sort of construct in loops, which inline functions often enclose:

fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // returns from hasZeros
    }
    return false
}
Copied!
Note that some inline functions may call the lambdas passed to them as parameters not directly from the function body, but from another execution context, such as a local object or a nested function. In such cases, non-local control flow is also not allowed in the lambdas. To indicate that, the lambda parameter needs to be marked with the crossinline modifier:

inline fun f(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ...
}
Copied!
break and continue are not yet available in inlined lambdas, but we are planning to support them too.

Reified type parameters﻿
Sometimes you need to access a type passed as a parameter:

fun <T> TreeNode.findParentOfType(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}
Copied!
Here, you walk up a tree and use reflection to check if a node has a certain type. It’s all fine, but the call site is not very pretty:

treeNode.findParentOfType(MyTreeNode::class.java)
Copied!
What you actually want is simply pass a type to this function. You can call it like this:

treeNode.findParentOfType<MyTreeNode>()
Copied!
To enable this, inline functions support reified type parameters, so you can write something like this:

inline fun <reified T> TreeNode.findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }
    return p as T?
}
Copied!
You qualified the type parameter with the reified modifier to make it accessible inside the function, almost as if it were a normal class. Since the function is inlined, no reflection is needed, normal operators like !is and as are working now. Also, you can call it as mentioned above: myTree.findParentOfType<MyTreeNodeType>().

Though reflection may not be needed in many cases, you can still use it with a reified type parameter:

inline fun <reified T> membersOf() = T::class.members

fun main(s: Array<String>) {
    println(membersOf<StringBuilder>().joinToString("\n"))
}
Copied!
Normal functions (not marked as inline) cannot have reified parameters. A type that does not have a run-time representation (for example, a non-reified type parameter or a fictitious type like Nothing) cannot be used as an argument for a reified type parameter.

Inline properties﻿
The inline modifier can be used on accessors of properties that don't have a backing field. You can annotate individual property accessors:

val foo: Foo
    inline get() = Foo()

var bar: Bar
    get() = ...
    inline set(v) { ... }
Copied!
You can also annotate an entire property, which marks both of its accessors as inline:

inline var bar: Bar
    get() = ...
    set(v) { ... }
Copied!
At the call site, inline accessors are inlined as regular inline functions.

Restrictions for public API inline functions﻿
When an inline function is public or protected and is not a part of a private or internal declaration, it is considered a module 's public API. It can be called in other modules and is inlined at such call sites as well.

This imposes certain risks of binary incompatibility caused by changes in the module that declares an inline function in case the calling module is not re-compiled after the change.

To eliminate the risk of such incompatibility being introduced by a change in non -public API of a module, the public API inline functions are not allowed to use non-public-API declarations, i.e. private and internal declarations and their parts, in their bodies.

An internal declaration can be annotated with @PublishedApi, which allows its use in public API inline functions. When an internal inline function is marked as @PublishedApi, its body is checked too, as if it were public.

 */
/*
High-order functions and lambdas﻿

Edit page
Last modified: 05 May 2021
Kotlin functions are first-class, which means that they can be stored in variables and data structures, passed as arguments to and returned from other higher-order functions. You can operate with functions in any way that is possible for other non-function values.

To facilitate this, Kotlin, as a statically typed programming language, uses a family of function types to represent functions and provides a set of specialized language constructs, such as lambda expressions.

Higher-order functions﻿
A higher-order function is a function that takes functions as parameters, or returns a function.

A good example is the functional programming idiom fold for collections, which takes an initial accumulator value and a combining function and builds its return value by consecutively combining current accumulator value with each collection element, replacing the accumulator:

fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}
Copied!
In the code above, the parameter combine has a function type (R, T) -> R, so it accepts a function that takes two arguments of types R and T and returns a value of type R. It is invoked inside the for -loop, and the return value is then assigned to accumulator.

To call fold, you need to pass it an instance of the function type as an argument, and lambda expressions (described in more detail below) are widely used for this purpose at higher-order function call sites:

val items = listOf(1, 2, 3, 4, 5)
​
// Lambdas are code blocks enclosed in curly braces.
items.fold(0, {
    // When a lambda has parameters, they go first, followed by '->'
    acc: Int, i: Int ->
    print("acc = $acc, i = $i, ")
    val result = acc + i
    println("result = $result")
    // The last expression in a lambda is considered the return value:
    result
})
​
// Parameter types in a lambda are optional if they can be inferred:
val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })
​
// Function references can also be used for higher-order function calls:
val product = items.fold(1, Int::times)
​
Target platform: JVMRunning on kotlin v.1.5.0
The following sections explain in more detail the concepts mentioned so far.

Function types﻿
Kotlin uses a family of function types like (Int) -> String for declarations that deal with functions: val onClick: () -> Unit = ....

These types have a special notation that corresponds to the signatures of the functions - their parameters and return values:

All function types have a parenthesized parameter types list and a return type: (A, B) -> C denotes a type that represents functions taking two arguments of types A and B and returning a value of type C. The parameter types list may be empty, as in () -> A. The Unit return type cannot be omitted.

Function types can optionally have an additional receiver type, which is specified before a dot in the notation: the type A.(B) -> C represents functions that can be called on a receiver object of A with a parameter of B and return a value of C. Function literals with receiver are often used along with these types.

Suspending functions belong to function types of a special kind, which have a suspend modifier in the notation, such as suspend () -> Unit or suspend A.(B) -> C.

The function type notation can optionally include names for the function parameters: (x: Int, y: Int) -> Point. These names can be used for documenting the meaning of the parameters.

To specify that a function type is nullable, use parentheses: ((Int, Int) -> Int)?.

Function types can be combined using parentheses: (Int) -> ((Int) -> Unit).

The arrow notation is right-associative, (Int) -> (Int) -> Unit is equivalent to the previous example, but not to ((Int) -> (Int)) -> Unit.

You can also give a function type an alternative name by using a type alias:

typealias ClickHandler = (Button, ClickEvent) -> Unit
Copied!
Instantiating a function type﻿
There are several ways to obtain an instance of a function type:

Using a code block within a function literal, in one of the forms:

a lambda expression: { a, b -> a + b },

an anonymous function: fun(s: String): Int { return s.toIntOrNull() ?: 0 }

Function literals with receiver can be used as values of function types with receiver.

Using a callable reference to an existing declaration:

a top-level, local, member, or extension function: ::isOdd, String::toInt,

a top-level, member, or extension property: List<Int>::size,

a constructor: ::Regex

These include bound callable references that point to a member of a particular instance: foo::toString.

Using instances of a custom class that implements a function type as an interface:

class IntTransformer: (Int) -> Int {
    override operator fun invoke(x: Int): Int = TODO()
}

val intFunction: (Int) -> Int = IntTransformer()
Copied!
The compiler can infer the function types for variables if there is enough information:

val a = { i: Int -> i + 1 } // The inferred type is (Int) -> Int
Copied!
Non-literal values of function types with and without receiver are interchangeable, so that the receiver can stand in for the first parameter, and vice versa. For instance, a value of type (A, B) -> C can be passed or assigned where a A.(B) -> C is expected and the other way around:

val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
val twoParameters: (String, Int) -> String = repeatFun // OK
​
fun runTransformation(f: (String, Int) -> String): String {
    return f("hello", 3)
}
val result = runTransformation(repeatFun) // OK
​
Target platform: JVMRunning on kotlin v.1.5.0
A function type with no receiver is inferred by default, even if a variable is initialized with a reference to an extension function. To alter that, specify the variable type explicitly.

Invoking a function type instance﻿
A value of a function type can be invoked by using its invoke(...) operator: f.invoke(x) or just f(x).

If the value has a receiver type, the receiver object should be passed as the first argument. Another way to invoke a value of a function type with receiver is to prepend it with the receiver object, as if the value were an extension function: 1.foo(2),

Example:

val stringPlus: (String, String) -> String = String::plus
val intPlus: Int.(Int) -> Int = Int::plus
​
println(stringPlus.invoke("<-", "->"))
println(stringPlus("Hello, ", "world!"))
​
println(intPlus.invoke(1, 1))
println(intPlus(1, 2))
println(2.intPlus(3)) // extension-like call
​
Target platform: JVMRunning on kotlin v.1.5.0
Inline functions﻿
Sometimes it is beneficial to use inline functions, which provide flexible control flow, for higher-order functions.

Lambda expressions and anonymous functions﻿
Lambda expressions and anonymous functions are function literals. Function literals are functions that are not declared but passed immediately as an expression. Consider the following example:

max(strings, { a, b -> a.length < b.length })
Copied!
Function max is a higher-order function, it takes a function value as the second argument. This second argument is an expression that is itself a function, called a function literal, which is equivalent to the following named function:

fun compare(a: String, b: String): Boolean = a.length < b.length
Copied!
Lambda expression syntax﻿
The full syntactic form of lambda expressions is as follows:

val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
Copied!
A lambda expression is always surrounded by curly braces.

Parameter declarations in the full syntactic form go inside curly braces and have optional type annotations.

The body goes after an -> sign.

If the inferred return type of the lambda is not Unit, the last (or possibly single) expression inside the lambda body is treated as the return value.

If you leave all the optional annotations out, what's left looks like this:

val sum = { x: Int, y: Int -> x + y }
Copied!
Passing trailing lambdas﻿
In Kotlin, there is a convention: if the last parameter of a function is a function, then a lambda expression passed as the corresponding argument can be placed outside the parentheses:

val product = items.fold(1) { acc, e -> acc * e }
Copied!
Such syntax is also known as trailing lambda.

If the lambda is the only argument to that call, the parentheses can be omitted entirely:

run { println("...") }
Copied!
it: implicit name of a single parameter﻿
It's very common that a lambda expression has only one parameter.

If the compiler can figure the signature out itself, it is allowed not to declare the only parameter and omit ->. The parameter will be implicitly declared under the name it:

ints.filter { it > 0 } // this literal is of type '(it: Int) -> Boolean'
Copied!
Returning a value from a lambda expression﻿
You can explicitly return a value from the lambda using the qualified return syntax. Otherwise, the value of the last expression is implicitly returned.

Therefore, the two following snippets are equivalent:

ints.filter {
    val shouldFilter = it > 0
    shouldFilter
}

ints.filter {
    val shouldFilter = it > 0
    return@filter shouldFilter
}
Copied!
This convention, along with passing a lambda expression outside parentheses, allows for LINQ-style code:

strings.filter { it.length == 5 }.sortedBy { it }.map { it.uppercase() }
Copied!
Underscore for unused variables﻿
If the lambda parameter is unused, you can place an underscore instead of its name:

map.forEach { _, value -> println("$value!") }
Copied!
Destructuring in lambdas﻿
Destructuring in lambdas is described as a part of destructuring declarations.

Anonymous functions﻿
One thing missing from the lambda expression syntax presented above is the ability to specify the return type of the function. In most cases, this is unnecessary because the return type can be inferred automatically. However, if you do need to specify it explicitly, you can use an alternative syntax: an anonymous function.

fun(x: Int, y: Int): Int = x + y
Copied!
An anonymous function looks very much like a regular function declaration, except that its name is omitted. Its body can be either an expression (as shown above) or a block:

fun(x: Int, y: Int): Int {
    return x + y
}
Copied!
The parameters and the return type are specified in the same way as for regular functions, except that the parameter types can be omitted if they can be inferred from context:

ints.filter(fun(item) = item > 0)
Copied!
The return type inference for anonymous functions works just like for normal functions: the return type is inferred automatically for anonymous functions with an expression body and has to be specified explicitly (or is assumed to be Unit) for anonymous functions with a block body.

Anonymous function parameters are always passed inside the parentheses. The shorthand syntax allowing to leave the function outside the parentheses works only for lambda expressions.

One other difference between lambda expressions and anonymous functions is the behavior of non-local returns. A return statement without a label always returns from the function declared with the fun keyword. This means that a return inside a lambda expression will return from the enclosing function, whereas a return inside an anonymous function will return from the anonymous function itself.

Closures﻿
A lambda expression or anonymous function (as well as a local function and an object expression) can access its closure, which includes the variables declared in the outer scope. The variables captured in the closure can be modified in the lambda:

var sum = 0
ints.filter { it > 0 }.forEach {
    sum += it
}
print(sum)
Copied!
Function literals with receiver﻿
Function types with receiver, such as A.(B) -> C, can be instantiated with a special form of function literals – function literals with receiver.

As said above, Kotlin provides the ability to call an instance of a function type with receiver providing the receiver object.

Inside the body of the function literal, the receiver object passed to a call becomes an implicit this, so that you can access the members of that receiver object without any additional qualifiers, or access the receiver object using a this expression.

This behavior is similar to extension functions, which also allow you to access the members of the receiver object inside the body of the function.

Here is an example of a function literal with receiver along with its type, where plus is called on the receiver object:

val sum: Int.(Int) -> Int = { other -> plus(other) }
Copied!
The anonymous function syntax allows you to specify the receiver type of a function literal directly. This can be useful if you need to declare a variable of a function type with receiver, and to use it later.

val sum = fun Int.(other: Int): Int = this + other
Copied!
Lambda expressions can be used as function literals with receiver when the receiver type can be inferred from context. One of the most important examples of their usage is type-safe builders:

class HTML {
    fun body() { ... }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the receiver object to the lambda
    return html
}

html {       // lambda with receiver begins here
    body()   // calling a method on the receiver object
}

Extensions﻿
Edit page
Last modified: 11 February 2021
Kotlin provides an ability to extend a class with new functionality without having to inherit from the class or use design patterns such as Decorator. This is done via special declarations called extensions.

For example, you can write new functions for a class from a third-party library that you can't modify. Such functions are available for calling in the usual way as if they were methods of the original class. This mechanism is called extension functions. There are also extension properties that let you define new properties for existing classes.

Extension functions﻿
To declare an extension function, prefix its name with a receiver type, that means the type being extended. The following adds a swap function to MutableList<Int>:

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
Copied!
The this keyword inside an extension function corresponds to the receiver object (the one that is passed before the dot). Now, you can call such a function on any MutableList<Int>:

val list = mutableListOf(1, 2, 3)
list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'list'
Copied!
This function makes sense for any MutableList<T>, and you can make it generic:

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
Copied!
You declare the generic type parameter before the function name to make it available in the receiver type expression. See Generic functions.

Extensions are resolved statically﻿
Extensions do not actually modify classes they extend. By defining an extension, you do not insert new members into a class, but merely make new functions callable with the dot-notation on variables of this type.

Extension functions are dispatched statically, that means that they are not virtual by receiver type. An extension function being called is determined by the type of the expression on which the function is invoked, not by the type of the result of evaluating that expression at runtime. For example:

open class Shape
​
class Rectangle: Shape()
​
fun Shape.getName() = "Shape"
​
fun Rectangle.getName() = "Rectangle"
​
fun printClassName(s: Shape) {
    println(s.getName())
}
​
printClassName(Rectangle())
Target platform: JVMRunning on kotlin v.1.5.0
This example prints Shape, because the called extension function depends only on the declared type of the parameter s, which is the Shape class.

If a class has a member function, and an extension function is defined which has the same receiver type, the same name, and is applicable to given arguments, the member always wins. For example:

class Example {
    fun printFunctionType() { println("Class method") }
}
​
fun Example.printFunctionType() { println("Extension function") }
​
Example().printFunctionType()
Target platform: JVMRunning on kotlin v.1.5.0
This code prints Class method.

However, it's perfectly OK for extension functions to overload member functions which have the same name but a different signature:

class Example {
    fun printFunctionType() { println("Class method") }
}
​
fun Example.printFunctionType(i: Int) { println("Extension function") }
​
Example().printFunctionType(1)
Target platform: JVMRunning on kotlin v.1.5.0
Nullable receiver﻿
Note that extensions can be defined with a nullable receiver type. Such extensions can be called on an object variable even if its value is null, and can check for this == null inside the body.

This way you can call toString() in Kotlin without checking for null: the check happens inside the extension function.

fun Any?.toString(): String {
    if (this == null) return "null"
    // after the null check, 'this' is autocast to a non-null type, so the toString() below
    // resolves to the member function of the Any class
    return toString()
}
Copied!
Extension properties﻿
Similarly to functions, Kotlin supports extension properties:

val <T> List<T>.lastIndex: Int
    get() = size - 1
Copied!
Since extensions do not actually insert members into classes, there's no efficient way for an extension property to have a backing field. This is why initializers are not allowed for extension properties. Their behavior can only be defined by explicitly providing getters/setters.

Example:

val House.number = 1 // error: initializers are not allowed for extension properties
Copied!
Companion object extensions﻿
If a class has a companion object defined, you can also define extension functions and properties for the companion object. Just like regular members of the companion object, they can be called using only the class name as the qualifier:

class MyClass {
    companion object { }  // will be called "Companion"
}
​
fun MyClass.Companion.printCompanion() { println("companion") }
​
fun main() {
    MyClass.printCompanion()
}
Target platform: JVMRunning on kotlin v.1.5.0
Scope of extensions﻿
In most cases, you define extensions on the top level - directly under packages:

package org.example.declarations

fun List<String>.getLongestString() { /*...*/}
Copied!
To use such an extension outside its declaring package, import it at the call site:

package org.example.usage

import org.example.declarations.getLongestString

fun main() {
    val list = listOf("red", "green", "blue")
    list.getLongestString()
}
Copied!
See Imports for more information.

Declaring extensions as members﻿
Inside a class, you can declare extensions for another class. Inside such an extension, there are multiple implicit receivers- objects members of which can be accessed without a qualifier. The instance of the class in which the extension is declared is called dispatch receiver, and the instance of the receiver type of the extension method is called extension receiver.

class Host(val hostname: String) {
    fun printHostname() { print(hostname) }
}
​
class Connection(val host: Host, val port: Int) {
     fun printPort() { print(port) }
​
     fun Host.printConnectionString() {
         printHostname()   // calls Host.printHostname()
         print(":")
         printPort()   // calls Connection.printPort()
     }
​
     fun connect() {
         /*...*/
         host.printConnectionString()   // calls the extension function
     }
}
​
fun main() {
    Connection(Host("kotl.in"), 443).connect()
    //Host("kotl.in").printConnectionString(443)  // error, the extension function is unavailable outside Connection
}
Target platform: JVMRunning on kotlin v.1.5.0
In case of a name conflict between the members of the dispatch receiver and the extension receiver, the extension receiver takes precedence. To refer to the member of the dispatch receiver, you can use the qualified this syntax.

class Connection {
    fun Host.getConnectionString() {
        toString()         // calls Host.toString()
        this@Connection.toString()  // calls Connection.toString()
    }
}
Copied!
Extensions declared as members can be declared as open and overridden in subclasses. This means that the dispatch of such functions is virtual with regard to the dispatch receiver type, but static with regard to the extension receiver type.

open class Base { }
​
class Derived : Base() { }
​
open class BaseCaller {
    open fun Base.printFunctionInfo() {
        println("Base extension function in BaseCaller")
    }
​
    open fun Derived.printFunctionInfo() {
        println("Derived extension function in BaseCaller")
    }
​
    fun call(b: Base) {
        b.printFunctionInfo()   // call the extension function
    }
}
​
class DerivedCaller: BaseCaller() {
    override fun Base.printFunctionInfo() {
        println("Base extension function in DerivedCaller")
    }
​
    override fun Derived.printFunctionInfo() {
        println("Derived extension function in DerivedCaller")
    }
}
​
fun main() {
    BaseCaller().call(Base())   // "Base extension function in BaseCaller"
    DerivedCaller().call(Base())  // "Base extension function in DerivedCaller" - dispatch receiver is resolved virtually
    DerivedCaller().call(Derived())  // "Base extension function in DerivedCaller" - extension receiver is resolved statically
}
Target platform: JVMRunning on kotlin v.1.5.0
Note on visibility﻿
Extensions utilize the same visibility of other entities as regular functions declared in the same scope would. For example:

An extension declared at the top level of a file has access to the other private top-level declarations in the same file.

If an extension is declared outside its receiver type, such an extension cannot access the receiver's private members.
*/
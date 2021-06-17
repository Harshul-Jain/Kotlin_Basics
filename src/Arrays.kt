import java.util.function.BiConsumer

val numbers= arrayOf(1,2,3,4,5)
/*
it works on vararg
[vararg]:It tells that you can have n numbers of arguments inside any function
 */
val name= arrayOf("1","2","3","4","5")
//val name= arrayOf("1","2","3","4",5)// we haven't given any type to array
val nameString= arrayOf<String>("1","2","3","4","5")
val num=Array(5,{i->i*1})
val numArray= intArrayOf(1,2)
val nullArray= arrayOfNulls<String>(10)
//Lists or Collections
var listOfNames= listOf<String>("Hello","World","harshul")
var mutableListOfNames= mutableListOf<String>("Hello ","World","harshul")
var mutableArrayListOfNames= arrayListOf<String>("Hello ","World","harshul")
val mapOfNames= mapOf<String,String>("Name" to "harshul","age" to "20","Nationality" to "Indian")
//map is immutable while hashMap is mutable
val hashMapOfNames= hashMapOf<String,String>("Name" to "harshul","age" to "20","Nationality" to "Indian")


fun main(){
    println(num[0])
    println(num.get(0))
    //listOfNames.add("harshul")   This does not works as list is immutable
    mutableListOfNames.add("Jain")
    println(mapOfNames["Name"])
    hashMapOfNames["Occupation"]="Student"
    println(hashMapOfNames["Occupation"])
    hashMapOfNames.forEach { t, u -> println(" "+t+" "+u) }
    mutableListOfNames.forEach {
        println(it)
    }
    nameString.forEach {
        println(it)
    }
    mapOfNames.forEach { t, u ->
        println(" "+t+" "+u)
    }
    //Loops
    for(name in numArray){
        println(name)
    }
    for(x in 0..10){
        println(x)//print from 0 to 10
    }
    for(x in 0 until 10){
        //10 is exclusive
        println(x)
    }
    for(x in 0 until 10 step 2){
        //10 is exclusive
        println(x)
    }
    for(x in 10 downTo 0 step 2){
        print(x) //10 is inclusive
    }
    //Some Special Keywords
    //as
    val any:Any="Harshul"
    val bAny:String=any as String
    //is
    if(any is String){
        any.length
    }
    when(any){
        "Harshul"->{
            println("First Name")
        }
        "Jain"->{
            println("Last Name")
        }
        else->{
            println("Name")
        }
    }
    //Also return value
    val x=when(any){
        "Harshul"->"$any Jain"
        "Jain"->"Harshul $any"
        else->"Not a valid name"
    }
    println(x)
}
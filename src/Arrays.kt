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
}
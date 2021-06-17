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
fun main(){
    println(num[0])
    println(num.get(0))
}
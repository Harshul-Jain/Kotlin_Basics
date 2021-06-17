/*
var f :String = null
this cannot be done as f is an non nullable variable
//For making them nullable we add '?'
var f:String?=null
var g: String? = null
var size = g.length
this will give error (  calls are allowed on a nullable receiver of type String?)
Solution
var size=g?.length //Show g is nullable object
Output:-null
Now
var size:Int=g?.length (this is not valid as setting a null value to non nullable variable)
Solution1:
var size:Int=g?.length!! (this will give NullPointerException ) !!->used as NullSafety when you are sure the Value would or should be Null
but it will stop the Program
var size:Int?=g?.length
var size:Int=g?.length ?: 0 (?: Elvin Operator)

*/
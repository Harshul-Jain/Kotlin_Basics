fun main(){
    val user:User=User()
    user.email
    val account=Account()
    account.insert(12345,"Harshul",1000f)
    account.deposit(100F)
    account.withDraw(150F)
    account.withDraw(50F)
    println(account.amount)
    println("Account no ${account.accNo} ,Account Holder Name ${account.name} ,Amount ${account.amount}")
    //Primary Constructor Declaration or Call
    val accounts=Accounts(12345,"Harshul",1000f)
    //Secondary Constructor Declaration or Call
    val account3=Account3(12345)
}

//It is good Practice to declare class name first letter in UpperCase
class User{
    val name:String=""
    val mobile:Int=1
    val gender:String=""
    val email:String=""
    val bio:String=""

    fun checkEmail():Boolean{
        return email.isNotEmpty()
    }

}
class Account{
    //Properties
    var accNo:Int=0
    var name:String?=null
    var amount:Float=0f
    //Constructor
    /*
    It is a function with same name as class name
    It is used too construct or initialize the class
    Two Type of Constructors
    1.Primary Constructor-Only 1
    2.Secondary Constructor-Multiple Secondary Constructorsgi
     */
    //Member Functions
    /*fun insert(ac:Int,n:String,amnt:Float){
        accNo=ac
        name=n
        amount=amnt
    }*/
    //this point to the current receiver
    fun insert(accNo:Int,name:String,amount:Float){
        this.accNo=accNo
        this.name=name
        this.amount=amount
    }

    fun deposit(money:Float){
        amount+=money
        println(amount)
    }
    fun withDraw(money:Float){
        if(amount<money){
            println("Not sufficient Funds")
        }
        else{
            amount-=money
            println(amount)
        }

    }
}
//Primary Constructor
class Accounts(var accNo:Int,var name:String,var amount:Float){

}
//Constructor with init block
class Account2( accNo:Int, name:String, amount:Float){
    var accNo:Int
    var name:String
    var amount:Float
    init {
        this.accNo=accNo
        this.amount=amount
        this.name=name
    }
}
//Difference between Primary Constructor and Constructor with init block
//in case of init block i can to manipulation which can not be done in other like
/*
 init {
        this.accNo=accNo
        this.amount=amount+1000f
        this.name=name.capitalize()
    }
 */
//In kotlin, const and val both represents the immutability and read only values and act as final keyword in java.
// val keyword must be used to declare for run time values and const keyword must be used to declare compile time values.


//Secondary Constructor
class Account3{
    var accNo:Int=1
    var name:String=""
    var amount:Float=0f

    constructor(accNo :Int){
        this.accNo=accNo
        name="Default"
        amount=0f
    }

    constructor(accNo: Int,name:String){
        this.accNo=accNo
        this.name=name
        amount=0f
    }

    constructor(accNo: Int,name: String,amount: Float){
        this.accNo=accNo
        this.name=name
        this.amount=amount
    }

}

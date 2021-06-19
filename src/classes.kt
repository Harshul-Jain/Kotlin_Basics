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

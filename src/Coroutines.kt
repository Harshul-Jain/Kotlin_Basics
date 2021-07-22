import kotlin.coroutines.*
import javafx.application.Application.*
import javax.xml.bind.JAXBElement

val  numList2=ArrayList<Int>()
//the concept of Multithread is inherited from JAVA in Kotlin.The Kotlin introduces
//the concept of Coroutines .Coroutines allow us to work in different way,Coroutines
//use worker threads .If you have large number of thread say 100 or more your sustem
//can slow down by creation and deletion of Thread .They use a pool of threads lets say
//pool of 4 threads ot pool of  5 threads.Then each Coroutine can be scheduled on
//particular thread and sometimes when a thread has started something and waiting for that to
// end ,That thread might me free,so what kotlin coroutine does is that it reutilizes those
//threads and is able to run more number of tasks in less number of threads.So  Coroutines are
//light weight thread because they actually do not create JAVA thread Object,every coroutine do not
//create JAVA Thread Object,able to run more number of task on less number of thread by reutilizing
//the free time when threads are waiting.
fun main(){
    for(i in 0 until 10000 ){
        numList2.add(i)
    }
    runBlocking{
        launch{printList("1") }//launch and fire
        async{printList("1") }.await()//the next statements cannot be executed before yhis completes
        launch{printList("1") }
    }
    //we use GlobalScope.launch but no output will be printed because the main function immediately end after GlobalScope.launch block
    GlobalScope.launch{
        launch{printList("1") }//launch and fire
        async{printList("1") }.await()//the next statements cannot be executed before yhis completes
        launch{printList("1") }
    }
    runBlocking{
        launch{printList("1") }//launch and fire
        launch{printList("1") }
        launch{printList("1") }
    }
    //The Output of Above runBlocking Block is running in Round Robin Fashion and why there
    //are running in round robin fashion is because this scheduling happens at the compiler level,at software level
}
suspend fun printList(id:String){
    for (i in 0 until 10000){
        if(i%100 ==0){
            //we have written withContext(Dispatchers.IO) because they make run on different worker thread
            //if we do not write this then entire process will run on main Thread
            
            withContext(Dispatchers.IO){
                println("$id $i")
            }
        }
    }
}
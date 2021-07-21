import java.util.concurrent.CopyOnWriteArrayList

//val numList = ArrayList<Int>()
val numList = CopyOnWriteArrayList<Int>()
fun main() {
    for (i in 0 until 10000) {
        numList.add(i)
    }
//    Thread { printList(1 }.start()
//    Thread { printList(2) }.start()
//    Thread { printList(3) }.start
//    dropMultiples(3)
//    dropMultiples(5)
//    dropMultiples(7)
//    println(numList)
//    print("Done")
//    Thread{dropMultiples(3)}.start()
//    Thread{dropMultiples(5)}.start()
//    Thread{dropMultiples(7)}.start()
    val itr=numList.iterator()
    Thread{dropMultiples(3,itr)}.start()
    Thread{dropMultiples(5,itr)}.start()
    Thread{dropMultiples(7,itr)}.start()
}

fun printList(id: Int) {
    for (i in 0 until 10000) {
        if (i % 100 == 0)
            println("${Thread.currentThread().id}  $i")
    }
}

fun dropMultiples(n: Int) {
    // causes ConcurrentModificationException
//    for (i in  numList) {
//        if (i % n == 0) {
//            numList.remove(i)
//        }
//    }
    // OK
//    for (i in 0 until numList.size) {
//        if (i % n == 0) {
//            numList.remove(i)
//        }
//    }
    //Most Correct Way
    val itr=numList.iterator()
    while(itr.hasNext()){
        val i=itr.next()
        if(i%n==0){
           itr.remove()
        }
    }
    //When we do above thing with Threads we get ConcurrentModificationException
    //lets try to give a common iterator to all
}
fun dropMultiples(n: Int,itr:MutableIterator<Int>){
    while(itr.hasNext()){
        val i=itr.next()
        if(i%n==0){
            itr.remove()
        }
    }
    // Still we are getting ConcurrentModificationException
    //To resolve it Easiest Way to make ArrayList as CopyOnWriteArrayList but it is costlier process as ArrayList
    //gets copied for every Thread
}
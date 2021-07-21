val numList = ArrayList<Int>()

fun main() {
    for (i in 0 until 10000) {
        numList.add(i)
    }
//    Thread { printList(1 }.start()
//    Thread { printList(2) }.start()
//    Thread { printList(3) }.start
    dropMultiples(3)
    dropMultiples(5)
    dropMultiples(7)
    println(numList)
    print("Done")
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

}
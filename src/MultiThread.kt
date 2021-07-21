val numList=ArrayList<Int>()

fun main(){
    for (i in 0 until 10000){
       numList.add(i)
    }
    Thread{ printList()}.start()
    Thread{ printList()}.start()
    Thread{ printList()}.start()
}

fun printList() {
    for(i in 0 until 10000){
        if(i%100==0)
        println("${Thread.currentThread().id}  $i")
    }
}

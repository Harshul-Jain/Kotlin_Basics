import kotlin.concurrent.thread

fun main() {
    println("start = ${System.currentTimeMillis()}")
    thread { task1 { println("end = ${System.currentTimeMillis()}")}}
    thread { task2{ println("end = ${System.currentTimeMillis()}")} }
    //Here we have achieved the same time as in Delay.kt but have to
    //two threads to achieve this.Hence Delay.kt was better

}

private fun task1(onEnd:()-> Unit) {
    println("Starting task 1 on ${Thread.currentThread().name}")
    Thread.sleep(1000)
    println("Ending task 1 on ${Thread.currentThread().name}")
    onEnd()
}

private fun task2(onEnd: () -> Unit) {
    println("Starting task 2 on ${Thread.currentThread().name}")
    Thread.sleep(1000)
    println("Ending task 2 on ${Thread.currentThread().name}")
    onEnd()
}
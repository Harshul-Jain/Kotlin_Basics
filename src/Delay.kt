fun main() {
    println("start = ${System.currentTimeMillis()}")
    runBlocking {
        launch { task1() }
        launch { task2() }
    }
    print("end = ${System.currentTimeMillis()}")
    //This takes about 1100 ms to complete.Means that it
    //completes much faster than ThreadSleep.kt
    //Now to achieve same time in ThreadSleep.kt we can modify
    //it { SEE AdvancedThreadSleep.kt}
}

private suspend fun task1() {
    println("Starting task 1 on ${Thread.currentThread().name}")
    delay(1000)
    println("Ending task 1 on ${Thread.currentThread().name}")
}

private suspend fun task2() {
    println("Starting task 2 on ${Thread.currentThread().name}")
    delay(1000)
    println("Ending task 2 on ${Thread.currentThread().name}")
}
fun main() {
    println("start = ${System.currentTimeMillis()}")
    task1()
    task2()
    print("end = ${System.currentTimeMillis()}")
    /* OUTPUT
    start = 1626973581721
    Starting task 1 on main
    Ending task 1 on main
    Starting task 2 on main
    Ending task 2 on main
    end = 1626973583737
     */
    //As we can see it runs on mains Thread and takes 2 sec time(because thread slept two times each for 1 sec)
    // lets see whats happen when we use concept on Coroutines and
    //Delay{ SEE Delay.kt}
}

private fun task1() {
    println("Starting task 1 on ${Thread.currentThread().name}")
    Thread.sleep(1000)
    println("Ending task 1 on ${Thread.currentThread().name}")
}

private fun task2() {
    println("Starting task 2 on ${Thread.currentThread().name}")
    Thread.sleep(1000)
    println("Ending task 2 on ${Thread.currentThread().name}")
}
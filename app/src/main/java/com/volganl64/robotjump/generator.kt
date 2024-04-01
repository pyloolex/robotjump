import kotlin.math.max

const val INF = 1e9.toInt()


fun calc(jump: Int, x: Int, y: Int): Int
{
    return jump - 1 + (x + jump - 1) / jump + (y + jump - 1) / jump
}


fun stat(x: Int, y: Int)
{
    println("$x,$y")
    for (jump in 1 until max(x, y) + 1)
    {
        System.out.printf("%3d", jump)
    }
    println()
    for (jump in 1 until max(x, y) + 1)
    {
        System.out.printf("%3d", calc(jump, x, y))
    }
    println()

}


fun main()
{
    // ulimit -s unlimited
    // kotlinc d.kt -include-runtime -d e.jar && java -jar e.jar < input.txt

    for (i in 0 until 20)
    {
        for (j in 0 until 20)
        {
            stat(i, j)
        }
    }
}

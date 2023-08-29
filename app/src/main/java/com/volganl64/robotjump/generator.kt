
import kotlin.math.max

val INF = 1e9.toInt()


fun calc(jump: Int, x: Int, y: Int): Int
{
    return jump - 1 + (x + jump - 1) / jump + (y + jump - 1) / jump
}


fun solve()
{
    val (tx, ty) = readln().split(" ").map(String::toInt)

    var lf = 0
    var rg = (1e9 + 7).toInt()
    while (rg - lf > 2)
    {
        val mid = (rg - lf) / 3
        val x1 = lf + mid
        val x2 = rg - mid
        //println("$lf $x1 $x2 $rg ${calc(x1, tx, ty)} ${calc(x2, tx, ty)}")
        if (calc(x1, tx, ty) < calc(x2, tx, ty))
        {
            rg = x2
        }
        else
        {
            lf = x1
        }
    }

    //println(calc((rg + lf).toLong() / 2, tx, ty))
    // for (i in 1 until 50)
    // {
    //     println("$i ${calc(i.toLong(), tx, ty)}")
    // }
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

    // val t = readln().toInt()
    // for (i in 0 until t)
    // {
    //     solve()
    // }

    //solve()

    for (i in 0 until 20)
    {
        for (j in 0 until 20)
        {
            stat(i, j)
        }
    }
}

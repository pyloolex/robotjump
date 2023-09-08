package com.volganl64.robotjump


data class LevelProperties(val target: Pair<Int, Int>,
                           val constraints: List<Int>)


val Levels = listOf(
    LevelProperties(Pair(4, 7), listOf(9, 7, 6)),       // 01
    LevelProperties(Pair(9, 6), listOf(10, 8, 7)),
    LevelProperties(Pair(7, 7), listOf(10, 8, 7)),
    LevelProperties(Pair(13, 5), listOf(10, 9, 8)),     // 04
    LevelProperties(Pair(5, 14), listOf(10, 9, 8)),
    LevelProperties(Pair(19, 9), listOf(13, 11, 10)),
    LevelProperties(Pair(8, 25), listOf(14, 12, 11)),   // 07
    LevelProperties(Pair(26, 7), listOf(13, 12, 11)),
    LevelProperties(Pair(27, 28), listOf(16, 15, 14)),
    LevelProperties(Pair(37, 50), listOf(20, 19, 18)),  // 10
    LevelProperties(Pair(49, 46), listOf(22, 20, 19)),
    LevelProperties(Pair(26, 81), listOf(22, 21, 20)),
    LevelProperties(Pair(95, 58), listOf(26, 25, 24)),  // 13
    LevelProperties(Pair(80, 97), listOf(28, 27, 26)),
    LevelProperties(Pair(99, 91), listOf(29, 28, 27)),
)

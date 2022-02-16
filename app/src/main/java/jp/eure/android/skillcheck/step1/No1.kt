package jp.eure.android.skillcheck.step1

/**
 * N個の要素を持つ整数型の配列が入力として与えられる。
 * その配列を以下のように加工し、文字列型の配列として出力せよ。
 * 最初と最後の要素にはそれぞれ"top-"と"bottom-"を先頭に付与する。
 * それ以外の要素には"middle-"を先頭に付与する。
 *
 * 入力例1: [0, 1, 2, 3]
 * 出力例1: ["top-0", "middle-1", "middle-2", "bottom-3"]
 *
 * 入力例2: [0, 1]
 * 出力例2: ["top-0", "bottom-1"]
 *
 * 入力例3: [0]
 * 出力例3: ["top-0"]
 */
object No1 {

    fun run(inputs: List<Int>): List<String> {
        val stringList = inputs.map { it.toString() }.toMutableList()

        for((index, value) in stringList.withIndex()){
            when (index) {
                0 -> stringList[0] = "top-$value"
                stringList.size - 1 -> stringList[stringList.size - 1] = "bottom-$value"
                else -> stringList[index] = "middle-$value"
            }
        }

        return stringList
    }

}

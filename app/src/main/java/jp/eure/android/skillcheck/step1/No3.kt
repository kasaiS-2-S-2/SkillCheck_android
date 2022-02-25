package jp.eure.android.skillcheck.step1

/**
 * 昇順に整列された2つの整数型の配列に共通する要素を出力せよ。
 *
 * 入力例1: [2, 4, 7, 9], [1, 3, 4, 5, 8, 9]
 * 出力例1: [4, 9]
 *
 * 入力例2: [1, 2, 3, 4], [5, 6, 7, 8]
 * 出力例2: []
 *
 * 入力例3: [1]
 * 出力例3: [1]
 */
object No3 {

    // 時間足らずでできなかったが、ハッシュテーブルを用いれば計算量をNにできたと思う。
    fun run(inputs1: List<Int>, inputs2: List<Int>): List<Int> {
        val answer = mutableListOf<Int>()
        for(value1 in inputs1){
            for(value2 in inputs2){
                if (value1 == value2) {
                    answer.add(value1)
                }
            }
        }

        return answer.toList()
    }

}
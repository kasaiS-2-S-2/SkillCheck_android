package jp.eure.android.skillcheck.step1

/**
 * 以下に示すような木構造を持つオブジェクト(Root node)があるとする。
 * 入力として与えられる2つのノードに共通する親ノード(Ancestor node)を出力せよ。
 * - A
 *   - B
 *     - C
 *     - D
 *       - E
 *
 * 入力例1: C, E
 * 出力例2: B
 *
 * 入力例2: B, E
 * 出力例2: A
 *
 * 入力例3: A, B
 * 出力例3: null
 */
object No2 {

    fun run(root: Node, inputA: String, inputB: String): Node? {

    }

}

data class Node(
    val value: String,
    val children: List<Node>
)
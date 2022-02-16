package jp.eure.android.skillcheck

import jp.eure.android.skillcheck.step1.No1
import jp.eure.android.skillcheck.step1.No2
import jp.eure.android.skillcheck.step1.No3
import jp.eure.android.skillcheck.step1.Node
import org.junit.Test

class Step1Test {

    private fun assert(expected: Any?, actual: Any?) {
        assert(
            value = expected == actual,
            lazyMessage = { "Expected: $expected, Actual: $actual" }
        )
    }

    @Test
    fun testNo1() {
        var inputs = listOf(0, 1, 2, 3)
        var expected = listOf("top-0", "middle-1", "middle-2", "bottom-3")
        var actual = No1.run(inputs)
        assert(expected, actual)

        inputs = listOf(0, 1)
        expected = listOf("top-0", "bottom-1")
        actual = No1.run(inputs)
        assert(expected, actual)

        inputs = listOf(0)
        expected = listOf("top-0")
        actual = No1.run(inputs)
        assert(expected, actual)
    }

    @Test
    fun testNo2() {
        val root = Node(
            value = "A",
            children = listOf(
                Node(
                    value = "B",
                    children = listOf(
                        Node(
                            value = "C",
                            children = emptyList()
                        ),
                        Node(
                            value = "D",
                            children = listOf(
                                Node(
                                    value = "E",
                                    children = emptyList()
                                )
                            )
                        )
                    )
                )
            )
        )

        var inputA = "C"
        var inputB = "E"
        var expected: String? = "B"
        var actual = No2.run(root, inputA, inputB)
        assert(expected, actual?.value)

        inputA = "B"
        inputB = "E"
        expected = "A"
        actual = No2.run(root, inputA, inputB)
        assert(expected, actual?.value)

        inputA = "A"
        inputB = "B"
        expected = null
        actual = No2.run(root, inputA, inputB)
        assert(expected, actual?.value)
    }

    @Test
    fun testNo3() {
        var inputs1 = listOf(2, 4, 7, 9)
        var inputs2 = listOf(1, 3, 4, 5, 8, 9)
        var actual = No3.run(inputs1, inputs2)
        var expected = listOf(4, 9)
        assert(expected, actual)

        inputs1 = listOf(1, 2, 3, 4)
        inputs2 = listOf(5, 6, 7, 8)
        actual = No3.run(inputs1, inputs2)
        expected = emptyList()
        assert(expected, actual)

        inputs1 = listOf(1)
        inputs2 = listOf(1)
        actual = No3.run(inputs1, inputs2)
        expected = listOf(1)
        assert(expected, actual)
    }

}
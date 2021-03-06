package board

import org.junit.Assert.*
import org.junit.Test

class TestSquareBoard {

    private fun Cell?.asString() = if (this != null) "($i, $j)" else ""

    private fun Collection<Cell>.asString() = joinToString(prefix = "[", postfix = "]") { it.asString() }

    @Test
    fun testAllCells() {
        val board = createSquareBoard(2)
        val cells = board.getAllCells().sortedWith(compareBy<Cell> { it.i }.thenBy { it.j })
        assertEquals("[(1, 1), (1, 2), (2, 1), (2, 2)]", cells.asString())
    }

    @Test
    fun testCell() {
        val board = createSquareBoard(2)
        val cell = board.getCellOrNull(1, 2)
        assertEquals(1, cell?.i)
        assertEquals(2, cell?.j)
    }

    @Test
    fun testNoCellAsNull() {
        val board = createSquareBoard(2)
        val cell = board.getCellOrNull(3, 3)
        assertEquals(null, cell)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNoCellAsException() {
        val board = createSquareBoard(2)
        board.getCell(3, 3)
    }

    @Test
    fun testCellFound() {
        val board = createSquareBoard(2)
        board.getCell(1, 1)
        board.getCell(1, 2)
        board.getCell(2, 1)
        board.getCell(2, 2)
    }

    @Test
    fun testRow() {
        val board = createSquareBoard(2)
        val row = board.getRow(1, 1..2)
        assertEquals("[(1, 1), (1, 2)]", row.asString())
    }

    @Test
    fun testColumn() {
        val board = createSquareBoard(2)
        val column = board.getColumn(1..2, 1)
        assertEquals("[(1, 1), (2, 1)]", column.asString())
    }

    @Test
    fun testRowReversed() {
        val board = createSquareBoard(2)
        val row = board.getRow(1, 2 downTo 1)
        assertEquals("[(1, 2), (1, 1)]", row.asString())
    }

    @Test
    fun testRowWrongRange() {
        val board = createSquareBoard(2)
        val row = board.getRow(1, 1..10)
        assertEquals("[(1, 1), (1, 2)]", row.asString())
    }

    @Test
    fun testNeighbour() {
        val board = createSquareBoard(2)
        with(board) {
            val cell = getCellOrNull(1, 1)
            assertNotNull(cell)
            assertNull(cell!!.getNeighbour(Direction.UP))
            assertNull(cell.getNeighbour(Direction.LEFT))
            assertEquals("(2, 1)", cell.getNeighbour(Direction.DOWN).asString())
            assertEquals("(1, 2)", cell.getNeighbour(Direction.RIGHT).asString())
        }
    }
}

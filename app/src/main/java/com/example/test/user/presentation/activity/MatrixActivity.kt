package com.example.test.user.presentation.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test.R
import com.example.test.databinding.ActivityMatrixBinding

class MatrixActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMatrixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatrixBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val inputSize = 5 // Example user input
        val matrix = generateColorMatrix(inputSize)
        val pairCount = countColorPairs(matrix)
        displayMatrix(binding.gridLayout, matrix)
        Log.d("## MatrixPairs", "Total pairs with the same color: $pairCount")
        Toast.makeText(this@MatrixActivity,"Total pairs with the same color: $pairCount" ,Toast.LENGTH_LONG).show()

    }

    fun generateColorMatrix(size: Int): Array<Array<Int>> {
        val colors = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN)
        return Array(size) { Array(size) { colors.random() } }
    }
    fun countColorPairs(matrix: Array<Array<Int>>): Int {
        var count = 0
        val visited = mutableSetOf<Pair<Int, Int>>()

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                val color = matrix[i][j]
                val neighbors = listOf(
                    i to j + 1, // Right
                    i + 1 to j  // Down
                )

                for (neighbor in neighbors) {
                    if (neighbor.first in matrix.indices && neighbor.second in matrix[i].indices &&
                        matrix[neighbor.first][neighbor.second] == color &&
                        neighbor !in visited
                    ) {
                        count++
                        visited.add(neighbor)
                    }
                }
            }
        }
        return count
    }
    fun displayMatrix(gridLayout: GridLayout, matrix: Array<Array<Int>>) {
        gridLayout.removeAllViews()
        gridLayout.columnCount = matrix.size
        gridLayout.rowCount = matrix.size

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                val square = View(gridLayout.context).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 100
                        height = 100
                        setMargins(5, 5, 5, 5)
                    }
                    setBackgroundColor(matrix[i][j])
                }
                gridLayout.addView(square)
            }
        }
    }

}
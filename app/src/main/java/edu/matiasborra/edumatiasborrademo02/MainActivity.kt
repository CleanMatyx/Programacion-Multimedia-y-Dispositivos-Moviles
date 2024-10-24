package edu.matiasborra.edumatiasborrademo02

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import edu.matiasborra.edumatiasborrademo02.adapters.ItemsAdapter
import edu.matiasborra.edumatiasborrademo02.databinding.ActivityMainBinding
import edu.matiasborra.edumatiasborrademo02.model.Items

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        itemsAdapter = ItemsAdapter(
            Items.items,
            itemClick = { item ->
                DetailActivity.navigateToDetail(this, item.id)
            },
            imageClick = { item ->
                showSnackbar("Image clicked: ${item.id}").show()
            }
        )
        binding.recyclerView.adapter = itemsAdapter
    }
    // Show a snackbar with a message
    private fun showSnackbar(message: String) : Snackbar {
        val snackbar = Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        )
        val params = CoordinatorLayout.LayoutParams(snackbar.view.layoutParams)
        params.gravity = Gravity.BOTTOM
        params.setMargins(0, 0, 0, -binding.root.paddingBottom)
        snackbar.view.layoutParams = params

        return snackbar
    }
}
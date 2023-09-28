package com.example.newwarehouseapp.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newwarehouseapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.presentation.add_input_note.AddInputNoteFragmentDirections
import androidx.navigation.fragment.findNavController
import com.example.newwarehouseapp.data.db.WarehouseDatabase
import androidx.lifecycle.lifecycleScope
import com.example.newwarehouseapp.data.db.dto.ProductDto
import com.example.newwarehouseapp.data.db.dto.ProductOnWarehouseDto
import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductOnWarehouse
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    private val sharedPreferences: SharedPreferences by inject()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val dao = WarehouseDatabase.getInstance(this).dao

        var productCount = 1000
        val rnds : Int = (0..10).random()
        var i = 0

        lifecycleScope.launch {
            while (i<productCount){
                var product =
                    ProductDto(
                        id = 0,
                        name = "product"+i.toString(),
                        description = "Just program num"+i.toString(),
                        price = (10..1000).random()
                    )
                var id = dao.insertProduct(product)
                dao.insertProductOnWarehouse(
                    ProductOnWarehouseDto(
                        id = 0,
                        warehouseIdOfProduct = id.toInt(),
                        count =  (50..200).random()
                    )
                )
                i++
            }
        }

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment

        setSupportActionBar(findViewById(R.id.toolbar))
        var bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)


        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.inputNotesFragment,R.id.outputNotesFragment, R.id.warehouseFragment ->
                        bottomNavigationView.visibility = View.VISIBLE
                    else -> bottomNavigationView.visibility = View.GONE
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.newwarehouseapp.R.menu.sign_out_menu, menu)
        return true
    }
}
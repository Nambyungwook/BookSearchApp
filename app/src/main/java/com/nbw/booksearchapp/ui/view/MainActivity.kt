package com.nbw.booksearchapp.ui.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nbw.booksearchapp.R
import com.nbw.booksearchapp.data.db.BookSearchDatabase
import com.nbw.booksearchapp.data.repository.BookSearchRepositoryImpl
import com.nbw.booksearchapp.databinding.ActivityMainBinding
import com.nbw.booksearchapp.ui.viewmodel.BookSearchViewModel
import com.nbw.booksearchapp.ui.viewmodel.BookSearchViewModelProviderFactory
import com.nbw.booksearchapp.util.Constants.DATASTORE_NAME

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    // DataStore의 Singleton 객체 생성
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        setupBottomNavigationView()
//        // 앱이 처음 실행되었을때 SearchFragment가 실행되도록 설정
//        if (savedInstanceState == null) {
//            binding.bottomNavigationView.selectedItemId = R.id.fragment_search
//        }
        setupJetpackNavigation()

        val database = BookSearchDatabase.getInstance(this)
        val bookSearchRepository = BookSearchRepositoryImpl(database, dataStore)
        val factory = BookSearchViewModelProviderFactory(bookSearchRepository, this)
        bookSearchViewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]
    }

    private fun setupJetpackNavigation() {
        val host = supportFragmentManager
            .findFragmentById(R.id.booksearch_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_search, R.id.fragment_favorite, R.id.fragment_setting
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//    private fun setupBottomNavigationView() {
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.fragment_search -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, SearchFragment())
//                        .commit()
//                    true
//                }
//                R.id.fragment_favorite -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, FavoriteFragment())
//                        .commit()
//                    true
//                }
//                R.id.fragment_setting -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, SettingFragment())
//                        .commit()
//                    true
//                }
//                else -> false
//            }
//        }
//    }
}
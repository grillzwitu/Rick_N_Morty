package com.example.rick_n_morty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_n_morty.api.CharRepository
import com.example.rick_n_morty.api.CharacterApi
import com.example.rick_n_morty.api.Response
import com.example.rick_n_morty.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val character = mutableListOf<Response>()

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this, MainViewModelFactory(
                CharRepository(
                    CharacterApi.apiService
                )
            )
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Rick and Morty"

        val mainAdapter = MainAdapter(character)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = mainAdapter

        viewModel.characterLiveData.observe(this, {
            character.addAll(it)
            mainAdapter.notifyDataSetChanged()
        })
    }
}
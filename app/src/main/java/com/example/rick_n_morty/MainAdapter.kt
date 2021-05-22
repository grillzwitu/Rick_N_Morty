package com.example.rick_n_morty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rick_n_morty.api.Results
import com.example.rick_n_morty.databinding.CharItemsBinding


class MainAdapter(private val charList: List<Results>) :
    RecyclerView.Adapter<MainAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(charList[position])

    override fun getItemCount(): Int = charList.size

    inner class CharacterViewHolder(private val binding: CharItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Results) {
            binding.charImage.load(character.characterImage)
            binding.charName.text = character.characterName
            binding.charStatus.text = character.characterStatus
            binding.charSpecie.text = character.characterSpecies
        }

    }

}
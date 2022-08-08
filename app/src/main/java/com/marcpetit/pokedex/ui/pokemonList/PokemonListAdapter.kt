package com.marcpetit.pokedex.ui.pokemonList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcpetit.pokedex.databinding.PokemonListItemBinding
import com.marcpetit.pokedex.domainmodel.PokemonListDisplay
import com.squareup.picasso.Picasso

class PokemonListAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<PokemonListDisplay, PokemonListViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<PokemonListDisplay>() {

        override fun areItemsTheSame(p0: PokemonListDisplay, p1: PokemonListDisplay): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(p0: PokemonListDisplay, p1: PokemonListDisplay): Boolean {
            return p0 == p1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonListViewHolder(PokemonListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    override fun getItemCount(): Int = currentList.size
}

class PokemonListViewHolder(private val binding: PokemonListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(pokemon: PokemonListDisplay, onClick: (String) -> Unit) {
        with(binding) {
            Picasso.get().load(pokemon.sprites?.frontDefault).into(pokemonSprite)
            pokemonNumber.text = "#${pokemon.id.toString().padStart(3, '0')}"
            pokemon.name?.apply {
                pokemonSprite.contentDescription = this
                pokemonName.text = replaceFirstChar(Char::uppercase)
                root.setOnClickListener { onClick(this) }
            }
        }
    }
}

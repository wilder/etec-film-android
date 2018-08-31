package br.com.etec.etec_film_android.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.etec.etec_film_android.R
import kotlinx.android.synthetic.main.card_film.view.*

class MainAdapter(val items: ArrayList<Film>, val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_film, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        if (holder != null) {
            val film = items[position]
            //holder.title.text = note.title
            holder.ivFilmNome.text = film.nome
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFilmCartaz = itemView.iv_cfilm_cartaz
        val ivFilmNome = itemView.tv_cfilm_nome
    }

}
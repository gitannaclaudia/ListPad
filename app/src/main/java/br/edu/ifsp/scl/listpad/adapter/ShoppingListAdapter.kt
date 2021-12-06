package br.edu.ifsp.scl.listpad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.listpad.R
import br.edu.ifsp.scl.listpad.model.ShoppingList
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class ShoppingListAdapter(options: FirestoreRecyclerOptions<ShoppingList>)
    : FirestoreRecyclerAdapter<ShoppingList, ShoppingListAdapter.ShoppingListViewHolder>(options)
{
    var clickListener: ShoppingListClickListener?=null

    inner class ShoppingListViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val nomeVH = view.findViewById<TextView>(R.id.nomeTv)
        val descricaoVH = view.findViewById<TextView>(R.id.descricaoTv)
        val dataVH = view.findViewById<TextView>(R.id.dataTv)
        init {
            view.setOnClickListener { clickListener?.onItemClick(bindingAdapterPosition) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingListAdapter.ShoppingListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shoppinglist_celula, parent, false)
        return ShoppingListViewHolder(view)
    }

    interface ShoppingListClickListener
    {
        fun onItemClick(pos: Int)
    }

    override fun onBindViewHolder(
        holder: ShoppingListAdapter.ShoppingListViewHolder,
        position: Int,
        model: ShoppingList
    ) {
        holder.nomeVH.text = model.nome
        holder.descricaoVH.text = model.descricao
        holder.dataVH.text = model.data
    }
}
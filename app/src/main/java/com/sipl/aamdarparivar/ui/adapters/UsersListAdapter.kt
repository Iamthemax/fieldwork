package com.sipl.aamdarparivar.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sipl.aamdarparivar.R
import com.sipl.aamdarparivar.database.entity.Users

class UsersListAdapter(private var list:ArrayList<Users>): RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName=itemView.findViewById<TextView>(R.id.tvName)
        val tvMobile=itemView.findViewById<TextView>(R.id.tvMobile)
        val tvTaluka=itemView.findViewById<TextView>(R.id.tvTaluka)
        val tvVillage=itemView.findViewById<TextView>(R.id.tvVillage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.ViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_user_row,parent,false);
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: UsersListAdapter.ViewHolder, position: Int) {
        holder.tvName.text="${list.get(position).firstName} ${list.get(position).middleName} ${list.get(position).lastName} "
        holder.tvVillage.text=list.get(position).villageName
        holder.tvTaluka.text=list.get(position).talukaName
        holder.tvMobile.text=list.get(position).mobile
    }
    override fun getItemCount(): Int {
        return list.size
    }
}
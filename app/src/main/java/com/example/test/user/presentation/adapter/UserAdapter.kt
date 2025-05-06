package com.example.test.user.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.user.domain.model.UserDomain
import com.example.test.databinding.RawUserBinding

class UserAdapter (private var userList:List<UserDomain>):RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private lateinit var itemClick:ItemClick
    var searchList : List<UserDomain> = userList!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val b = RawUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(b)
    }

    fun SetUpInteface(itemClick: ItemClick){
        this.itemClick = itemClick
    }

    interface ItemClick{
        fun DataClick(userDomain: UserDomain)
    }
    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.b.txtName.text = searchList.get(position).userName

        holder.b.txtName.setOnClickListener({
            itemClick.DataClick(searchList.get(position))
        })
    }

    fun searchUser(char:CharSequence){
        if (char.isEmpty()){
            searchList = userList
        }else{
            var filterList: ArrayList<UserDomain> = ArrayList<UserDomain>()
            var flag = 0

            for(raw in userList){

                if (raw.userName.lowercase().contains(char.toString().lowercase())){
                    flag =1
                    filterList.add(raw)

                }
            }
            if (flag==1){
                searchList = filterList
            }
        }
        notifyDataSetChanged()
    }

    class UserViewHolder(var b:RawUserBinding):RecyclerView.ViewHolder(b.root)
}
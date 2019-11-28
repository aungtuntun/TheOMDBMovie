package com.imceits.aungtuntun.theomdbmovie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.imceits.aungtuntun.theomdbmovie.data.model.Cast
import com.imceits.aungtuntun.theomdbmovie.databinding.CastItemBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>(){
    private var dataList: List<Cast> = emptyList()

    fun setData(@Nullable items: List<Cast>) {
        dataList = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemBinding: CastItemBinding = CastItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return CastViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        return holder.onBind(dataList[position])
    }

    class CastViewHolder(itemView: CastItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val itemBinding = itemView

        fun onBind(cast: Cast) {
            itemBinding.cast = cast
            itemBinding.executePendingBindings()
        }
    }
}
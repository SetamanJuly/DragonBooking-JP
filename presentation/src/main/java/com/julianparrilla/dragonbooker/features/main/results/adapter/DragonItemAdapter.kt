package com.julianparrilla.dragonbooker.features.main.results.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.ResultsDataState
import com.julianparrilla.domain.model.toCurrency
import com.julianparrilla.dragonbooker.databinding.ItemDragonBinding
import com.julianparrilla.dragonbooker.utils.loadCrop

class DragonItemAdapter(
    private val userItems: List<ResultsDataState>,
    private val currencyDataState: CurrencyDataState
) : RecyclerView.Adapter<DragonItemAdapter.ViewHolder>() {

    lateinit var binding: ItemDragonBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemDragonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, currencyDataState)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userItems[position])
    }

    override fun getItemCount(): Int = userItems.size

    class ViewHolder(private val binding: ItemDragonBinding, private val currencyDataState: CurrencyDataState) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: ResultsDataState) {
            binding.ivOriginAirline.loadCrop(item.inbound.airlineImage)
            binding.ivDestinationAirline.loadCrop(item.outbound.airlineImage)
            binding.tvOriginAirline.text = item.inbound.airline
            binding.tvOriginDate.text = item.inbound.arrivalDate
            binding.tvOrigin.text = item.inbound.origin
            binding.tvDestination.text = item.outbound.origin
            binding.tvDestinationAirline.text = item.outbound.airline
            binding.tvDestinationDate.text = item.outbound.arrivalDate
            binding.tvPrice.text = item.toCurrency(currencyDataState)
        }
    }
}

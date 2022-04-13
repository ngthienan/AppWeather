package com.annt.igitproject.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.annt.appweather.R
import com.annt.appweather.model.WeatherDataModel

class WeatherAdapter(var list : ArrayList<WeatherDataModel> ) : ListAdapter<WeatherDataModel, NoteOldViewHolder>(WeatherDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteOldViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteOldViewHolder(inflater.inflate(R.layout.item_weather, parent, false))

    }

    override fun onBindViewHolder(holder: NoteOldViewHolder, position: Int) {
        holder.bindData(list[position])
    }
}

class NoteOldViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    private val tvAveTemp: TextView = itemView.findViewById(R.id.tvAveTemp)
    private val tvPressure: TextView = itemView.findViewById(R.id.tvPressure)
    private val tvHumidity: TextView = itemView.findViewById(R.id.tvHumidity)
    private val tvDescription: TextView = itemView.findViewById(R.id.tvDesrciption)


    fun bindData(weatherDateModel: WeatherDataModel) {
        tvDate.text = weatherDateModel.date.toString()
        tvAveTemp.text = weatherDateModel.eve.toString()
        tvPressure.text = weatherDateModel.pressure.toString()
        tvHumidity.text = weatherDateModel.humidity.toString()
        tvDescription.text = weatherDateModel.description.toString()
    }
}


class WeatherDiffCallBack : DiffUtil.ItemCallback<WeatherDataModel>() {

    override fun areItemsTheSame(oldItem: WeatherDataModel, newItem: WeatherDataModel): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: WeatherDataModel, newItem: WeatherDataModel): Boolean {
        return oldItem == newItem
    }

}

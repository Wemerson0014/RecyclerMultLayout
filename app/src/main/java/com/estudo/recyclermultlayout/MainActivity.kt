package com.estudo.recyclermultlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = arrayListOf(
            1 to "Title",
            0 to "ItemA",
            0 to "ItemB",
            1 to "ItemC",
            0 to "ItemD",
            0 to "ItemE"
        )

        val adapter = MyAdapter(items)
        findViewById<RecyclerView>(R.id.my_recycler).adapter = adapter
    }

    class MyAdapter(private val items: List<Pair<Int, String>>): RecyclerView.Adapter<MyAdapter.Holder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return if (viewType == 0) {
                MyContentHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_a, parent, false))
            } else {
                MyHeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_b, parent, false))
            }
        }

        override fun getItemViewType(position: Int): Int {
            return items[position].first
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int {
            return items.size
        }

        abstract class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            abstract fun bind(obj: Pair<Int, String>)
        }

        class MyContentHolder(itemView: View) : Holder(itemView) {

            private val textView : TextView = itemView.findViewById(R.id.txt)

            override fun bind(obj: Pair<Int, String>) {
                textView.text = obj.second
            }

        }

        class MyHeaderHolder(itemView: View) : Holder(itemView) {
            override fun bind(obj: Pair<Int, String>) {
            }

        }

    }
}
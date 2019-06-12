package io.github.kenneycode.funrenderer.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.kenneycode.funrenderer.uitl.FileUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_sample.view.*

class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FileUtil.context = applicationContext
        setContentView(R.layout.activity_main)
        val sampleActivities = arrayOf(
                SampleItem(getString(R.string.basic_usage), SampleBasicUsageActivity::class.java)
        )
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        sampleList.layoutManager = layoutManager
        sampleList.adapter = Adapter(sampleActivities)
    }

    inner class Adapter(private val sampleActivities: Array<SampleItem>) : RecyclerView.Adapter<VH>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_sample, null, false))
        }

        override fun getItemCount(): Int {
            return sampleActivities.size
        }

        override fun onBindViewHolder(vh: VH, index: Int) {
            vh.itemView.sampleButton.text = sampleActivities[index].sampleSame
            vh.itemView.sampleButton.setOnClickListener {
                val intent = Intent(this@MainActivity, sampleActivities[index].sampleActivity)
                this@MainActivity.startActivity(intent)
            }
        }

    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class SampleItem(val sampleSame: String, val sampleActivity: Class<*>)

}
package com.my.sapiaassignment.uilayer.petslist

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.my.sapiaassignment.R
import com.my.sapiaassignment.datalayer.apimodel.PetItem
import com.my.sapiaassignment.utility.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PetsListAdapter(var context: Context) :
    RecyclerView.Adapter<PetsListAdapter.ViewHolder>() {
    private var list: List<PetItem>? = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<PetItem>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pet_item_raw, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val petItem = list?.get(position)
        holder.title.text = petItem?.title ?: ""
        setImage(holder, petItem)
        holder.imageView.setOnClickListener {
            val bundle = bundleOf(Constants.SELECTED_PET_CONTENT_URL to (petItem?.content_url ?: ""))
            holder.imageView.findNavController().navigate(
                R.id.action_petslist_to_petdetail,
                bundle
            )
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    private fun setImage(holder: ViewHolder, petItem: PetItem?) {
        val builder = Picasso.Builder(context)
        builder.listener { _, _, exception ->
            Log.i(
                TAG,
                "onImageLoadFailed: " + exception!!.message
            )
        }
        builder.build().load(petItem?.image_url ?: "")
            .placeholder(android.R.drawable.ic_delete)
            .error(android.R.drawable.ic_btn_speak_now)
            .into(holder.imageView, object : Callback {
                override fun onSuccess() {
                    holder.progressBar.visibility = View.GONE
                    holder.title.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    holder.progressBar.visibility = View.VISIBLE
                    holder.title.visibility = View.GONE

                }
            })

    }


    // Holds the views for adding it to image and text
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var imageView: ImageView = item.findViewById(R.id.missionImage)
        var progressBar: ProgressBar = item.findViewById(R.id.progressBar)
        var title: TextView = item.findViewById(R.id.title)
    }

    companion object {
        private const val TAG = "NasaGridImageAdapter"
    }
}
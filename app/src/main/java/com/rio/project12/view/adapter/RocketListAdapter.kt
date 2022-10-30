package com.rio.project12.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rio.project12.R
import com.rio.project12.model.Rec
import com.rio.project12.model.database.DRocketHistory

/**
 *  ViewAdapter is an user defined adapter class that extends RecyclerView's Adapter class
 *
 *  info:
 *      Creates and bind the item view for the recycler view and return it to the Recyclerview
 *      with the layout we designed and provided
 *
 *  Methodology:
 *      -> it get the list of elements from the the Activity/Fragment and assign it to the
 *      instance variable items
 *      -> then it counts the number of items in the list (i.e items --> instance variable)
 *      -> Then it creates view for for the list of items(using View holder class) and return it for binding
 *      -> once the list creation is completed the values in the list of the elements are binded and
 *      returned to the recycler view
 *
 */
class RocketListAdapter (private val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    /**
     * items is the instance variable that stores the list of items from the activity/fragment
     */
    var items:List<DRocketHistory> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * onCreate is an override method of the class RecyclerView.Adapter
     *
     * @Methodology:
     *      creates the instance of the viewHolder class
     *
     * @returns the object of the class ViewHolder
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recycler_view_item_rocket,
                parent, false)
        )
    }

    /**
     * onBindViewHolder is an override method of the class RecyclerView.Adapter
     *
     * @Methodology:
     *      binds the list of items with the viewHolders
     *
     *      here holder's are verified for the user defined ViewHolder class
     *      as the parameter is of increased scope
     *
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.image.load(items[position].image ?: Rec.noImageFound)
            holder.name.text = items[position].name
            holder.apply {
                name.setOnClickListener {
                    navigate(holder, position)
                }
                cardView.setOnClickListener {
                    navigate(holder, position)
                }
            }
        }
    }

    /**
     * method to redirect to the fragment reocket_info_fragment
     */
    private fun navigate(holder:ViewHolder, position:Int){
        Rec.dRocketHistory = items[position]
        Navigation.findNavController(holder.name).navigate(R.id.action_rocketListFragment_to_rocketInfoFragment)
    }

    /**
     * getItemCount is an override method of the class RecyclerView.Adapter
     *
     * @returns the count of the elements in the items list
     */
    override fun getItemCount(): Int = items.size



    /**
     * class ViewHolder is an user defined class that extends the ViewHolder class of RecyclerView
     *  Constructor of this class takes object of the class View
     * @Methodology:
     *      it binds layout components with the instance variable
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name:TextView = view.findViewById(R.id.nameType1)
        val cardView :CardView = view.findViewById(R.id.cardViewInnerRocketView1)
        val image:ImageView = view.findViewById(R.id.imageView1)
    }

    /**
     * class ViewHolder is an user defined class that extends the ViewHolder class of RecyclerView
     *  Constructor of this class takes object of the class View
     * @Methodology:
     *      it binds layout components with the instance variable
     */
    class ViewHolder2(view: View) : RecyclerView.ViewHolder(view){
        val name:TextView = view.findViewById(R.id.nameViewType2)
        val image:ImageView = view.findViewById(R.id.imageviewType2)
    }
}
package com.darta.matchmaking.fragment.swipe

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.darta.matchmaking.R
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_swipe.*
import java.util.*


class SwipeFragment : Fragment() {


    private val cardStackView : CardStackView? = null
    private var manager: CardStackLayoutManager? = null
    private var adapter: CardStackAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_swipe, container, false)



        val button = root.findViewById<Button>(R.id.btn)
        button.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
            manager?.setSwipeAnimationSetting(setting)
            cardStackView?.swipe()

        }
        init(root)
        return root
    }

    private fun init(root: View) {
        val cardStackView: CardStackView = root.findViewById(R.id.card_stack_view)
        manager = CardStackLayoutManager(context, object : CardStackListener {
            override fun onCardDragging(direction: Direction, ratio: Float) {
                Log.d(TAG, "onCardDragging: d=" + direction.name + " ratio=" + ratio)
            }

            override fun onCardSwiped(direction: Direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager!!.topPosition + " d=" + direction)
                if (direction == Direction.Right) {
                    Toast.makeText(context, "Direction Right", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Top) {
                    Toast.makeText(context, "Direction Top", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Left) {
                    Toast.makeText(context, "Direction Left", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Bottom) {
                    Toast.makeText(context, "Direction Bottom", Toast.LENGTH_SHORT).show()
                }

                // Paginating
                if (manager!!.topPosition == adapter!!.itemCount - 5) {
                    paginate()
                }
            }

            override fun onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager!!.topPosition)
            }

            override fun onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager!!.topPosition)
            }

            override fun onCardAppeared(view: View, position: Int) {
                val tv = view.findViewById<TextView>(R.id.item_name)
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.text)
            }

            override fun onCardDisappeared(view: View, position: Int) {
                val tv = view.findViewById<TextView>(R.id.item_name)
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.text)
            }
        })
        manager!!.setStackFrom(StackFrom.None)
        manager!!.setVisibleCount(3)
        manager!!.setTranslationInterval(8.0f)
        manager!!.setScaleInterval(0.95f)
        manager!!.setSwipeThreshold(0.3f)
        manager!!.setMaxDegree(20.0f)
        manager!!.setDirections(Direction.FREEDOM)
        manager!!.setCanScrollHorizontal(true)
        manager!!.setSwipeableMethod(SwipeableMethod.Manual)
        manager!!.setOverlayInterpolator(LinearInterpolator())
        adapter = CardStackAdapter(addList() as List<ItemModel>)
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator = DefaultItemAnimator()
    }

    private fun paginate() {
        val old = adapter!!.getItems()
        val baru: Any? = ArrayList<Any?>(addList())
        val callback = CardStackCallback(old, baru as List<ItemModel>)
        val hasil = DiffUtil.calculateDiff(callback)
        adapter!!.setItems(baru)
        hasil.dispatchUpdatesTo(adapter!!)
    }

    private fun addList(): List<ItemModel?>? {
        val items: MutableList<ItemModel?> = ArrayList()
        items.add(ItemModel(R.drawable.sample1, "Markonah", "24", "Jember"))
        items.add(ItemModel(R.drawable.sample2, "Marpuah", "20", "Malang"))
        items.add(ItemModel(R.drawable.sample3, "Sukijah", "27", "Jonggol"))
        items.add(ItemModel(R.drawable.sample4, "Markobar", "19", "Bandung"))
        items.add(ItemModel(R.drawable.sample5, "Marmut", "25", "Hutan"))
        items.add(ItemModel(R.drawable.sample1, "Markonah", "24", "Jember"))
        items.add(ItemModel(R.drawable.sample2, "Marpuah", "20", "Malang"))
        items.add(ItemModel(R.drawable.sample3, "Sukijah", "27", "Jonggol"))
        items.add(ItemModel(R.drawable.sample4, "Markobar", "19", "Bandung"))
        items.add(ItemModel(R.drawable.sample5, "Marmut", "25", "Hutan"))
        return items
    }

}


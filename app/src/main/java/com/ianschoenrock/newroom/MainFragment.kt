package com.ianschoenrock.newroom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private lateinit var submitBTN: Button
    private lateinit var itemET: EditText
    private lateinit var itemRV: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false).apply{
            ///////////////////////////////////////////////////////////////////////
            //Room Code
            ///////////////////////////////////////////////////////////////////////

            val db = Room.databaseBuilder(
                requireActivity().applicationContext,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().build()



            val itemDao = db.itemDao()
            var dbItems: List<Item> = itemDao.getAll()

            ///////////////////////////////////////////////////////////////////////
            //View Code
            ///////////////////////////////////////////////////////////////////////


            submitBTN = findViewById(R.id.submit_btn)
            itemET = findViewById(R.id.item_et)
            itemRV = findViewById(R.id.items_rv)

            adapter = ItemRecyclerAdapter(dbItems)
            itemRV.adapter = adapter
            itemRV.layoutManager = LinearLayoutManager(requireContext())

            submitBTN.setOnClickListener {
                MainScope().launch {
                    itemDao.insert(Item(item = itemET.text.toString()))
                    dbItems = itemDao.getAll()

                    adapter = ItemRecyclerAdapter(dbItems)

                    itemRV.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
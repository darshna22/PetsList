package com.my.sapiaassignment.uilayer.petslist

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.sapiaassignment.MyApplication
import com.my.sapiaassignment.R
import com.my.sapiaassignment.base.di.activity.ActivityModule
import com.my.sapiaassignment.base.di.activity.DaggerActivityComponent
import com.my.sapiaassignment.base.network.DataResult
import com.my.sapiaassignment.datalayer.apimodel.PetItem
import com.my.sapiaassignment.uilayer.PetsMainActivity
import com.my.sapiaassignment.utility.GridSpacingItemDecoration
import com.my.sapiaassignment.utility.LiveDataInternetConnections
import com.my.sapiaassignment.utility.TimeUtility
import com.my.sapiaassignment.utility.TimeUtility.endTime
import com.my.sapiaassignment.utility.TimeUtility.startTime
import com.my.sapiaassignment.utility.TimeUtility.workingDays
import javax.inject.Inject

class PetsListFragment : Fragment() {
    @Inject
    lateinit var petsViewModel: PetsViewModel
    private lateinit var liveDataInternetConnections: LiveDataInternetConnections
    private lateinit var nasaGridImageAdapter: PetsListAdapter
    private var petsItemList: List<PetItem> = emptyList()
    private lateinit var gridRecyclerview: RecyclerView
    private lateinit var dataNotAvailableTxt: TextView
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_petslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val activityComponent = DaggerActivityComponent.builder()
            .myAppComponent((activity?.applicationContext as MyApplication).appComponent)
            .activityModule(ActivityModule(activity as PetsMainActivity))
            .build()
        activityComponent.inject(this)

        //view initialization
        gridRecyclerview = view.findViewById(R.id.grid_recyclerview)
        dataNotAvailableTxt = view.findViewById(R.id.dataNotAvailableTxt)
        progressBar = view.findViewById(R.id.progressBar)

        initAdapter()
    }

    private fun showPetsData() {
        if (isWorkingHoursAndDay()) {
            checkInternetConnection()
        } else {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val dialogFragment = DialogFragment()
        activity?.let { dialogFragment.show(it.supportFragmentManager, "My  Fragment") }
    }


    private fun initAdapter() {
        nasaGridImageAdapter = context?.let { PetsListAdapter(it) }!!

        gridRecyclerview.apply {
            val spanCount = 2 // 3 columns
            val spacing = 50 // 50px
            val includeEdge = true
            setHasFixedSize(true)
            this.addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount,
                    spacing,
                    includeEdge
                )
            )
            layoutManager = GridLayoutManager(context, 2)
            adapter = nasaGridImageAdapter

        }
    }

    override fun onResume() {
        super.onResume()
        showPetsData()
    }

    private fun checkInternetConnection() {

        val application: Application = activity?.applicationContext as Application
        liveDataInternetConnections = LiveDataInternetConnections(application)
        liveDataInternetConnections.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                if (petsItemList.isNotEmpty()) {
                    setDataToAdapter(petsItemList)
                    dataNotAvailableTxt.visibility = View.GONE
                } else
                    fetchAndObserveApiData()
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.internet_not_available_msg),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun fetchAndObserveApiData() {
        // fetch nasa image list
        petsViewModel.fetchPetsListData()
        petsViewModel.petsList.observe(viewLifecycleOwner) { it ->
            when (it) {
                is DataResult.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    dataNotAvailableTxt.text = getString(R.string.data_loading_msg)
                }
                is DataResult.Success -> {
                    progressBar.visibility = View.GONE
                    if (it.data?.isNotEmpty() == true) {
                        dataNotAvailableTxt.visibility = View.GONE
                        petsItemList = it.data
                        val sortedList =
                            petsItemList.sortedBy { it.title }
                        setDataToAdapter(sortedList)
                    } else
                        dataNotAvailableTxt.text = getString(R.string.data_not_available)
                }
                is DataResult.Error -> {
                    dataNotAvailableTxt.text =
                        it.exception?.message ?: getString(R.string.try_later_msg)

                }
            }

        }

    }


    private fun setDataToAdapter(sortedList: List<PetItem>) {
        gridRecyclerview.visibility = View.VISIBLE
        nasaGridImageAdapter.update(sortedList)
    }

    private fun isWorkingHoursAndDay(): Boolean {
        val currentTime = TimeUtility.getCurrentTime()
        val currentDay = TimeUtility.dayOfMonth()
        return (workingDays.contains(currentDay) && currentTime in startTime..endTime)
    }


}
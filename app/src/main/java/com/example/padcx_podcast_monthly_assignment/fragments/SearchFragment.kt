package com.example.padcx_podcast_monthly_assignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.adapter.PodcastCategoryAdapter
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.SearchCategoryPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls.SearchCategoryPresenterImpl
import com.example.padcx_podcast_monthly_assignment.mvp.view.SearchCategoryView
import com.example.shared.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SearchFragment : BaseFragment(),SearchCategoryView {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPodCastCategoryAdapter: PodcastCategoryAdapter
    private lateinit var mCategoryPresenter : SearchCategoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerView()


        mCategoryPresenter.onUIReady(this)
    }

    private fun setUpPresenter() {
        mCategoryPresenter = ViewModelProviders.of(this).get(SearchCategoryPresenterImpl::class.java)
        mCategoryPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView() {
        mPodCastCategoryAdapter = PodcastCategoryAdapter()
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_podCastCategory.layoutManager=linearLayoutManager
        rv_podCastCategory.adapter=mPodCastCategoryAdapter

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun displayPodCastCategoryLists(categoryLists: ArrayList<PodCastCategoryVO>) {
        Log.d("CategorySize",categoryLists.size.toString())
        mPodCastCategoryAdapter.setNewData(categoryLists.toMutableList())
    }

    override fun showErrorMessage(errorMessage: String) {

    }


    override fun getLifeCycleOwner(): LifecycleOwner =this


}
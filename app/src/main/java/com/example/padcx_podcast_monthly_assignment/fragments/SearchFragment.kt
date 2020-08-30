package com.example.padcx_podcast_monthly_assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.adapter.PodcastCategoryAdapter
import com.example.padcx_podcast_monthly_assignment.adapter.UpNextPodcastAdapter
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.CategoryPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls.CategoryPresenterImpl
import com.example.padcx_podcast_monthly_assignment.mvp.view.CategoryView
import com.example.shared.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_search.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SearchFragment : BaseFragment(),CategoryView {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPodCastCategoryAdapter: PodcastCategoryAdapter
    private lateinit var mCategoryPresenter : CategoryPresenter

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
    }

    private fun setUpPresenter() {
        mCategoryPresenter = ViewModelProviders.of(this).get(CategoryPresenterImpl::class.java)
        mCategoryPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView() {
        mPodCastCategoryAdapter = PodcastCategoryAdapter()
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_podCastCategory.layoutManager=linearLayoutManager
        rv_podCastCategory.adapter=mPodCastCategoryAdapter

        mPodCastCategoryAdapter.setNewData(mutableListOf(1,2,3,4))
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

    override fun showErrorMessage(errorMessage: String) {

    }

    override fun getLifeCycleOwner(): LifecycleOwner = this
}
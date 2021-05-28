package com.manuel28g.carsales.covidworlddata.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.manuel28g.carsales.covidworlddata.R
import com.manuel28g.carsales.covidworlddata.core.application.AppCovid
import com.manuel28g.carsales.covidworlddata.databinding.FragmentErrorDataBinding
import com.manuel28g.carsales.covidworlddata.viewmodel.CovidInfoViewModel
import javax.inject.Inject

class ErrorDataFragment: Fragment(), RetryActionButton{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: FragmentErrorDataBinding
    private val viewModel: CovidInfoViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_error_data, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.actionButton = this

        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
    }

    override fun onClickButton() {
        viewModel.resetError()
        Navigation.findNavController(mBinding.root).popBackStack()
    }

    private fun setupInjection(){
        (context?.applicationContext as AppCovid).getComponent().inject(this)
    }
}



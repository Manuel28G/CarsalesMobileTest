package com.manuel28g.carsales.covidworlddata.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.manuel28g.carsales.covidworlddata.R
import com.manuel28g.carsales.covidworlddata.core.application.AppCovid
import com.manuel28g.carsales.covidworlddata.core.application.appComponent
import com.manuel28g.carsales.covidworlddata.databinding.FragmentHomeBinding
import com.manuel28g.carsales.covidworlddata.viewmodel.CovidInfoViewModel
import java.util.*
import javax.inject.Inject

class HomeFragment: Fragment(), DatePickerDialog.OnDateSetListener{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mDatePickerDialog: DatePickerDialog

    private val viewModel: CovidInfoViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mDatePickerDialog = DatePickerDialog(requireContext(),
            this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1)

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mBinding.dataPicker = mDatePickerDialog
        mBinding.lifecycleOwner = viewLifecycleOwner

        mDatePickerDialog.datePicker.maxDate = viewModel.getMaxDate()
        mDatePickerDialog.datePicker.minDate = viewModel.getMinDate()
        mBinding.viewModel = viewModel

        viewModel.andErrorOccurs().observe(viewLifecycleOwner,  {
            if(it){
                viewModel.resetError()
                navigateToError()
            }
        })

        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
    }

    private fun callData(date:String?){
        if(date.isNullOrEmpty())
            viewModel.getActualDate()
        else
            viewModel.getData(date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        callData("${year}" +
                "-${(month+1).toString().padStart(2,'0')}" +
                "-${dayOfMonth.toString().padStart(2,'0')}")
    }

    private fun navigateToError(){
        findNavController().navigate(R.id.action_home_to_error)
    }

    private fun setupInjection(){
        appComponent().inject(this)
    }

}
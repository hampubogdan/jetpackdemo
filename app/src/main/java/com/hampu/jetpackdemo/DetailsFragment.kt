package com.hampu.jetpackdemo

/**
 * Created by Hampu Bogdan on 6/17/2018.
 */
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailsArgs = DetailsFragmentArgs.fromBundle(arguments)
        val itemId = detailsArgs.itemId
        itemText.text = "Selected Item: $itemId"
    }
}
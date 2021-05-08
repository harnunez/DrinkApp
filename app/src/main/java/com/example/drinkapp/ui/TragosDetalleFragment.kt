package com.example.drinkapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.drinkapp.R
import com.example.drinkapp.data.model.Drink
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

class TragosDetalleFragment : Fragment() {

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tragos_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drink.let {
            Glide.with(requireContext()).load(drink.imagen).into(img_trago_detalle)
            trago_title_detalle.text = drink.nombre
            trago_desc_detalle.text = drink.descripcion
        }
    }

}
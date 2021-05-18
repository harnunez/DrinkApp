package com.example.drinkapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.drinkapp.AppDatabase
import com.example.drinkapp.R
import com.example.drinkapp.data.DataSource
import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.data.model.DrinkEntity
import com.example.drinkapp.domain.RepoImpl
import com.example.drinkapp.ui.viewmodel.MainViewModel
import com.example.drinkapp.ui.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

class TragosDetalleFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>{
        VMFactory(RepoImpl(DataSource(AppDatabase.getDataBase(requireActivity().applicationContext)))) }

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
            if(drink.hasAlcohol=="Non_Alcoholic"){

            }else{

            }
        }

        btn_guardar_trago.setOnClickListener {
            viewModel.guardarTrago(DrinkEntity(drink.cocktailId,drink.nombre,drink.descripcion,drink.hasAlcohol))
            Toast.makeText(requireContext(),"Se guardo el trago como favorito", Toast.LENGTH_SHORT)
                .show()
        }
    }

}
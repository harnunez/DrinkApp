package com.example.drinkapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkapp.AppDatabase
import com.example.drinkapp.R
import com.example.drinkapp.data.DataSourceImpl
import com.example.drinkapp.data.model.Drink
import com.example.drinkapp.domain.RepoImpl
import com.example.drinkapp.ui.viewmodel.MainViewModel
import com.example.drinkapp.ui.viewmodel.VMFactory
import com.example.drinkapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_favoritos.*


class FavoritosFragment : Fragment(), MainAdapter.OnTragoClickListener {

    private val viewModel by viewModels<MainViewModel>{
        VMFactory(RepoImpl(DataSourceImpl(AppDatabase.getDataBase(requireActivity().applicationContext)))) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupRecyclerView(){
        rv_trago_favoritos.layoutManager = LinearLayoutManager(requireContext())
        rv_trago_favoritos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))

    }

    private fun setupObserver(){
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading ->{

                }
                is Resource.Succes ->{
                    val listaDrink  = result.data.map {
                        Drink(it.tragoId,it.imagen,it.nombre,it.descripcion,it.hasAlcohol)
                    }

                    rv_trago_favoritos.adapter = MainAdapter(requireContext(),listaDrink,this)
                }
                is Resource.Failure ->{

                }
            }
        })
    }

    override fun onTragoClick(drink: Drink) {
        //Implementar delete al tocar el trago en favoritos
    }
}
package com.example.drinkapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(),MainAdapter.OnTragoClickListener {

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()

        setupObservers()
        irAFavoritos()

    }

    private fun irAFavoritos() {
        btn_favoritos.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
        }
    }

    private fun setupSearchView(){
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTrago(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    //Recycler Config
    private fun setupRecyclerView(){
        rv_tragos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    private fun setupObservers(){
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading ->{
                    layout_progress.visibility = View.VISIBLE
                }

                is Resource.Succes -> {
                    layout_progress.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(),result.data,this)
                }

                is Resource.Failure -> {
                    layout_progress.visibility = View.GONE
                    Toast.makeText(requireContext(),"Ocurri?? un error al traer los datos ${result.exception}",Toast.LENGTH_LONG)
                }
            }
        })
    }

    override fun onTragoClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.action_mainFragment_to_tragosDetalleFragment,bundle)
    }


}
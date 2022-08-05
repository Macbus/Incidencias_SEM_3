package com.example.incidencias_sem.ui.query

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.database.AppDatabase
import com.example.incidencias_sem.databinding.FragmentQueryBinding
import com.example.incidencias_sem.ui.viewModels.QueryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.util.stream.Collectors

class QueryFragment : Fragment() {

    private var _binding: FragmentQueryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QueryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getIncidence().observe(viewLifecycleOwner) { conditions ->
            val items =
                conditions.stream().map { condition -> condition.id_incidence }
                    .collect(Collectors.toList())
            val adapter =
                ArrayAdapter(requireContext(), R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.fragmentSpSearch.adapter = adapter
        }
        binding.fragmentSpSearch.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    with(viewModel) {
                        indEstado(binding.fragmentSpSearch.selectedItem as String)
                            .observe(viewLifecycleOwner) {
                                binding.tvIncidence.text ="Nº Incidencia: ${it.incidence.id_incidence}"
                                binding.tvIssi.text ="ISSI: ${it.incidence.Issi_id}"
                                binding.tvIndicativo.text ="Indicativo: ${it.incidence.call_sign}"
                                binding.tvLocation.text ="Centro: ${it.incidence.location}"
                                binding.tvDeviceType.text = "Equipo: ${it.equipo.name}"
                                binding.tvBreakdown.text = "Avería: ${it.breakdown.name}"
                                binding.tvCondition.text ="Estado: ${ it.condition.name}"
                            }
                    }
                    Toast.makeText(requireContext(), "Datos correctos", Toast.LENGTH_SHORT)
                        .show()

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

    }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

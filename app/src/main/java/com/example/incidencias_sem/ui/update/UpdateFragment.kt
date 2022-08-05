package com.example.incidencias_sem.ui.update

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.incidencias_sem.databinding.FragmentUpdateBinding
import com.example.incidencias_sem.ui.viewModels.UpdateViewModel
import java.util.stream.Collectors


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UpdateViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
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
            binding.fragmentSpIncidence.adapter = adapter
        }
        viewModel.getCondition().observe(viewLifecycleOwner) {
            val items = it.stream().map { breakdown -> breakdown.name }.collect(Collectors.toList())

            val adapter =
                ArrayAdapter(requireContext(), R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.fragmentSpCondition.adapter = adapter
        }

        binding.fragmentSpIncidence.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                with(viewModel){
                    this.indEstado(binding.fragmentSpIncidence.selectedItem as String).observe(viewLifecycleOwner){
                       binding.fragmentSpCondition.setSelection((it-1))
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        binding.fragmentUpdateBtnActualizar.setOnClickListener {

            val estado = (binding.fragmentSpCondition.selectedItemPosition + 1 ).toString()
            val incidencia =binding.fragmentSpIncidence.selectedItem as String


            with(viewModel) {
                updateConditions(incidencia,estado)
               }
            Toast.makeText(requireContext(), "Estado actualizado correctamente", Toast.LENGTH_SHORT)
                .show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
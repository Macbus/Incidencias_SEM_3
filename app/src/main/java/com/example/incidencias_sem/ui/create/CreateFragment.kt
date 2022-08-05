package com.example.incidencias_sem.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.incidencias_sem.R
import com.example.incidencias_sem.database.entities.Incidence
import com.example.incidencias_sem.databinding.FragmentCreateBinding
import com.example.incidencias_sem.ui.viewModels.CreateViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.stream.Collectors


class CreateFragment : Fragment() {


    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getConditions().observe(viewLifecycleOwner) { conditions ->
            val items =
                conditions.stream().map { condition -> condition.name }.collect(Collectors.toList())

            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerEstado.adapter = adapter
        }

        viewModel.getDevices().observe(viewLifecycleOwner) {
            val items = it.stream().map { dev -> dev.name }.collect(Collectors.toList())

            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerEquipo.adapter = adapter
        }
        viewModel.getbreakdowns().observe(viewLifecycleOwner) {
            val items = it.stream().map { breakdown -> breakdown.name }.collect(Collectors.toList())

            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerAveria.adapter = adapter
        }

        binding.fragmentTieIncidence.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.fragmentTilIncidence.error = ""
            }
        }
        binding.fragmentTieISSI.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.fragmentTilISSI.error = ""
            }
        }
        binding.fragmentTieLocation.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.fragmentTilLocation.error = ""
        }
        binding.fragmentTieIncidence.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.fragmentTilIncidence.error = ""
            }
        }
        binding.fragmentTieISSI.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.fragmentTilISSI.error = ""
            }
        }
        binding.fragmentTieLocation.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.fragmentTilLocation.error = ""
            }
        }
        binding.fragmentTieLocation.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.fragmentCreateBtnCrear.performClick()
                true
            }
            false

        }
        binding.fragmentCreateBtnCrear.setOnClickListener {


            val attr = listOf(
                binding.fragmentTieIncidence,
                binding.fragmentTieISSI,
                binding.fragmentTieISSI
            )
            var error = false
            attr.forEach {
                if (it.obtenerTexto().isBlank()) {
                    error = true
                    when (it.id) {
                        R.id.fragment_tie_incidence -> {
                            binding.fragmentTilIncidence.error =
                                "Debes introducir una Nº de Incidencia"
                        }
                        R.id.fragment_tie_ISSI -> {
                            binding.fragmentTilISSI.error =
                                "Debes introducir un Issi"
                        }
                        R.id.fragment_tie_location -> {
                            binding.fragmentTilLocation.error = "Debes intruducir una localización"
                        }

                    }
                }
                if (error)
                    return@setOnClickListener
            }
            if (binding.fragmentTieISSI.obtenerTexto().length < 7) {
                binding.fragmentTilISSI.error = "Debes introducir un Issi válido"
                return@setOnClickListener
            }
            val incidencia = binding.fragmentTieIncidence
            val issi = binding.fragmentTieISSI
            val indicativo = setCallSign(binding.fragmentTieISSI.obtenerTexto())
            val centro = binding.fragmentTieLocation
            val equipo = binding.spinnerEquipo
            val averia = binding.spinnerAveria
            val estado = binding.spinnerEstado

            val createModel: CreateViewModel by viewModels()
            with(createModel) {
                insertIncidence(
                    Incidence(
                        incidencia.obtenerTexto(),
                        issi.obtenerTexto(),
                        indicativo,
                        centro.obtenerTexto(),
                        (equipo.selectedItemPosition + 1).toString(),
                        (averia.selectedItemPosition + 1).toString(),
                        (estado.selectedItemPosition + 1).toString()
                    )
                )
            }
            Toast.makeText(requireContext(), "Incidencia creada correctamente", Toast.LENGTH_SHORT)
                .show()
            clearTextInput()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    fun TextInputEditText.obtenerTexto(): String {
        return text.toString()
    }

    fun setCallSign(call: String): String {

        val callSign = call.substring(2, 6)
        return callSign
    }

    fun clearTextInput() {

        binding.fragmentTieIncidence.text?.clear()
        binding.fragmentTieISSI.text?.clear()
        binding.fragmentTieLocation.text?.clear()

    }


}





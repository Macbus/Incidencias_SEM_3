package com.example.incidencias_sem.ui.signin


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.incidencias_sem.R
import com.example.incidencias_sem.database.entities.Users
import com.example.incidencias_sem.databinding.FragmentRegisterBinding
import com.example.incidencias_sem.ui.viewModels.UsersViewModels
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.registerTieName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.registerTilName.error = ""
            }
        }
        binding.registerTieSurname.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.registerTilSurname.error = ""
            }
        }
        binding.registerTieEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.registerTilEmail.error = ""
        }
        binding.registerTiePassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.registerTilPassword.error = ""
        }
        binding.registerTiePassword2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.registerTilPassword2.error = ""
        }
        binding.registerTieName.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.registerTilName.error = ""
            }
        }
        binding.registerTieSurname.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.registerTilSurname.error = ""
            }
        }
        binding.registerTieEmail.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.registerTilEmail.error = ""
            }
        }
        binding.registerTiePassword.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.registerTilPassword.error = ""
            }
        }
        binding.registerTilPassword2.setOnClickListener {
            val text = it as TextInputEditText
            if (text.text!!.isNotEmpty()) {
                binding.registerTilPassword2.error = ""
            }
        }
        binding.registerTiePassword2.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.registerBtnRegistro.performClick()
                true
            }
            false

        }
        binding.registerBtnRegistro.setOnClickListener {

            val name = binding.registerTieName
            val surname = binding.registerTieSurname
            val email = binding.registerTieEmail
            val pass1 = binding.registerTiePassword
            val pass2 = binding.registerTiePassword2

            val attr = listOf(name, surname, email, pass1, pass2)
            var error = false
            attr.forEach {
                if (it.obtenerTexto().isBlank()) {
                    error = true
                    when (it.id) {
                        R.id.register_Tie_Name -> {
                            binding.registerTilName.error =
                                "Debes de introducir tu nombre completo"
                        }
                        R.id.register_tie_surname -> {
                            binding.registerTilSurname.error =
                                "Debes de introducir tu apellido completo"
                        }
                        R.id.register_tie_email -> {
                            binding.registerTilEmail.error = "Debes de introducir tu email"
                        }
                        R.id.register_tie_password -> {
                            binding.registerTilPassword.error =
                                "Debes de introducir una contraseña"
                        }
                        R.id.register_tie_password2 -> {
                            binding.registerTilPassword2.error =
                                "Debes de introducir nuevamente la contraseña"

                        }
                    }
                }
            }
            if (error)
                return@setOnClickListener
            if (!email.obtenerTexto().trim { it <= ' ' }.matches(emailRegex.toRegex())) {
                Toast.makeText(requireContext(), "Invalid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass1.obtenerTexto().length < 5) {
                binding.registerTilPassword.error =
                    "La contraseña debe de tener como mínimo 5 caracteres"
                return@setOnClickListener
            }
            if (pass1.obtenerTexto() != pass2.obtenerTexto()) {
                binding.registerTilPassword.error = "Las contraseñas no coinciden"
                binding.registerTilPassword2.error = "Las contraseñas no coinciden"
                return@setOnClickListener
            }

            val userModel: UsersViewModels by viewModels()
            userModel.save(
                Users(
                    name.obtenerTexto(),
                    surname.obtenerTexto(),
                    email.obtenerTexto(),
                    pass1.obtenerTexto()
                )
            )
            Toast.makeText(requireContext(), "El usuario ha sido registrado", Toast.LENGTH_SHORT)
                .show()

            val actions = RegisterFragmentDirections.toLoginFragment(setData())
            NavHostFragment.findNavController(this).navigate(actions)
        }

        binding.registerBtnCancelar.setOnClickListener {
            val actions = RegisterFragmentDirections.toLoginFragment()
            NavHostFragment.findNavController(this).navigate(actions)

        }
        return view
    }

    fun TextInputEditText.obtenerTexto(): String {
        return text.toString()
    }

    fun setData() = arrayOf(
        binding.registerTieEmail.text.toString(),
        binding.registerTiePassword.text.toString()
    )


}














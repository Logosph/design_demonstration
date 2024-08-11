package ru.logosph.design_demonstration.presenter.fragments.main

import android.animation.Animator.AnimatorListener
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.logosph.design_demonstration.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        // viewModel = MainViewModel()  -  НЕ СМЕЙ
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.blockingView.setOnClickListener {  }

        binding.button.setOnClickListener {

            lifecycleScope.launch {
                viewModel.register(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                ).collect { value ->
                    binding.apply {
                        when (value) {

                            State.LOADING ->  {
                                blockingView.visibility = View.VISIBLE
                                progressBar.visibility = View.VISIBLE
                            }

                            State.SUCCESS -> {

                                Snackbar.make(binding.root, "Registered!", Snackbar.LENGTH_SHORT).show()

                                progressBar.setVisibilityAfterHide(View.GONE)
                                progressBar.hide()

                                val anim = ObjectAnimator.ofFloat(
                                    blockingView,
                                    "alpha",
                                    0.5f,
                                    0f
                                )

                                anim.duration = 500
                                anim.start()

                                withContext(Dispatchers.IO) {
                                    Thread.sleep(500)
                                }

                                blockingView.visibility = View.GONE


                            }

                            State.ERROR -> {

                                Snackbar.make(binding.root, "ERROR!", Snackbar.LENGTH_SHORT).show()

                                progressBar.setVisibilityAfterHide(View.GONE)
                                progressBar.hide()

                                val anim = ObjectAnimator.ofFloat(
                                    blockingView,
                                    "alpha",
                                    0.5f,
                                    0f
                                )

                                anim.duration = 500
                                anim.start()

                                withContext(Dispatchers.IO) {
                                    Thread.sleep(500)
                                }

                                blockingView.visibility = View.GONE
                            }

                        }
                    }
                }
            }

        }

        return binding.root
    }

}
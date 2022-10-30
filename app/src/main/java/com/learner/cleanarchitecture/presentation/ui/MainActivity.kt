package com.learner.cleanarchitecture.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.learner.cleanarchitecture.databinding.ActivityMainBinding
import com.learner.cleanarchitecture.databinding.ItemCoinBinding
import com.learner.cleanarchitecture.domain.model.Coin
import com.learner.cleanarchitecture.presentation.ui.bs.CoinDetailsDialog
import com.learner.cleanarchitecture.presentation.view_model.MainViewModel
import com.learner.cleanarchitecture.utils.MyGlobalAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private var coinList = listOf<Coin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coinAdapter =
            object : MyGlobalAdapter<ItemCoinBinding>(ItemCoinBinding::inflate, coinList.size) {
                override fun bind(binder: ItemCoinBinding, position: Int) {
                    coinList[position].apply {
                        binder.txtCoinName.text = "$rank. $name ($symbol)"
                        binder.txtActive.text = if (isActive) "active" else "inactive"

                        binder.root.setOnClickListener {
                            viewModel.getCoinDetailById(id)
                            showToast("Loading...")
                        }
                    }
                }
            }
        binding.rvCoinList.adapter = coinAdapter

        viewModel.coinsState.observe(this) {
            if (it.isLoading) binding.txtLoading.text = "Loading..."
            else if (it.error != null) binding.txtLoading.text = it.error
            else if (it.coin.isNotEmpty()) {
                coinList = it.coin
                coinAdapter.updateSize(coinList.size)
                binding.txtLoading.visibility = View.INVISIBLE
            }
        }
        viewModel.getCoins()


        viewModel.coinDetailState.observe(this) {
            if (it.isLoading) showToast("Please wait...")
            else if (it.error != null) showToast(it.error)
            else if (it.coinDetail != null) {
                CoinDetailsDialog(it.coinDetail).show(supportFragmentManager, null)
                showToast("${it.coinDetail.name} Coin loaded")
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun logd(msg: String) {
        Log.d("xyz", msg)
    }
}
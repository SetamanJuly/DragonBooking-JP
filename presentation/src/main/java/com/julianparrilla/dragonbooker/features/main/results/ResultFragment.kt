package com.julianparrilla.dragonbooker.features.main.results

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.julianparrilla.dragonbooker.R
import com.julianparrilla.dragonbooker.base.BaseFragment
import com.julianparrilla.dragonbooker.common.ViewStore
import com.julianparrilla.dragonbooker.databinding.FragmentResultBinding
import com.julianparrilla.dragonbooker.features.main.results.adapter.DragonItemAdapter
import com.julianparrilla.dragonbooker.utils.onClick
import com.julianparrilla.dragonbooker.utils.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
class ResultFragment : BaseFragment(), ViewStore<ResultState> {

    private val binding: FragmentResultBinding by viewBinding()

    private val store: ResultStore by inject()

    override fun getLayout(): Int = R.layout.fragment_result

    override fun onCreate() {
        store.onCreate(this)
        store.retrievePrevData(arguments)
        binding.rvDragons.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        store.cancel()
    }

    override fun ResultState.render() {
        data?.let {
            binding.tvTotal.text = resources.getString(R.string.total_items, it.results.size)
            binding.rvDragons.adapter = DragonItemAdapter(it.results, currencies!!)
        }

        binding.btnBack onClick {
            requireActivity().onBackPressed()
        }
    }
}

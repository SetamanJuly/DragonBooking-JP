package com.julianparrilla.dragonbooker.features.main.home

import android.widget.ArrayAdapter
import com.julianparrilla.dragonbooker.R
import com.julianparrilla.dragonbooker.base.BaseFragment
import com.julianparrilla.dragonbooker.common.ViewStore
import com.julianparrilla.dragonbooker.databinding.FragmentHomeBinding
import com.julianparrilla.dragonbooker.utils.onClick
import com.julianparrilla.dragonbooker.utils.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject


@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
class HomeFragment : BaseFragment(), ViewStore<HomeState> {

    private val binding: FragmentHomeBinding by viewBinding()

    private val store: HomeStore by inject()

    override fun getLayout(): Int = R.layout.fragment_home

    override fun onCreate() {
        store.onCreate(this)

        binding.cbOriginAnywhere.setOnCheckedChangeListener { _, b ->
            binding.actvOrigin.isEnabled = !b
            if (b) binding.actvOrigin.setText("")
        }

        binding.cbDestinationAnywhere.setOnCheckedChangeListener { _, b ->
            binding.actvDestination.isEnabled = !b
            if (b) binding.actvDestination.setText("")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        store.cancel()
    }

    override fun HomeState.render() {

        originDestination?.let {
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it.first).also {
                binding.actvOrigin.setAdapter(it)
                binding.actvOrigin.setOnItemClickListener { list, _, i, _ ->
                    onOriginChanged(list.getItemAtPosition(i) as String)
                }
            }

            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it.second).also {
                binding.actvDestination.setAdapter(it)
                binding.actvDestination.setOnItemClickListener { list, _, i, _ ->
                    onDestinationChanged(list.getItemAtPosition(i) as String)
                }
            }
        }

        binding.btnSearch onClick {
            onSearchClicked()
        }

    }
}
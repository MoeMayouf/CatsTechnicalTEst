package com.mayouf.catstechnicaltest.presentation.cats

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mayouf.catstechnicaltest.databinding.FragmentCatsBinding
import com.mayouf.catstechnicaltest.domain.model.Cats
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatsFragment : Fragment() {
    private var fragmentCatsBindings: FragmentCatsBinding? = null
    private var adapter: CatsAdapter? = null
    private val viewModel: CatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadCats()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentCatsBindings = FragmentCatsBinding.inflate(inflater, container, false)
        fragmentCatsBindings!!.catsRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.loadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            fragmentCatsBindings!!.progressBar.visibility =
                if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.catsLiveData.observe(viewLifecycleOwner, {
            initRecyclerView(it)
        })
        return fragmentCatsBindings!!.root

    }

    private fun initRecyclerView(cats: List<Cats>) {
        Log.i("AlbumsFragment", cats.toString())
        adapter = CatsAdapter(cats as MutableList<Cats>)
        fragmentCatsBindings!!.catsRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        fragmentCatsBindings = null
        super.onDestroy()
    }

    companion object {

        val FRAGMENT_NAME: String = CatsFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            CatsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
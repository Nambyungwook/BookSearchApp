package com.nbw.booksearchapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nbw.booksearchapp.databinding.FragmentFavoriteBinding
import com.nbw.booksearchapp.ui.adapter.BookSearchPagingAdapter
import com.nbw.booksearchapp.ui.viewmodel.FavoriteViewModel
import com.nbw.booksearchapp.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    //hilt를 사용해서 viewmodel 주입
//    private lateinit var bookSearchViewModel: BookSearchViewModel
//    private val bookSearchViewModel by activityViewModels<BookSearchViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    // PagingAdapter로 변경
//    private lateinit var bookSearchAdapter: BookSearchAdapter
    private lateinit var bookSearchAdapter: BookSearchPagingAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        setupRecyclerView()
        setupTouchHelper(view)

        // Livedata observe 부분 삭제
//        bookSearchViewModel.favoriteBooks.observe(viewLifecycleOwner) {
//            bookSearchAdapter.submitList(it)
//        }

        // flow로 변경
//        lifecycleScope.launch {
//            bookSearchViewModel.favoriteBooks.collectLatest {
//                bookSearchAdapter.submitList(it)
//            }
//        }

        // StateFlow 구독으로 변경
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                bookSearchViewModel.favoriteBooks.collectLatest {
//                    bookSearchAdapter.submitList(it)
//                }
//            }
//        }
        // StateFlow를 구독하기 위한 boilerplate 코드가 길어짐 이를 극복하기 위해 확장 함수를 만들어서 사용할 수 있음
//        collectLatestStateFlow(bookSearchViewModel.favoriteBooks) {
//            bookSearchAdapter.submitList(it)
//        }

        // favoritePagingBooks를 구독하게 변경
        // 페이징데이터는 시간에 따라 변화하는 특성을 가지기 때문에 반드시 collect가 아닌 collectLatest로 값을 구독해서 항상 기존에 Paging값을 캔슬하고 새 값을 구독
        collectLatestStateFlow(favoriteViewModel.favoritePagingBooks) {
            bookSearchAdapter.submitData(it)
        }
    }

    private fun setupRecyclerView() {
        // PagingAdapter로 변경
//        bookSearchAdapter = BookSearchAdapter()
        bookSearchAdapter = BookSearchPagingAdapter()
        binding.rvFavoriteBooks.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = bookSearchAdapter
        }
        bookSearchAdapter.setOnItemClickListener {
            val action = FavoriteFragmentDirections.actionFragmentFavoriteToFragmentBook(it)
            findNavController().navigate(action)
        }
    }

    private fun setupTouchHelper(view: View) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
//                val book = bookSearchAdapter.currentList[position]
//                bookSearchViewModel.deleteBook(book)
//                Snackbar.make(view, "Book has deleted", Snackbar.LENGTH_SHORT).apply {
//                    setAction("Undo") {
//                        bookSearchViewModel.saveBook(book)
//                    }
//                }.show()
                // Paging 응답은 null을 가질 수 있기 때문에 수정
                val pagedBook = bookSearchAdapter.peek(position)
                pagedBook?.let { book ->
                    favoriteViewModel.deleteBook(book)
                    Snackbar.make(view, "Book has deleted", Snackbar.LENGTH_SHORT).apply {
                        setAction("Undo") {
                            favoriteViewModel.saveBook(book)
                        }
                    }.show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvFavoriteBooks)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

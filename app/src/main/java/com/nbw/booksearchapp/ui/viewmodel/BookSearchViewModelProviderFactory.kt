package com.nbw.booksearchapp.ui.viewmodel

//@Suppress("UNCHECKED_CAST")
//class BookSearchViewModelProviderFactory(
//    private val booksSearchRepository: BookSearchRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
//            return BookSearchViewModel(booksSearchRepository) as T
//        }
//        throw IllegalArgumentException("ViewModel class not found")
//    }
//}

// viewmodel이 hilt를 사용하여 delegate에 의해 생성되기 때문에 사용하지 않음
//class BookSearchViewModelProviderFactory(
//    private val bookSearchRepository: BookSearchRepository,
//    private val workManager: WorkManager,
//    owner: SavedStateRegistryOwner,
//    defaultArgs: Bundle? = null
//) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
//    override fun <T : ViewModel> create(
//        key: String,
//        modelClass: Class<T>,
//        handle: SavedStateHandle
//    ): T {
//        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
//            return BookSearchViewModel(bookSearchRepository, workManager, handle) as T
//        }
//        throw IllegalArgumentException("ViewModel class not found")
//    }
//
//}
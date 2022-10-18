package com.nbw.booksearchapp.ui.viewmodel

//import androidx.lifecycle.*
//import androidx.paging.PagingData
//import androidx.paging.cachedIn
//import androidx.work.*
//import com.nbw.booksearchapp.data.model.Book
//import com.nbw.booksearchapp.data.model.SearchResponse
//import com.nbw.booksearchapp.data.repository.BookSearchRepository
//import com.nbw.booksearchapp.worker.CacheDeleteWorker
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.util.concurrent.TimeUnit
//import javax.inject.Inject
//
//@HiltViewModel
//class BookSearchViewModel @Inject constructor(
//    private val bookSearchRepository: BookSearchRepository,
//    private val workManager: WorkManager,
//    private val savedSateHandle: SavedStateHandle // 모듈의 설정이 없어도 자동으로 주입됨
//) : ViewModel() {
//
//    // API
//    private val _searchResult = MutableLiveData<SearchResponse>()
//    val searchResult: LiveData<SearchResponse> get() = _searchResult
//
//    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
//        val response = bookSearchRepository.searchBooks(query, getSortMode(), 1, 15)
//        if (response.isSuccessful) {
//            response.body()?.let { body ->
//                _searchResult.postValue(body)
//            }
//        }
//    }
//
//    // Room
//    fun saveBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
//        bookSearchRepository.insertBooks(book)
//    }
//
//    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
//        bookSearchRepository.deleteBooks(book)
//    }
//
//
//    //    val favoriteBooks: Flow<List<Book>> = bookSearchRepository.getFavoriteBooks()
//    // Flow -> StateFlow로 변경하여 FavoriteFragment의 라이프사이클과 동기화 시킴
//    val favoriteBooks: StateFlow<List<Book>> = bookSearchRepository.getFavoriteBooks()
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())
//
//    // SaveState
//    var query = String()
//        set(value) {
//            field = value
//            savedSateHandle.set(SAVE_STATE_KEY, value)
//        }
//
//    init {
//        query = savedSateHandle.get<String>(SAVE_STATE_KEY) ?: ""
//    }
//
//    companion object {
//        private const val SAVE_STATE_KEY = "query"
//        private val WORKER_KEY = "cache_worker"
//    }
//
//    // DataStore
//    fun saveSortMode(value: String) = viewModelScope.launch(Dispatchers.IO) {
//        bookSearchRepository.saveSortMode(value)
//    }
//
//    suspend fun getSortMode() = withContext(Dispatchers.IO) {
//        bookSearchRepository.getSortMode().first()
//    }
//
//    fun saveCacheDeleteMode(value: Boolean) = viewModelScope.launch(Dispatchers.IO) {
//        bookSearchRepository.saveCacheDeleteMode(value)
//    }
//
//    suspend fun getCacheDeleteMode() = withContext(Dispatchers.IO) {
//        bookSearchRepository.getCacheDeleteMode().first()
//    }
//
//    // Paging
//    val favoritePagingBooks: StateFlow<PagingData<Book>> =
//        bookSearchRepository.getFavoritePagingBooks()
//            .cachedIn(viewModelScope) // 코루틴이 데이터 스트림을 캐시하고 공유가능하게 함
//            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())
//
//    private val _searchPagingResult = MutableStateFlow<PagingData<Book>>(PagingData.empty())
//    val searchPagingResult: StateFlow<PagingData<Book>> = _searchPagingResult.asStateFlow()
//
//    fun searchBooksPaging(query: String) {
//        viewModelScope.launch {
//            bookSearchRepository.searchBooksPaging(query, getSortMode())
//                .cachedIn(viewModelScope)
//                .collect() {
//                    _searchPagingResult.value = it
//                }
//        }
//    }
//
//    // WorkManager
//    fun setWork() {
//        val constraints = Constraints.Builder()
//            .setRequiresCharging(true)
//            .setRequiresBatteryNotLow(true)
//            .build()
//
//        val workRequest = PeriodicWorkRequestBuilder<CacheDeleteWorker>(15, TimeUnit.MINUTES)
//            .setConstraints(constraints)
//            .build()
//
//        workManager.enqueueUniquePeriodicWork(
//            WORKER_KEY, ExistingPeriodicWorkPolicy.REPLACE, workRequest
//        )
//    }
//
//    fun deleteWork() = workManager.cancelUniqueWork(WORKER_KEY)
//
//    fun getWorkStatus(): LiveData<MutableList<WorkInfo>> =
//        workManager.getWorkInfosForUniqueWorkLiveData(WORKER_KEY)
//}
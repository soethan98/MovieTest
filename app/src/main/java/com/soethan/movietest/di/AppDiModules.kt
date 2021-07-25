import com.soethan.movietest.data.di.DATA_MAPPER_MODULE
import com.soethan.movietest.data.di.DATA_MODULE
import com.soethan.movietest.data.di.DB_MODULE
import com.soethan.movietest.data.di.NET_MODULE
import com.soethan.movietest.di.CONTEXT_MODULE
import com.soethan.movietest.di.VIEW_MODEL_MODULE
import com.soethan.movietest.domain.di.USECASE_MODULE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
val APP_DI_MODULES = listOf(
    NET_MODULE,
    VIEW_MODEL_MODULE,
    CONTEXT_MODULE,
    DATA_MAPPER_MODULE,
    DATA_MODULE,
    DB_MODULE,
//    FRAGMENT_MODULE,
    USECASE_MODULE

)
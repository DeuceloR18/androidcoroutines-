import com.example.coroutineskt.userinfo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // La función es 'suspend'
    @GET("nombre/{id}")
    suspend fun obtenerUsuario(@Path("id") id: Int): userinfo.Usuario

}
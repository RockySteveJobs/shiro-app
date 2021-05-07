import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.exoplayer2.device.DeviceInfo
import com.google.common.truth.Truth.assertThat
import com.lagradost.shiro.ui.MainActivity
import com.lagradost.shiro.utils.ShiroApi
import com.lagradost.shiro.utils.ShiroApi.Companion.getAnimePage
import com.lagradost.shiro.utils.ShiroApi.Companion.getHome
import com.lagradost.shiro.utils.ShiroApi.Companion.getHomeOnly
import com.lagradost.shiro.utils.ShiroApi.Companion.getRandomAnimePage
import com.lagradost.shiro.utils.ShiroApi.Companion.getToken
import com.lagradost.shiro.utils.ShiroApi.Companion.getVideoLink
import com.lagradost.shiro.utils.ShiroApi.Companion.quickSearch
import com.lagradost.shiro.utils.ShiroApi.Companion.search
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


@RunWith(JUnitPlatform::class)
object ApiTest : Spek({
    var token: ShiroApi.Token? = null
    var animePage: ShiroApi.AnimePage? = null

    val title = "Attack on titan"
    val slug = "shingeki-no-kyojin"

    group("Api test") {
        describe("Api Test") {
            it("Token test") {
                token = getToken()
                assertThat(token).isNotNull()
            }
            it("Home test") {
                val home = getHomeOnly(token)
                assertThat(home).isNotNull()
            }
            it("Seach test") {
                val search = search(title, token)
                assertThat(search).isNotNull()
            }
            it("Quick Search test") {
                val quickSearch = quickSearch(title, token)
                assertThat(quickSearch).isNotNull()
            }
            it("Animepage test") {
                animePage = getAnimePage(slug, token)
                assertThat(animePage).isNotNull()
                assertThat(animePage?.data?.slug).isEqualTo(slug)
            }
            it("Video link test") {
                val episodeLink = animePage?.data?.episodes?.get(0)?.videos?.get(0)?.let { getVideoLink(it.video_id) }
                assertThat(episodeLink).isNotNull()
            }
            it("Random page test") {
                animePage = getRandomAnimePage(token)
                assertThat(animePage).isNotNull()
            }
        }
    }
})

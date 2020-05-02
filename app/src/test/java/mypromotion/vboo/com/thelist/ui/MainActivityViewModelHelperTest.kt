package mypromotion.vboo.com.thelist.ui

import android.content.Context
import android.content.res.Resources
import android.view.View
import mypromotion.vboo.com.thelist.R
import mypromotion.vboo.com.thelist.db.entity.Album
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainActivityViewModelHelperTest {

    @Mock
    lateinit var context: Context

    @Mock
    var resources: Resources? = null

    lateinit var mainActivityViewModelHelper: MainActivityViewModelHelper


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mainActivityViewModelHelper = MainActivityViewModelHelper(this.context, listOf(), false, true)
    }

    @Test
    fun getProgressBarVisibilityTest_network_available() {
        Assert.assertEquals(View.VISIBLE, mainActivityViewModelHelper.getProgressBarVisibility())

        mainActivityViewModelHelper.isError = true
        Assert.assertEquals(View.GONE, mainActivityViewModelHelper.getProgressBarVisibility())

        mainActivityViewModelHelper.isError = false
        mainActivityViewModelHelper.listAlbum = listOf(Album(1, 1, "title", "url", "url"))

        Assert.assertEquals(View.GONE, mainActivityViewModelHelper.getProgressBarVisibility())
    }

    @Test
    fun getProgressBarVisibilityTest_network_not_available() {
        mainActivityViewModelHelper.isConnectedToNetwork = false
        Assert.assertEquals(View.GONE, mainActivityViewModelHelper.getProgressBarVisibility())

        mainActivityViewModelHelper.listAlbum = listOf(Album(1, 1, "title", "url", "url"))
        Assert.assertEquals(View.GONE, mainActivityViewModelHelper.getProgressBarVisibility())

        mainActivityViewModelHelper.isError = true
        Assert.assertEquals(View.GONE, mainActivityViewModelHelper.getProgressBarVisibility())

        mainActivityViewModelHelper.isError = false
    }

    @Test
    fun getMessageValue_visible() {
        mainActivityViewModelHelper.isError = true
        Assert.assertEquals(View.VISIBLE, mainActivityViewModelHelper.getMessageVisibility())

        mainActivityViewModelHelper.isError = false
        mainActivityViewModelHelper.isConnectedToNetwork = false
        Assert.assertEquals(View.VISIBLE, mainActivityViewModelHelper.getMessageVisibility())

    }

    @Test
    fun getMessageValue_not_visible() {
        Assert.assertEquals(View.GONE, mainActivityViewModelHelper.getMessageVisibility())

        mainActivityViewModelHelper.isError = false
        mainActivityViewModelHelper.isConnectedToNetwork = true
        Assert.assertEquals(View.GONE, mainActivityViewModelHelper.getMessageVisibility())

    }

    @Test
    fun getMessageValueTest() {
        mainActivityViewModelHelper.isConnectedToNetwork = false
        `when`(context.resources).thenReturn(this.resources)
        `when`(this.resources?.getString(R.string.no_item_message)).thenReturn("no item message")
        `when`(this.resources?.getString(R.string.error_message)).thenReturn("error message")

        Assert.assertEquals("no item message", mainActivityViewModelHelper.getMessageValue())

        mainActivityViewModelHelper.isConnectedToNetwork = true
        mainActivityViewModelHelper.isError = true
        Assert.assertEquals("error message", mainActivityViewModelHelper.getMessageValue())
    }
}
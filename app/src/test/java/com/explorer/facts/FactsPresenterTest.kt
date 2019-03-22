package com.explorer.facts

import com.explorer.facts.model.Fact
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FactsPresenterTest {

    private val facts = mutableListOf<Fact>()

    @Mock
    private lateinit var view: FactsContract.View
    private var presenter: FactsPresenter? = null

    @Before
    fun setUp() {
        presenter = FactsPresenter(view)
        val fact1 = Fact("abc", "efg", "https://abc.com/xyz.png")
        val fact2 = Fact("", "", "")
        val fact3 = Fact("dfg", "vbn", "https://fgf.com/ngh.png")
        facts.add(fact1)
        facts.add(fact2)
        facts.add(fact3)
    }

    @After
    fun tearDown() {
        facts.clear()
        presenter = null
    }

    @Test
    fun removeEmptyFacts() {
        val facts = presenter?.removeEmptyFacts(facts)
        Assert.assertNotNull(facts)
        Assert.assertEquals(facts?.size, 2)

        Assert.assertEquals(facts?.get(0)?.title, "abc")
        Assert.assertEquals(facts?.get(1)?.title, "dfg")
    }
}
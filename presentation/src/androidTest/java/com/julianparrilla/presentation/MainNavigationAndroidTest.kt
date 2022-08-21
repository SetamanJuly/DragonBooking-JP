package com.julianparrilla.presentation

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.julianparrilla.dragonbooker.R
import com.julianparrilla.dragonbooker.features.main.MainActivity
import com.julianparrilla.dragonbooker.features.main.MainStore
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mockito

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainNavigationAndroidTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    lateinit var mainStore: MainStore
    lateinit var moduleMocked: Module

    @Before
    fun setUp() {
        mainStore = Mockito.mock(MainStore::class.java)
        moduleMocked = module {
            single {
                mainStore
            }
        }
        loadKoinModules(moduleMocked)
    }

    @After
    fun after() {
        unloadKoinModules(moduleMocked)
    }

    @Test
    fun splashActivityTest() {
        val appCompatCheckBox = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.cbOriginAnywhere),
                ViewMatchers.withText("Anywhere"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.auth_nav_host_fragment),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatCheckBox.perform(ViewActions.click())
        val appCompatCheckBox2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.cbDestinationAnywhere), ViewMatchers.withText("Anywhere"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.auth_nav_host_fragment),
                        0
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatCheckBox2.perform(ViewActions.click())
        val appCompatCheckBox3 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.cbPriceTo), ViewMatchers.withText("No limit"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.auth_nav_host_fragment),
                        0
                    ),
                    9
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatCheckBox3.perform(ViewActions.click())
        val appCompatButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btnSearch), ViewMatchers.withText("Search"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.auth_nav_host_fragment),
                        0
                    ),
                    10
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatButton.perform(ViewActions.click())
        val appCompatButton2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btnBack), ViewMatchers.withText("Other search"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.auth_nav_host_fragment),
                        0
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatButton2.perform(ViewActions.click())
    }

    companion object {
        private fun childAtPosition(
            parentMatcher: Matcher<View>,
            position: Int
        ): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description) {
                    description.appendText("Child at position $position in parent ")
                    parentMatcher.describeTo(description)
                }

                public override fun matchesSafely(view: View): Boolean {
                    val parent = view.parent
                    return (
                        parent is ViewGroup && parentMatcher.matches(parent) &&
                            view == parent.getChildAt(position)
                        )
                }
            }
        }
    }
}

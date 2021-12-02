/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.forage

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test the UI features that involved persisted data.
 */
@RunWith(AndroidJUnit4::class)
class PersistenceInstrumentationTests {

    @Before
    fun setup() {
        launchActivity<MainActivity>()
        onView(withId(R.id.add_forageable_fab)).perform(click())

        onView(withId(R.id.name_input)).perform(replaceText("Name"))
        onView(withId(R.id.location_address_input)).perform(replaceText("Address"))
        onView(withId(R.id.notes_input)).perform(replaceText("Notes"))
        onView(withId(R.id.save_btn)).perform(click())
    }

    @Test
    fun new_forageable_is_displayed_in_list() {
        onView(withText("Name")).check(matches(isDisplayed()))
        onView(withText("Address")).check(matches(isDisplayed()))
    }

    @Test
    fun new_forageable_is_displayed_in_detail() {
        onView(withText("Name")).perform(click())
        onView(withText("Name")).check(matches(isDisplayed()))
        onView(withText("Address")).check(matches(isDisplayed()))
        onView(withText(("Currently out of season"))).check(matches(isDisplayed()))
        onView(withText("Notes")).check(matches(isDisplayed()))
    }

    @Test
    fun edit_new_forageable() {
        onView(withText("Name")).perform(click())
        onView(withId(R.id.edit_forageable_fab)).perform(click())
        onView(withId(R.id.name_input)).perform(replaceText("New Name"))
        onView(withId(R.id.location_address_input)).perform(replaceText("New Address"))
        onView(withId(R.id.notes_input)).perform(replaceText("New Notes"))
        onView(withId(R.id.in_season_checkbox)).perform(click())
        onView(withId(R.id.save_btn)).perform(click())
        Thread.sleep(1000)
        onView(withText("New Name")).perform(click())
        Thread.sleep(2000)
        onView(withText("New Name")).check(matches(isDisplayed()))
        onView(withText("New Address")).check(matches(isDisplayed()))
        onView(withText(("Currently in season"))).check(matches(isDisplayed()))
        onView(withText("New Notes")).check(matches(isDisplayed()))
    }

    @Test
    fun delete_new_forageable() {
        onView(withText("Name")).perform(click())
        onView(withId(R.id.edit_forageable_fab)).perform(click())
        onView(withId(R.id.delete_btn)).perform(click())
        Thread.sleep(1000)
        onView(withText("Name")).check(doesNotExist())
    }
}

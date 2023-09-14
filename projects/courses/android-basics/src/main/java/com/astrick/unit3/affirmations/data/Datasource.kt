/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.astrick.unit3.affirmations.data

import com.astrick.androidbasicscompose.R

//import com.example.affirmations.R
//import com.example.affirmations.model.Affirmation

/**
 * [Datasource] generates a list of [Affirmation]
 */
class Datasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.affirmations1),
            Affirmation(R.string.affirmation2, R.drawable.affirmations2),
            Affirmation(R.string.affirmation3, R.drawable.affirmations3),
            Affirmation(R.string.affirmation4, R.drawable.affirmations4),
            Affirmation(R.string.affirmation5, R.drawable.affirmations5),
            Affirmation(R.string.affirmation6, R.drawable.affirmations6),
            Affirmation(R.string.affirmation7, R.drawable.affirmations7),
            Affirmation(R.string.affirmation8, R.drawable.affirmations8),
            Affirmation(R.string.affirmation9, R.drawable.affirmations9),
            Affirmation(R.string.affirmation10, R.drawable.affirmations10)
        )
    }
}

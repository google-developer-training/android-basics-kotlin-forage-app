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

import android.app.Application
import com.example.forage.data.ForageDatabase

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [ForageDatabase]
 */
class BaseApplication : Application() {

    // TODO: provide a ForageDatabase value by lazy here
}
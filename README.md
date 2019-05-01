Olivia's App 
================
This repository contains the code for the application delivered to the client of Marquette University Engineering Senior Design Team B22.

This application is built using Google's Android Room with a View Codelab as a reference. 

Introduction
------------
The purpose of this application is to provide the client with a set of two images depicting "options" for them to make a decision on. The application interfaces with a custom-built peripheral via Bluetooth to initiate the "decision". The client's care team is able to interact with a library of options library, choose a set of options, and present them to the client. The client is able to indicate her choice via the custom base.

This application utilizes Android's Room Persistance Library to create a backend database to hold an the pathname pointing to a high resolution image, pathname pointing to a thumbnail-sized copy of that high resolution image, its title, and its category. Each set of these parameters consitutes a "word" or "option" The database is queried based on hard-coded categories. 

Important notes: 
*The Bluetooth Connection is hard-coded to the specific base originally deleivered to the client in May 2019. If a different base needs to be connected, the hard-coded MAC address needs to be changed.

*This application was tested and delvivered on a Samsung Galaxy Tab S4.
Pre-requisites
--------------

* Android Studio 3.0 or later and you know how to use it.

* Make sure Android Studio is updated, as well as your SDK and Gradle.
Otherwise, you may have to wait for a while until all the updates are done.

* A device or emulator that runs API level 26 or newer

You need to be solidly familiar with the Java programming language,
object-oriented design concepts, and Android Development Fundamentals.
In particular:

* RecyclerView and Adapters
* SQLite database and the SQLite query language
* Threading and AsyncTask
* Bluetooth Implementation
* It helps to be familiar with software architectural patterns that separate
  data from the user interface, such as MVP or MVC. This codelab implements the
  architecture defined in the
  [Guide to App Architecture](
  https://developer.android.com/topic/libraries/architecture/guide.html)

Getting Started
---------------

1. [Install Android Studio](https://developer.android.com/studio/install.html),
if you don't already have it.
2. Download the app repository
2. Import into Android Studio.
3. Build and run the app.

License for Original Google CodeLab
-------

Copyright 2017 Google, Inc.

All image and audio files (including *.png, *.jpg, *.svg, *.mp3, *.wav
and *.ogg) are licensed under the CC BY 4.0 license. All other files are
licensed under the Apache 2 license.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the LICENSE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.

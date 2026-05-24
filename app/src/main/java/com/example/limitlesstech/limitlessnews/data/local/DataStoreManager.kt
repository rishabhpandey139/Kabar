// data/local/DataStoreManager.kt

package com.example.limitlesstech.limitlessnews.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = "app_preferences"
)

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {//it behave like singleton but inside memember

        // 🔥 Login & Onboarding -Keys Set
        private val IS_LOGGED_IN =
            booleanPreferencesKey("is_logged_in")

        private val IS_ONBOARDING_DONE =
            booleanPreferencesKey("is_onboarding_done")

        // 🔥 User Selection
        private val COUNTRY =
            stringPreferencesKey("country")

        private val TOPIC =
            stringPreferencesKey("topic")

        private val SOURCES =
            stringPreferencesKey("sources")
    }

    // ------------------------------------------------
    // 🔥 LOGIN value set+read
    // ------------------------------------------------

    suspend fun saveLoginState(value: Boolean) {

        context.dataStore.edit { pref ->
            pref[IS_LOGGED_IN] = value
        }
    }

    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map { pref ->
            pref[IS_LOGGED_IN] ?: false
        }

    // ------------------------------------------------
    // 🔥 ONBOARDING value set+read
    // ------------------------------------------------

    suspend fun saveOnboardingState(value: Boolean) {

        context.dataStore.edit { pref ->
            pref[IS_ONBOARDING_DONE] = value
        }
    }

    val isOnboardingDone: Flow<Boolean> =
        context.dataStore.data.map { pref ->
            pref[IS_ONBOARDING_DONE] ?: false
        }

    // ------------------------------------------------
    // 🔥 COUNTRY value set+read
    // ------------------------------------------------

    suspend fun saveCountry(value: String) {

        context.dataStore.edit { pref ->
            pref[COUNTRY] = value
        }
    }

    val country: Flow<String> =
        context.dataStore.data.map { pref ->
            pref[COUNTRY] ?: "in"
        }

    // ------------------------------------------------
    // 🔥 TOPIC
    // ------------------------------------------------

    suspend fun saveTopic(value: String) {

        context.dataStore.edit { pref ->
            pref[TOPIC] = value
        }
    }

    val topic: Flow<String> =
        context.dataStore.data.map { pref ->
            pref[TOPIC] ?: "general"
        }

    // ------------------------------------------------
    // 🔥 SOURCES
    // ------------------------------------------------

    suspend fun saveSources(value: Set<String>) {//unique value take karega set

        context.dataStore.edit { pref ->

            pref[SOURCES] =
                value.joinToString(",")
        }
    }

    val sources: Flow<Set<String>> =
        context.dataStore.data.map { pref ->

            pref[SOURCES]
                ?.split(",")
                ?.filter { it.isNotBlank() }
                ?.toSet()
                ?: emptySet()
        }
}
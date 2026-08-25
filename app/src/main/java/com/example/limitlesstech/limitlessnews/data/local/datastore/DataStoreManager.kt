package com.example.limitlesstech.limitlessnews.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.auth.FirebaseAuth
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
    @ApplicationContext private val context: Context,
    private val firebaseAuth: FirebaseAuth
) {

    companion object {

        private val IS_LOGGED_IN =
            booleanPreferencesKey("is_logged_in")

        private val IS_ONBOARDING_DONE =
            booleanPreferencesKey("is_onboarding_done")
    }

    private fun currentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    private fun countryKey(userId: String) =
        stringPreferencesKey("country_$userId")

    private fun topicKey(userId: String) =
        stringPreferencesKey("topic_$userId")

    private fun sourcesKey(userId: String) =
        stringPreferencesKey("sources_$userId")

    // --------------------------------
    // LOGIN
    // --------------------------------

    suspend fun saveLoginState(
        value: Boolean
    ) {

        context.dataStore.edit { pref ->
            pref[IS_LOGGED_IN] = value
        }
    }

    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map { pref ->
            pref[IS_LOGGED_IN] ?: false
        }

    // --------------------------------
    // ONBOARDING
    // --------------------------------

    suspend fun saveOnboardingState(
        value: Boolean
    ) {

        context.dataStore.edit { pref ->
            pref[IS_ONBOARDING_DONE] = value
        }
    }

    val isOnboardingDone: Flow<Boolean> =
        context.dataStore.data.map { pref ->
            pref[IS_ONBOARDING_DONE] ?: false
        }

    // --------------------------------
    // COUNTRY - UID BASED
    // --------------------------------

    suspend fun saveCountry(
        value: String
    ) {

        val userId = currentUserId()
            ?: return

        context.dataStore.edit { pref ->
            pref[countryKey(userId)] = value
        }
    }

    val country: Flow<String>
        get() {

            val userId = currentUserId()

            return context.dataStore.data.map { pref ->

                if (userId == null) {
                    "in"
                } else {
                    pref[countryKey(userId)] ?: "in"
                }
            }
        }

    // --------------------------------
    // TOPIC - UID BASED
    // --------------------------------

    suspend fun saveTopic(
        value: String
    ) {

        val userId = currentUserId()
            ?: return

        context.dataStore.edit { pref ->
            pref[topicKey(userId)] = value
        }
    }

    val topic: Flow<String>
        get() {

            val userId = currentUserId()

            return context.dataStore.data.map { pref ->

                if (userId == null) {
                    "general"
                } else {
                    pref[topicKey(userId)] ?: "general"
                }
            }
        }

    // --------------------------------
    // SOURCES - UID BASED
    // --------------------------------

    suspend fun saveSources(
        value: Set<String>
    ) {

        val userId = currentUserId()
            ?: return

        context.dataStore.edit { pref ->

            pref[sourcesKey(userId)] =
                value.joinToString(",")
        }
    }

    val sources: Flow<Set<String>>
        get() {

            val userId = currentUserId()

            return context.dataStore.data.map { pref ->

                if (userId == null) {
                    emptySet()
                } else {

                    pref[sourcesKey(userId)]
                        ?.split(",")
                        ?.filter {
                            it.isNotBlank()
                        }
                        ?.toSet()
                        ?: emptySet()
                }
            }
        }
}
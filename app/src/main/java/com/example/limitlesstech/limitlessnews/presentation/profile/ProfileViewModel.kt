package com.example.limitlesstech.limitlessnews.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.domain.common.Result
import com.example.limitlesstech.limitlessnews.domain.usecase.profile.SaveProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val saveProfileUseCase: SaveProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onEvent(event: ProfileEvent) {

        when (event) {

            is ProfileEvent.UsernameChanged -> {

                _uiState.update {
                    it.copy(username = event.value)
                }
            }

            is ProfileEvent.FullNameChanged -> {

                _uiState.update {
                    it.copy(fullName = event.value)
                }
            }

            is ProfileEvent.EmailChanged -> {

                _uiState.update {
                    it.copy(email = event.value)
                }
            }

            is ProfileEvent.PhoneChanged -> {

                _uiState.update {
                    it.copy(phone = event.value)
                }
            }

            is ProfileEvent.ImageChanged -> {

                _uiState.update {
                    it.copy(imageUri = event.value)
                }
            }

            ProfileEvent.SaveProfile -> {

                saveProfile()
            }
            is ProfileEvent.ClearErrorMessage -> {
                _uiState.update {
                    it.copy(
                        errorMessage = null
                    )
                }
            }
        }
    }

    private fun saveProfile() {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            when (

                val result = saveProfileUseCase(

                    username = _uiState.value.username,

                    fullName = _uiState.value.fullName,

                    email = _uiState.value.email,

                    phone = _uiState.value.phone,

                    imageUri = _uiState.value.imageUri

                )

            ) {

                is Result.Success -> {

                    _uiState.update {

                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }

                is Result.Failure -> {

                    _uiState.update {

                        it.copy(
                            isLoading = false,
                            errorMessage = mapError(result.error)
                        )
                    }
                }
            }
        }
    }

    private fun mapError(error: DomainError): String {

        return when (error) {

            DomainError.EmptyUsername ->
                "Username is required"

            DomainError.EmptyFullName ->
                "Full name is required"

            DomainError.EmptyEmail ->
                "Email is required"

            DomainError.InvalidEmailFormat ->
                "Invalid email"

            DomainError.EmptyPhone ->
                "Phone number is required"

            DomainError.InvalidPhone ->
                "Invalid phone number"

            DomainError.ProfileImageRequired ->
                "Please select a profile image"

            DomainError.UserNotLoggedIn ->
                "Please login again"

            DomainError.ImageUploadFailed ->
                "Image upload failed"

            DomainError.ProfileSaveFailed ->
                "Unable to save profile"

            DomainError.Network ->
                "No internet connection"

            else ->
                "Something went wrong"
        }
    }
}
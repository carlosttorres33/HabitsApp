package com.carlostorres.habitsapp.authentication.data.matcher

import android.util.Patterns
import com.carlostorres.habitsapp.authentication.domain.matcher.EmailMatcher

class EmailMtcherImpl: EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
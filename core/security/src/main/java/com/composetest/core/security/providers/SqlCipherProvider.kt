package com.composetest.core.security.providers

import androidx.sqlite.db.SupportSQLiteOpenHelper

interface SqlCipherProvider {
    fun getFactory(): SupportSQLiteOpenHelper.Factory?
}
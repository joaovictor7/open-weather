package com.composetest.core.security.providers

import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SqliteCipherProviderImpl @Inject constructor() : SqliteCipherProvider {

    init {
        loadSqlCipherLibrary()
    }

    override fun getFactory(key: String) = SupportOpenHelperFactory(key.toByteArray())

    private fun loadSqlCipherLibrary() {
        System.loadLibrary("sqlcipher")
    }
}